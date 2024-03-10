package soo.receipt_writer.receipt.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import soo.receipt_writer.receipt.controller.io.ReceiptRemoveRequest;
import soo.receipt_writer.receipt.controller.io.ReceiptRequest;
import soo.receipt_writer.users.LoginSession;
import soo.receipt_writer.users.repository.User;
import soo.receipt_writer.users.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReceiptServiceTest {

    @Autowired
    ReceiptService receiptService;

    @Autowired
    UserRepository userRepository;

    @Test
    void 내역추가() {
        // given
        User user = new User("asdas", "soo", "asd", "asd");
        userRepository.insertOne(user);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("loginSession", new LoginSession(user.getUserId()));
        request.setSession(session);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ReceiptRequest insertOne = new ReceiptRequest("20210101", "10000", "홈플러스");

        // when
        int row = receiptService.addReceipt(insertOne);

        // then
        assertThat(row).isEqualTo(1);
    }

    @Test
    void 내역목록조회() {
        // given
        User user = new User("asdas", "soo", "asd", "asd");
        userRepository.insertOne(user);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("loginSession", new LoginSession(user.getUserId()));
        request.setSession(session);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ReceiptRequest insertOne = new ReceiptRequest("20210101", "10000", "홈플러스");
        receiptService.addReceipt(insertOne);

        // when
        int row = receiptService.selectAll().size();

        // then
        assertThat(row).isEqualTo(1);
    }

    @Test
    void 내역삭제() {
        // given
        User user = new User("asdas", "soo", "asd", "asd");
        userRepository.insertOne(user);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("loginSession", new LoginSession(user.getUserId()));
        request.setSession(session);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String paymentDate = "20210101";

        ReceiptRequest insertOne = new ReceiptRequest("20210101", "10000", "홈플러스");
        receiptService.addReceipt(insertOne);

        // when
        int row = receiptService.removeReceipt(new ReceiptRemoveRequest(0, paymentDate));

        // then
        assertThat(row).isEqualTo(1);
    }
}