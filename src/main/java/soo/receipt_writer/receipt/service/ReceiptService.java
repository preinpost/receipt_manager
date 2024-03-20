package soo.receipt_writer.receipt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soo.receipt_writer.commons.config.LoginUtils;
import soo.receipt_writer.commons.exceptions.InvalidInputException;
import soo.receipt_writer.pages.PageParams;
import soo.receipt_writer.receipt.controller.io.ReceiptRemoveRequest;
import soo.receipt_writer.receipt.controller.io.ReceiptRequest;
import soo.receipt_writer.receipt.repository.ReceiptRepository;
import soo.receipt_writer.receipt.repository.dao.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    public int addReceipt(ReceiptRequest receipt) {

        long maxSeq = receiptRepository.getMaxSeq(
                new GetMaxSeqParams(LoginUtils.loginSession().userId(), receipt.paymentDate())
        );

        int result = receiptRepository.insertOne(
                new ReceiptInsertDAO(
                        LoginUtils.loginSession().userId(),
                        receipt.paymentDate(),
                        maxSeq,
                        receipt.paymentAmount(),
                        receipt.paymentLocation()
                )
        );

        if (result == 0) {
            throw new InvalidInputException("영수증 추가에 실패했습니다.");
        }

        return result;
    }

    public List<ReceiptSelectAllDAO> selectAll(PageParams params) {

        YearMonth yearMonth = YearMonth.from(LocalDate.of(params.year(), params.month(), 1));
        String dateStart = yearMonth.atDay(1).toString().replaceAll("-", "");
        String dateEnd = yearMonth.atEndOfMonth().toString().replaceAll("-", "");
        String sortBy = params.sort().toString();

        return receiptRepository.selectAllByMonth(
                new SelectAllByMonthParams(
                        LoginUtils.loginSession().userId(),
                        dateStart,
                        dateEnd,
                        sortBy
                )
        );
    }

    public void removeReceipt(ReceiptRemoveRequest request) {

        String userId = LoginUtils.loginSession().userId();

        ReceiptRemoveDAO removeDTO = new ReceiptRemoveDAO(userId, request.seq(), request.paymentDate());
        if (receiptRepository.removeReceipt(removeDTO) <= 0) {
            throw new RuntimeException("영수증 삭제에 실패했습니다.");
        }
    }

    public Long monthTotalAmount(PageParams params) {

        YearMonth yearMonth = YearMonth.from(LocalDate.of(params.year(), params.month(), 1));
        String dateStart = yearMonth.atDay(1).toString().replaceAll("-", "");
        String dateEnd = yearMonth.atEndOfMonth().toString().replaceAll("-", "");

        return receiptRepository.monthTotalAmount(
                new ReceiptMonthTotalAmountDAO(
                        LoginUtils.loginSession().userId(),
                        dateStart,
                        dateEnd
                )
        );
    }
}
