package com.team5.projrental.product.like;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.product.model.ProductToggleFavDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductLikeMapper {


    int insFav(ProductToggleFavDto dto);
    int delFav(ProductToggleFavDto dto);
    int delFavForDelUser(ProductToggleFavDto dto);
}
