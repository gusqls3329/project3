package com.team5.projrental.product.thirdproj.japrepositories.product;

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
import com.team5.projrental.product.thirdproj.japrepositories.product.like.ProductLikeRepository;
import com.team5.projrental.product.thirdproj.japrepositories.product.stock.StockRepository;
import com.team5.projrental.product.thirdproj.model.ProductListForMainDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.team5.projrental.entities.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;
    private final StockRepository stockRepository;
    private final ProductLikeRepository productLikeRepository;

    public Map<Long, List<ActivatedStock>> getActivatedStock(LocalDateTime refDate) {

        LocalDateTime from = LocalDateTime.of(refDate.getYear(), refDate.getMonth(), refDate.getMonthValue(), 0, 0, 0);
        LocalDateTime toBuilder = refDate.plusMonths(Const.SEARCH_ACTIVATED_MONTH_DURATION);
        LocalDateTime to = LocalDateTime.of(toBuilder.getYear(), toBuilder.getMonth(), toBuilder.toLocalDate().lengthOfMonth(),
                0, 0, 0);

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
                        .on(stock.product.status.eq(ProductStatus.ACTIVE))
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
                    int prodLikeCount = productLikeRepository.countByProdLikeIds(ProdLikeIds.builder()
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
                            .inventory(stockRepository.countById(productEntity.getId()))
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

    @Override
    public List<ProductListForMainDto> findEachTop8ByCategoriesOrderByIproductDesc(int limitNum) {


        List result = em.createNativeQuery("" +
                        "select " +
                        "U.iuser, U.nick, U.stored_pic as userPic, " +
                        "SQ.iproduct, SQ.title, SQ.stored_pic as prodMainPic, " +
                        "SQ.rental_price as rentalPrice, SQ.rental_start_date as rentalStartDate, " +
                        "SQ.rental_end_date as rentalEndDate, SQ.addr, SQ.rest_addr as restAddr, " +
                        "SQ.status, SQ.view, SQ.main_category as mainCategory, " +
                        "SQ.sub_category as subCateogry " +
                        "from ( " +
                        "select row_number() over(partition by main_category, sub_category order by iproduct desc) rn " +
                        "from product P" +
                        "order by P.iproduct desc" +
                        ") SQ" +
                        "join USER U on U.iuser = SQ.iuser " +
                        "where SQ.rn < :limitNum " +
                        "order By SQ.main_category, SQ.sub_category, SQ.rn ", ProductListForMainDto.class)
                .setParameter("limitNum", limitNum)
                .getResultList();


        return result;
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
