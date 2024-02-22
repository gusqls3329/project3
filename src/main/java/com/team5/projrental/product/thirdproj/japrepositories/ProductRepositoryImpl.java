package com.team5.projrental.product.thirdproj.japrepositories;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team5.projrental.common.Const;
import com.team5.projrental.entities.*;
import com.team5.projrental.entities.enums.ProductMainCategory;
import com.team5.projrental.entities.enums.ProductStatus;
import com.team5.projrental.entities.enums.ProductSubCategory;
import com.team5.projrental.entities.ids.ProdLikeIds;
import com.team5.projrental.product.model.Categories;
import com.team5.projrental.product.model.ProductListVo;
import com.team5.projrental.product.model.jpa.ActivatedStock;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.team5.projrental.entities.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;
    private final StockJpaRepository stockJpaRepository;
    private final ProdLikeJpaRepository prodLikeJpaRepository;

    public Map<Long, List<ActivatedStock>> getActivatedStock(LocalDate refDate) {

        LocalDate from = LocalDate.of(refDate.getYear(), refDate.getMonth(), refDate.getMonthValue());
        LocalDate toBuilder = refDate.plusMonths(Const.SEARCH_ACTIVATED_MONTH_DURATION);
        LocalDate to = LocalDate.of(toBuilder.getYear(), toBuilder.getMonth(), toBuilder.lengthOfMonth());

        QProduct product = QProduct.product;
        List<Long> iproducts = query.select(product.id)
                .from(product)
                .orderBy(product.id.desc())
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

    @Override
    public List<ProductListVo> findAllBy(Integer sort, String search, ProductMainCategory mainCategory,
                                         ProductSubCategory subCategory, int page, Long iuser, int limit) {

        return query.select(product)
                .from(product)
                .where(whereSearchForFindAllBy(search, mainCategory, subCategory))
                .offset(page)
                .limit(limit)
                .orderBy(orderByForFindAllBy(sort))
                .fetchAll()
                .stream()
                .map(productEntity -> {
                    int prodLikeCount = prodLikeJpaRepository.countByProdLikeIds(ProdLikeIds.builder()
                            .iusers(productEntity.getUser().getId())
                            .iproduct(productEntity.getId())
                            .build());
                    return ProductListVo.builder()
                            .iuser(productEntity.getUser().getId())
                            .nick(productEntity.getUser().getNick())
                            .userPic(productEntity.getUser().getBaseUser().getStoredPic())
                            .iproduct(productEntity.getId())
                            .title(productEntity.getTitle())
                            .prodMainPic(productEntity.getStoredPic())
                            .rentalPrice(productEntity.getRentalPrice())
                            .rentalStartDate(productEntity.getRentalDates().getRentalStartDate())
                            .rentalEndDate(productEntity.getRentalDates().getRentalEndDate())
                            .addr(productEntity.getAddress().getAddr())
                            .restAddr(productEntity.getAddress().getRestAddr())
                            .prodLike(prodLikeCount)
                            //FIXME -> enum 숫자 2차때랑 동일하게 변경. (ordinal 로 불가능한것은 value 설정 하기
                            .istatus(productEntity.getStatus().getCode())
                            .inventory(stockJpaRepository.countById(productEntity.getId()))
                            .isLiked(prodLikeCount == 1 ? 1 : 0)
                            .view(productEntity.getView())
                            .categories(Categories.builder()
                                    .mainCategory(productEntity.getMainCategory().getCategoryNum())
                                    .subCategory(productEntity.getSubCategory().getCategoryNum())
                                    .build())
                            .build();
                })
                .toList();

    }

    @Override
    public List<ProductListVo> findProductListVoByIproducts(List<Integer> imainCategory, List<Integer> isubCategory) {



        return null;
    }


    private BooleanBuilder whereSearchForFindAllBy(String search, ProductMainCategory mainCategory,
                                                   ProductSubCategory subCategory) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (search != null) {
            booleanBuilder.and(product.title.eq("%" + search + "%"));
        }

        booleanBuilder.and(product.mainCategory.eq(mainCategory));
        if (subCategory != null) {
            booleanBuilder.and(product.subCategory.eq(subCategory));
        }
        return booleanBuilder;
    }

    private OrderSpecifier<? extends Number> orderByForFindAllBy(Integer sort) {
        return sort == 1 ? product.prodLikes.size().desc() :
                sort == 2 ? product.view.desc() : null;
    }


}
