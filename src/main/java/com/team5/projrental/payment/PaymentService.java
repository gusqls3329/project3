package com.team5.projrental.payment;

import com.team5.projrental.common.Role;
import com.team5.projrental.common.exception.*;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.proc.DelPaymentDto;
import com.team5.projrental.payment.model.proc.GetInfoForCheckIproductAndIuserResult;
import com.team5.projrental.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.team5.projrental.common.Const.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ResVo postPayment(PaymentInsDto paymentInsDto) {

        // 1일당 가격 가져오기
        int rentalPrice = productRepository.findRentalPriceBy(paymentInsDto.getIproduct());
        // request 데이터 검증
        if (rentalPrice == 0) {
            throw new NoSuchProductException(NO_SUCH_PRODUCT_EX_MESSAGE);
        }
        CommonUtils.ifFalseThrow(NoSuchUserException.class, NO_SUCH_USER_EX_MESSAGE,
                productRepository.findIuserCountBy(paymentInsDto.getIbuyer()));
        CommonUtils.ifBeforeThrow(BadDateInfoException.class, RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE,
                paymentInsDto.getRentalEndDate(), paymentInsDto.getRentalStartDate());


        // 데이터 세팅 (+마지막 예외처리)
        paymentInsDto.setIpaymentMethod(CommonUtils.ifPaymentMethodNotContainsThrowOrReturn(paymentInsDto.getPaymentMethod()));
        paymentInsDto.setRentalDuration(((int) ChronoUnit.DAYS.between(paymentInsDto.getRentalStartDate(),
                paymentInsDto.getRentalEndDate())) + 1);
        paymentInsDto.setPrice(rentalPrice * paymentInsDto.getRentalDuration());
        paymentInsDto.setCode(createCode());

        // insert (select key 사용)
        if (paymentRepository.savePayment(paymentInsDto) != 0 &&
                productRepository.updateIpayment(paymentInsDto.getIproduct(), paymentInsDto.getIpayment()) != 0) {
            if (paymentRepository.saveProductPayment(paymentInsDto.getIproduct(), paymentInsDto.getIpayment()) != 0) {
                return new ResVo(1);
            }
        }

        throw new RuntimeException(SERVER_ERR_MESSAGE);

    }

    /**
     * div == 3
     * -3: ex (이미 완료된 거래)
     * -2: ex (이미 완료된 거래)
     * -1: ex (이미 완료된 거래)
     * 0: do (2 or 3)
     * 1: ex (이미 완료된 거래)
     * 2: do or ex (요청자가 구매자인 경우만 -3)
     * 3: do or ex (요청자가 판매자인 경우만 -3)
     * <p>
     * div == 2
     * -3: do
     * -2: ex (이미 숨김처리 됨)
     * -1: ex (삭제된 상품을 숨길 수 없음)
     * 0: ex (거래중이면 숨길수 없음)
     * 1: do
     * 2: ex (거래중이면 숨길수 없음)
     * 3: ex (거래중이면 숨길수 없음)
     * <p>
     * div == 1
     * -3: do
     * -2: do
     * -1: ex (이미 삭제된 결제)
     * 0: ex (거래중이면 삭제 불가능)
     * 1: do
     * 2: ex (거래중이면 삭제 불가능)
     * 3: ex (거래중이면 삭제 불가능)
     *
     * @param ipayment
     * @param iuser
     * @param div
     * @return ResVo
     */
    public ResVo delPayment(Integer ipayment, Integer iuser, Integer div) {

        // 데이터 검증
//        CommonUtils.ifFalseThrow(NoSuchProductException.class, NO_SUCH_PRODUCT_EX_MESSAGE,
//                productRepository.findIproductCountBy(iproduct));
//        CommonUtils.ifFalseThrow(NoSuchUserException.class, NO_SUCH_USER_EX_MESSAGE,
//                productRepository.findIuserCountBy(iuser));

        GetInfoForCheckIproductAndIuserResult checkResult = paymentRepository.checkIuserAndIproduct(ipayment);
        if (checkResult.getRentalEndDate() == null) {
            throw new NoSuchProductException(NO_SUCH_PRODUCT_EX_MESSAGE);
        }
        if (checkResult.getISeller() != iuser && checkResult.getIBuyer() != iuser) {
            throw new NoSuchUserException(NO_SUCH_USER_EX_MESSAGE);
        }
        // 취소요청이 아니면 오늘이 반납일보다 이후여야만 함.
        // 오늘이 반납일보다 이후가 아닌데 (대여 중인데) 삭제나 숨김 요청을 한 경우
        if (div != 3 && !LocalDate.now().isAfter(checkResult.getRentalEndDate())) {
            throw new BadDateInfoException(BAD_RENTAL_DEL_EX_MESSAGE);
        }
        // 반납일이 오늘보다 이후인데 취소요청을 한 경우
        if (div == 3 && LocalDate.now().isAfter(checkResult.getRentalEndDate())) {
            throw new BadDateInfoException(BAD_RENTAL_CANCEL_EX_MESSAGE);
        }


        // 객체 생성
        DelPaymentDto delPaymentDto = new DelPaymentDto(ipayment, divResolver(div, checkResult.getIstatus(), iuser == checkResult.getIBuyer() ? Role.BUYER :
                iuser == checkResult.getISeller() ? Role.SELLER : null));


        return new ResVo(paymentRepository.deletePayment(delPaymentDto));
    }


    /*
    ------- Extracted Method -------
*/
    private String createCode() {
        String nowStr = LocalDateTime.now().toString();
        String uuid = UUID.randomUUID().toString();
        String uuidResult = uuid.substring(uuid.lastIndexOf("-") + 1);

        return new StringBuilder().append(uuidResult).append(nowStr.substring(2, 4)).append(nowStr.substring(nowStr.length() - 6, nowStr.length() - 2))
                .append(nowStr.substring(8, 10)).toString();

    }

    private Integer divResolver(Integer div, Integer istatus, Role role) {
        if (role == null) throw new BadInformationException(BAD_INFO_EX_MESSAGE);
        if (div == 1 || div == 2) {
            if (istatus == -3 || istatus == 1 || (div == 1 && istatus == -2)) {
                return div * -1;
            }
        }
        if (div == 3) {
            if (istatus == 0) {
                if (role == Role.BUYER) {
                    return 2;
                }
                if (role == Role.SELLER) {
                    return 3;
                }
            }
            if (istatus == 2 && role == Role.BUYER || istatus == 3 && role == Role.SELLER) {
                return div * -1;
            }
        }
        throw new BadDivInformationException(BAD_DIV_INFO_EX_MESSAGE);
    }
}
