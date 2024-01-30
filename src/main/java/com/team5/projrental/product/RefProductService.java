package com.team5.projrental.product;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.product.model.*;
import com.team5.projrental.product.model.review.ReviewResultVo;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;


public interface RefProductService {

    List<ProductListVo> getProductList(Integer sort, String search, int icategory, int page, int prodPerPage);

    ProductVo getProduct(Integer icategory, Integer iproduct);

    ResVo postProduct(MultipartFile mainPic, List<MultipartFile> pics, ProductInsDto dto);

    ResVo putProduct(MultipartFile mainPic, List<MultipartFile> pics, ProductUpdDto dto);

    ResVo delProduct(Integer iproduct, Integer div);

    List<ProductUserVo> getUserProductList(Integer iuser, Integer page);

    List<ReviewResultVo> getAllReviews(Integer iproduct, Integer page);

    List<LocalDate> getDisabledDate(Integer iproduct, Integer y, Integer m);

}
