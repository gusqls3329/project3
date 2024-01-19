package com.team5.projrental.product.like;

import com.team5.projrental.common.model.ResVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/prod")
@Controller
public class ProductLikeController {


    private final ProductLikeService service;

    @GetMapping("/fav/{iuser}/{iproduct}")
    @Operation(summary = "찜 기능", description = "찜 토글")
    public ResVo toggleFav(@PathVariable int iuser, @PathVariable int iproduct) {
        return service.toggleFav(iuser, iproduct);
    }
}
