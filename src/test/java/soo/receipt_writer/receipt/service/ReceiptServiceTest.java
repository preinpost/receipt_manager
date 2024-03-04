package soo.receipt_writer.receipt.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import soo.receipt_writer.commons.config.LoginUtils;
import soo.receipt_writer.receipt.repository.Receipt;
import soo.receipt_writer.receipt.repository.dto.ReceiptRemoveDTO;
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

        Receipt receipt = Receipt.builder()
                .userId(user.getUserId())
                .receiptYear("2021")
                .receiptDate("0101")
                .paymentDate("20210101")
                .paymentAmount("10000")
                .paymentLocation("홈플러스")
                .build();

        // when
        int row = receiptService.addReceipt(receipt);

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

        Receipt receipt = Receipt.builder()
                .userId(user.getUserId())
                .receiptYear("2021")
                .receiptDate("0101")
                .paymentDate("20210101")
                .paymentAmount("10000")
                .paymentLocation("홈플러스")
                .build();

        receiptService.addReceipt(receipt);

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

        String paymentDate = "20210101";

        Receipt receipt = Receipt.builder()
                .userId(user.getUserId())
                .receiptYear("2021")
                .receiptDate("0101")
                .paymentDate(paymentDate)
                .paymentAmount("10000")
                .paymentLocation("홈플러스")
                .build();

        receiptService.addReceipt(receipt);

        Receipt receipt2 = Receipt.builder()
                .userId(user.getUserId())
                .receiptYear("2021")
                .receiptDate("0101")
                .paymentDate(paymentDate)
                .paymentAmount("10000")
                .paymentLocation("홈플러스")
                .build();

        receiptService.addReceipt(receipt2);

        // when
        int row = receiptService.removeReceipt(new ReceiptRemoveDTO(user.getUserId(), 0, paymentDate));

        // then
        assertThat(row).isEqualTo(1);
    }


}