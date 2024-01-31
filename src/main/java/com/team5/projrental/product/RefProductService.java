package com.team5.projrental.product;

import com.team5.projrental.common.aop.anno.CountView;
import com.team5.projrental.common.aop.category.CountCategory;
import com.team5.projrental.common.exception.BadMainPicException;
import com.team5.projrental.common.exception.BadWordException;
import com.team5.projrental.common.exception.IllegalProductPicsException;
import com.team5.projrental.common.exception.NoSuchProductException;
import com.team5.projrental.common.exception.base.BadDateInfoException;
import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.exception.base.BadProductInfoException;
import com.team5.projrental.common.exception.base.WrapRuntimeException;
import com.team5.projrental.common.exception.checked.FileNotContainsDotException;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.common.model.restapi.Addrs;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.product.model.*;
import com.team5.projrental.product.model.proc.*;
import com.team5.projrental.product.model.review.ReviewResultVo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.team5.projrental.common.Const.*;
import static com.team5.projrental.common.exception.ErrorCode.*;
import static com.team5.projrental.common.exception.ErrorCode.NO_SUCH_PRODUCT_EX_MESSAGE;


public interface RefProductService {

     List<ProductListVo> getProductList(Integer sort,
                                              String search,
                                              int imainCategory,
                                              int isubCategory,
                                              int page,
                                              int prodPerPage);
     ProductVo getProduct(int imainCategory, int isubCategory, Integer iproduct);
     ResVo postProduct(MultipartFile mainPic, List<MultipartFile> pics, ProductInsDto dto);
     ResVo putProduct(MultipartFile mainPic, List<MultipartFile> pics, ProductUpdDto dto);
     ResVo delProduct(Integer iproduct, Integer div) ;

     List<ProductUserVo> getUserProductList(Integer iuser, Integer page) ;

     List<ReviewResultVo> getAllReviews(Integer iproduct, Integer page) ;

     List<LocalDate> getDisabledDate(Integer iproduct, Integer y, Integer m);
}
