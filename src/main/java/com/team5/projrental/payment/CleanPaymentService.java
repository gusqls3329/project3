package com.team5.projrental.payment;

import com.team5.projrental.common.Flag;
import com.team5.projrental.common.Role;
import com.team5.projrental.common.exception.BadDivInformationException;
import com.team5.projrental.common.exception.NoSuchPaymentException;
import com.team5.projrental.common.exception.NoSuchProductException;
import com.team5.projrental.common.exception.NoSuchUserException;
import com.team5.projrental.common.exception.base.BadDateInfoException;
import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.exception.base.BadProductInfoException;
import com.team5.projrental.common.exception.base.WrapRuntimeException;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.PaymentVo;
import com.team5.projrental.payment.model.proc.*;
import com.team5.projrental.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static com.team5.projrental.common.exception.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CleanPaymentService implements RefPaymentService {

    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public ResVo postPayment(PaymentInsDto paymentInsDto) {

        int loginUserPk = getLoginUserPk();
        paymentInsDto.setIbuyer(loginUserPk);
        CommonUtils.ifFalseThrow(NoSuchUserException.class, NO_SUCH_USER_EX_MESSAGE,
                productRepository.findIuserCountBy(paymentInsDto.getIbuyer()));
        CommonUtils.ifBeforeThrow(BadDateInfoException.class, RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE,
                paymentInsDto.getRentalEndDate(), paymentInsDto.getRentalStartDate());


        // 해당 상품의 현재 등록된 rentalPrice, deposit, price 를 가져온다
        List<GetDepositAndPriceFromProduct> validationInfoFromProduct =
                paymentRepository.getValidationInfoFromProduct(paymentInsDto.getIproduct());
        CommonUtils.checkNullOrZeroIfCollectionThrow(NoSuchProductException.class, NO_SUCH_PRODUCT_EX_MESSAGE,
                validationInfoFromProduct);


        // iproduct 는 문제 없음이 검증된 상태.
        // deposit 과 price, rental_price 는 join 할 경우 현재기준으로 조회되며, 모든 리스트에 값이 일정함. 따라서
        GetDepositAndPriceFromProduct productValidation = validationInfoFromProduct.get(0);

        if (productValidation.getIseller() == loginUserPk) throw new BadProductInfoException(BAD_PRODUCT_INFO_EX_MESSAGE);


        // 거래 시작일이 제품의 거래시작일짜보다 이전이거나, 거래 종료일이 제품의 거래종료일짜보다 이후면 예외 발생
        CommonUtils.ifBeforeThrow(BadDateInfoException.class, ILLEGAL_DATE_EX_MESSAGE,
                paymentInsDto.getRentalStartDate(), productValidation.getProductRentalStartDate());
        CommonUtils.ifAfterThrow(BadDateInfoException.class, ILLEGAL_DATE_EX_MESSAGE,
                productValidation.getProductRentalEndDate(), paymentInsDto.getRentalEndDate());

        // 이미 등록된 날짜에는 동일한 상품이 더이상 결제등록 되지 않도록 예외처리
        AtomicReference<Integer> inventoryCounter = new AtomicReference<>(productValidation.getInventory());
        validationInfoFromProduct.forEach(o -> {
            if (o.getPaymentRentalStartDate() == null || o.getPaymentRentalEndDate() == null) return;

            if (!CommonUtils.notBetweenChecker(o.getPaymentRentalStartDate(), o.getPaymentRentalEndDate(),
                    paymentInsDto.getRentalStartDate(), paymentInsDto.getRentalEndDate())) {
                inventoryCounter.getAndSet(inventoryCounter.get() - 1);
            }
            if (inventoryCounter.get() == 0) {
                throw new BadDateInfoException(ILLEGAL_DATE_EX_MESSAGE);
            }
        });

        // 데이터 세팅 (+마지막 예외처리)
        paymentInsDto.setIpaymentMethod(CommonUtils.ifPaymentMethodNotContainsThrowOrReturn(paymentInsDto.getPaymentMethod()));
        paymentInsDto.setRentalDuration(((int) ChronoUnit.DAYS.between(paymentInsDto.getRentalStartDate(),
                paymentInsDto.getRentalEndDate())) + 1);
        paymentInsDto.setPrice(productValidation.getRentalPrice() * paymentInsDto.getRentalDuration());
        paymentInsDto.setCode(createCode());
//        paymentInsDto.setDeposit(CommonUtils.getDepositFromPer(paymentInsDto.getPrice(),
//                CommonUtils.getDepositPerFromPrice(productValidation.getPrice(), productValidation.getDeposit())));
        paymentInsDto.setDeposit(productValidation.getDeposit());

        if (paymentRepository.savePayment(paymentInsDto) != 0) {
            if (paymentRepository.saveProductPayment(paymentInsDto.getIproduct(), paymentInsDto.getIpayment()) != 0) {
                if (paymentRepository.savePaymentStatus(paymentInsDto.getIpayment(), productValidation.getIseller()) != 0 &&
                        paymentRepository.savePaymentStatus(paymentInsDto.getIpayment(), loginUserPk) != 0) {
                    return new ResVo(paymentInsDto.getIpayment());
                }
            }
        }

        throw new WrapRuntimeException(SERVER_ERR_MESSAGE);

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
     * @param div
     * @return ResVo
     */
    @Transactional
    public ResVo delPayment(Integer ipayment, Integer div) {

        // 데이터 검증
        int iuser = getLoginUserPk();
        GetInfoForCheckIproductAndIuserResult checkResult = paymentRepository.checkIuserAndIproduct(ipayment, iuser);
        if (checkResult == null) {
            throw new NoSuchProductException(NO_SUCH_PRODUCT_EX_MESSAGE);
        }


        if (checkResult.getISeller() != iuser && checkResult.getIBuyer() != iuser) {
            throw new NoSuchUserException(NO_SUCH_USER_EX_MESSAGE);
        }

        // 취소요청이 아니면 오늘이 반납일보다 이후여야만 함.
        // 오늘이 반납일보다 이후가 아닌데 (대여 중인데) 삭제나 숨김 요청을 한 경우
        if (div != 3 && !LocalDate.now().isEqual(checkResult.getRentalEndDate()) && !LocalDate.now().isAfter(checkResult.getRentalEndDate())) {
            throw new BadDateInfoException(BAD_RENTAL_DEL_EX_MESSAGE);
        }
        // 반납일이 오늘보다 이후인데 취소요청을 한 경우
        if (div == 3 && LocalDate.now().isAfter(checkResult.getRentalEndDate())) {
            throw new BadDateInfoException(BAD_RENTAL_CANCEL_EX_MESSAGE);
        }


        // 변경할 istatus 생성
        Integer istatusForUpdate = divResolver(div, checkResult.getIstatus(), iuser == checkResult.getIBuyer() ? Role.BUYER :
                iuser == checkResult.getISeller() ? Role.SELLER : null);
        // 객체 생성
        DelPaymentDto delPaymentDto = new DelPaymentDto(ipayment, istatusForUpdate, iuser);

        if (paymentRepository.deletePayment(delPaymentDto) == 0) {
            throw new WrapRuntimeException(SERVER_ERR_MESSAGE);
        }
        return new ResVo(istatusForUpdate);
    }


    public PaymentVo getPayment(Integer ipayment) {

        // 가져오기
        // iuser 또는 ipayment 가 없으면 결과가 size 0 일 것
        GetPaymentListResultDto aPayment = paymentRepository.findPaymentBy(
                new GetPaymentListDto(getLoginUserPk(), Flag.ONE.getValue(), ipayment));
        CommonUtils.ifAllNotNullThrow(NoSuchPaymentException.class, NO_SUCH_PAYMENT_EX_MESSAGE);
        return new PaymentVo(aPayment);
    }

    /*
    ------- Extracted Method -------
    */
    private String createCode() {

        String systemCurrent = String.valueOf(System.currentTimeMillis());
        String uuidBackBase = UUID.randomUUID().toString();

        return uuidBackBase.substring(0, 4) +
                systemCurrent +
                uuidBackBase.substring(uuidBackBase.length() - 3);
    }

    /**
     * 0: 활성화 상태
     * 1: 거래 완료됨
     * <p>
     * -1: 삭제됨
     * -2: 숨김
     * -3: 취소됨
     * -4: 기간지남
     * <p>
     * 논리적 삭제 가능 숫자: 1, -2, -3
     * 숨김 가능 숫자: 1, -3
     * 취소 요청시:
     * Role.buyer ->
     */
    private Integer divResolver(Integer div, Integer istatus, Role role) {

        if (role == null) throw new BadInformationException(BAD_INFO_EX_MESSAGE);
        if (istatus == 3 && role == Role.BUYER || istatus == 2 && role == Role.SELLER)
            // 이미 취소한 상태.
            throw new BadDivInformationException(BAD_DIV_INFO_EX_MESSAGE);
        if (div == 1 || div == 2) {
            if (istatus == -3 || istatus == 1 || (div == 1 && istatus == -2)) {
                return div * -1;
            }
        }
        if (div == 3) {
            if (istatus == 0) {
//                if (role == Role.BUYER) {
//                    return 3;
//                }
//                if (role == Role.SELLER) {
//                    return 2;
//                }
//            }
//            if (istatus == 2 && role == Role.BUYER || istatus == 3 && role == Role.SELLER) {
                return div * -1;
            }
        }
        throw new BadDivInformationException(BAD_DIV_INFO_EX_MESSAGE);
    }

    private int getLoginUserPk() {
        return authenticationFacade.getLoginUserPk();
    }

}
