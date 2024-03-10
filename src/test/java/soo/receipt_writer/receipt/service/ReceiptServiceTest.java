package soo.receipt_writer.receipt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import soo.receipt_writer.pages.PageParams;
import soo.receipt_writer.pages.ParamsOrder;
import soo.receipt_writer.receipt.controller.io.ReceiptRemoveRequest;
import soo.receipt_writer.receipt.controller.io.ReceiptRequest;
import soo.receipt_writer.users.LoginSession;
import soo.receipt_writer.users.repository.User;
import soo.receipt_writer.users.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@SpringBootTest
@Transactional
class ReceiptServiceTest {

    @Autowired
    ReceiptService receiptService;

    @Autowired
    UserRepository userRepository;

    User user = new User("asdas", "soo", "asd", "asd");
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpSession session = new MockHttpSession();

    @BeforeEach
    public void setUp() {
        userRepository.insertOne(user);
        session.setAttribute("loginSession", new LoginSession(user.getUserId()));
        request.setSession(session);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void 내역추가() {
        // given
        ReceiptRequest insertOne = new ReceiptRequest("20210101", "10000", "홈플러스");

        // when
        int row = receiptService.addReceipt(insertOne);

        // then
        assertThat(row).isEqualTo(1);
    }

    @Test
    void 내역목록조회() {
        // given
        ReceiptRequest insertOne = new ReceiptRequest("20210101", "10000", "홈플러스");
        receiptService.addReceipt(insertOne);

        PageParams pageParams = new PageParams("2021", "1", "0", "20", ParamsOrder.DESC);

        // when
        int row = receiptService.selectAll(pageParams).size();

        // then
        assertThat(row).isEqualTo(1);
    }

    @Test
    void 내역삭제() {
        // given
        String paymentDate = "20210101";

        ReceiptRequest insertOne = new ReceiptRequest("20210101", "10000", "홈플러스");
        receiptService.addReceipt(insertOne);

        // when
        receiptService.removeReceipt(new ReceiptRemoveRequest(0, paymentDate));

        // then
        assertThatNoException();
    }

    @Test
    void 월별총액() {
        // given
        ReceiptRequest insert1 = new ReceiptRequest("20210101", "10000", "홈플러스");
        receiptService.addReceipt(insert1);

        ReceiptRequest insert2 = new ReceiptRequest("20210101", "10000", "홈플러스");
        receiptService.addReceipt(insert2);

        ReceiptRequest insert3 = new ReceiptRequest("20210101", "10000", "홈플러스");
        receiptService.addReceipt(insert3);

        PageParams pageParams = new PageParams("2021", "1", "0", "20", ParamsOrder.DESC);

        // when
        receiptService.removeReceipt(new ReceiptRemoveRequest(0, "20210101"));

        Long totalAmount = receiptService.monthTotalAmount(pageParams);

        receiptService.selectAll(pageParams).forEach(System.out::println);

        // then
        assertThat(totalAmount).isEqualTo(20000L);
    }
}