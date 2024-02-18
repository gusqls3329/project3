package com.team5.projrental.product.thirdproj;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team5.projrental.common.Const;
import com.team5.projrental.entities.QPayment;
import com.team5.projrental.entities.QProduct;
import com.team5.projrental.entities.QStock;
import com.team5.projrental.entities.Stock;
import com.team5.projrental.entities.enums.ProductStatus;
import com.team5.projrental.product.model.jpa.ActivatedStock;
import com.team5.projrental.product.thirdproj.japrepositories.ProductJpaRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ThirdProjProductRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;
    private final ProductJpaRepository jpaRepository;


    public Map<Long, List<ActivatedStock>> getActivatedStock(LocalDate refDate) {

        LocalDate from = LocalDate.of(refDate.getYear(), refDate.getMonth(), refDate.getMonthValue());
        LocalDate toBuilder = refDate.plusMonths(Const.SEARCH_ACTIVATED_MONTH_DURATION);
        LocalDate to = LocalDate.of(toBuilder.getYear(), toBuilder.getMonth(), toBuilder.lengthOfMonth());

        QProduct product = QProduct.product;
        List<Long> iproducts = query.select(product.iproduct)
                .from(product)
                .orderBy(product.iproduct.desc())
                .offset(0)
                .limit(Const.ACTIVATED_CACHE_MAX_NUM)
                .fetch();
        Map<Long, List<ActivatedStock>> result = new HashMap<>();

        for (Long iproduct : iproducts) {
            LocalDate dateWalker = LocalDate.of(from.getYear(), from.getMonth(), from.getMonthValue());
            QStock stock = QStock.stock;
            QPayment payment = QPayment.payment;

            List<ActivatedStock> activatedStocks = new ArrayList<>();

            while (!dateWalker.equals(to)) {
                List<Stock> findStock = query.select(stock)
                        .from(stock)
                        .join(stock)
                        .on(stock.product.status.eq(ProductStatus.ACTIVATED))
                        .join(payment)
                        .on(payment.rentalDates.rentalStartDate.after(from).and(payment.rentalDates.rentalEndDate.before(from)))
                        .fetch();

                if (!findStock.isEmpty()) {
                    activatedStocks.add(ActivatedStock.builder()
                            .date(dateWalker)
                            // 상품중 activated 인 상품들의 재고를 가져오는데, refDate 가 결제의 rentalStartDate 와 rentalEndDate 사이가 아닌 재고만 가져오기
                            .activatedStock(findStock)
                            .build());
                }
                dateWalker = dateWalker.plusDays(1);
            }
            result.put(iproduct, activatedStocks);
        }
        return result;

    }

}
