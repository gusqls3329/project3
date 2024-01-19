package com.team5.projrental.product.like;

import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.product.model.ProductToggleFavDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.team5.projrental.common.exception.ErrorCode.ILLEGAL_EX_MESSAGE;

@Service
@RequiredArgsConstructor
public class ProductLikeService {

    private final ProductLikeMapper mapper;

    public ResVo toggleFav(int iuser, int iproduct) {
        ProductToggleFavDto dto = new ProductToggleFavDto();
        dto.setIuser(iuser);
        dto.setIproduct(iproduct);
        int affectedRow = mapper.delFav(dto);
        if(affectedRow == 0) {
            mapper.insFav(dto);
            return new ResVo(1);
        }
        if(affectedRow == 1) {
            return new ResVo(-1);
        }
        throw new BadInformationException(ILLEGAL_EX_MESSAGE);
    }
}
