package com.team5.projrental.product;

import com.team5.projrental.product.model.ProductInsDto;
import com.team5.projrental.product.model.ProductUpdDto;
import com.team5.projrental.product.model.innermodel.StoredFileInfo;
import com.team5.projrental.product.model.proc.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class ProductMapperTest {

    @Autowired
    ProductMapper productMapper;
    InsProdBasicInfoDto insProdBasicInfoDto = new InsProdBasicInfoDto(1, "test", "test", 40,
            "test", new StoredFileInfo("test pic1", "test pic 2"), 100, 10,
            80, LocalDate.of(2022, 11, 2), LocalDate.of(2024, 2, 2),
            LocalDate.of(2024, 3, 3), 1, 1.1, 2.2);


    @Test
    void getRentalPricePerDay() {

        assertThat(productMapper.getRentalPricePerDay(1)).isEqualTo(10000);

    }

    @Test
    void checkIuser() {

        assertThat(productMapper.checkIuser(1)).isEqualTo(1);
        assertThat(productMapper.checkIuser(2)).isEqualTo(1);
        assertThat(productMapper.checkIuser(3)).isEqualTo(1);
        assertThat(productMapper.checkIuser(4)).isEqualTo(1);
        assertThat(productMapper.checkIuser(5)).isEqualTo(1);

    }

    @Test
    void countView() {

        int viewForTest = productMapper.getViewForTest(1);
        log.info("viewForTest = {}", viewForTest);
        int result = productMapper.countView(new GetProductViewAopDto(1));
        int afterViewForTest = productMapper.getViewForTest(1);
        log.info("afterViewForTest = {}", afterViewForTest);
        assertThat(result).isEqualTo(1);
        assertThat(viewForTest + 1).isEqualTo(afterViewForTest);
    }

    @Test
    void getProductList() {

        List<GetProductListResultDto> productList = productMapper.getProductList(new GetProductListDto(1));
        Assertions.assertThat(productList.size()).isEqualTo(1);
        checkProductInfoByIproductIsOne(productList.get(0));

        int result = productMapper.insProduct(this.insProdBasicInfoDto);
        assertThat(result).isEqualTo(1);
        List<GetProductListResultDto> productList1 = productMapper.getProductList(new GetProductListDto(1));
        assertThat(productList1.size()).isEqualTo(2);

        List<GetProductListResultDto> productList2 = productMapper.getProductList(new GetProductListDto(2, null, 1));
        assertThat(productList2.get(0).getIproduct()).isEqualTo(4);
        List<GetProductListResultDto> productList3 = productMapper.getProductList(new GetProductListDto(null, null, 1));
        assertThat(productList3.size()).isEqualTo(4);
    }

    @Test
    void getProduct() {

        GetProductResultDto product = productMapper.getProduct(new GetProductBaseDto(1, 1));
        log.info("product result = {}", product);

        checkProductInfoByIproductIsOne(product);


    }


    @Test
    void getProdEctPics() {

        List<GetProdEctPicDto> prodEctPics = productMapper.getProdEctPics(1);
        assertThat(prodEctPics.size()).isEqualTo(2);
        assertThat(prodEctPics.get(0).getIpics()).isEqualTo(1);
        assertThat(prodEctPics.get(1).getIpics()).isEqualTo(2);
        prodEctPics.forEach(pic -> {
            assertThat((pic.getProdStoredPic())).isEqualTo("test");
            assertThat((pic.getProdRequestPic())).isEqualTo("test2");
        });

    }

    @Test
    void insProduct() {

        int result = productMapper.insProduct(new InsProdBasicInfoDto(1, "test", "test", 40,
                "test", new StoredFileInfo("test pic1", "test pic 2"), 100, 10,
                80, LocalDate.of(2022, 11, 2), LocalDate.of(2024, 2, 2),
                LocalDate.of(2024, 3, 3), 1, 1.1, 2.2));
        assertThat(result).isEqualTo(1);

    }

    @Test
    void insPics() {

        int result = productMapper.insPics(new InsProdPicsDto(3, List.of(new StoredFileInfo("test", "test2"))));
        assertThat(result).isEqualTo(1);

        int result2 = productMapper.insPics(new InsProdPicsDto(3, List.of(new StoredFileInfo("test", "test2"), new StoredFileInfo("test3", "test4"))));
        assertThat(result2).isEqualTo(2);

    }

    @Test
    void getIEupmyun() {

        List<Integer> iEupmyun = productMapper.getIEupmyun(List.of("깃허브동", "박동", "ㅁ잗맫라읍", "라면", "ㅏㅈㅁ대ㅔㄹ잗래면", "ㅏㄹ잳라ㅏㅐㄷ읍", "압량읍"));
        assertThat(iEupmyun.size()).isEqualTo(1);

        List<Integer> iEupmyun2 = productMapper.getIEupmyun(List.of("깃허브동", "용산동", "박동", "ㅁ잗맫라읍", "라면", "ㅏㅈㅁ대ㅔㄹ잗래면", "ㅏㄹ잳라ㅏㅐㄷ읍",
                "압량읍"));
        assertThat(iEupmyun2.size()).isEqualTo(2);


    }

    @Test
    void deletePic() {

        int result = productMapper.insPics(new InsProdPicsDto(3, List.of(new StoredFileInfo("test", "test2"))));
        assertThat(result).isEqualTo(1);

        int result2 = productMapper.insPics(new InsProdPicsDto(3, List.of(new StoredFileInfo("test", "test2"), new StoredFileInfo("test3", "test4"))));
        assertThat(result2).isEqualTo(2);

        assertThat(productMapper.getAllIpics(3).size()).isEqualTo(3);

        productMapper.deletePic(3, productMapper.getAllIpics(3));
        int picsCountForTest = productMapper.getPicsCountForTest(3);
        assertThat(picsCountForTest).isEqualTo(0);


    }

    @Test
    void getProductForUpdate() {

        UpdProdBasicDto productForUpdate = productMapper.getProductForUpdate(new GetProductBaseDto(1, 1));

        assertThat(productForUpdate.getPrice()).isEqualTo(1000000);
        assertThat(productForUpdate.getBuyDate()).isEqualTo(LocalDate.of(2022, 11, 30));
        assertThat(productForUpdate.getRentalStartDate()).isEqualTo(LocalDate.of(2023, 10, 22));
        assertThat(productForUpdate.getRentalEndDate()).isEqualTo(LocalDate.of(2023, 12, 11));


    }

    @Test
    void updateProduct() {

        int result = productMapper.updateProduct(ProductUpdDto.builder().iproduct(3).iuser(3).icategory(3).build());
        assertThat(result).isEqualTo(1);

        GetProductResultDto product = productMapper.getProduct(new GetProductBaseDto(3, 3));
        assertThat(product).isNotNull();

        productMapper.updateProduct(ProductUpdDto.builder().iproduct(3).iuser(3).rentalEndDate(LocalDate.of(2222, 2, 2)).build());
        product = productMapper.getProduct(new GetProductBaseDto(3, 3));
        assertThat(product.getRentalEndDate()).isEqualTo(LocalDate.of(2222, 2, 2));

    }

    @Test
    void getPicCount() {

        int beforePicCount = productMapper.getPicCount(1);
        assertThat(beforePicCount).isEqualTo(2);

        productMapper.insPics(new InsProdPicsDto(1, List.of(new StoredFileInfo("test", "test"))));
        productMapper.insPics(new InsProdPicsDto(1, List.of(new StoredFileInfo("test", "test"))));
        productMapper.insPics(new InsProdPicsDto(1, List.of(new StoredFileInfo("test", "test"))));

        assertThat(productMapper.getPicCount(1)).isEqualTo(beforePicCount + 3);
        assertThat(productMapper.getPicCount(2)).isEqualTo(0);

    }

    @Test
    void changeProdStatus() {

        productMapper.changeProdStatus(new DelProductBaseDto(1, 1, -1));


    }

    @Test
    void updateIpayment() {
    }

    @Test
    void checkIproduct() {
    }


    private static void checkProductInfoByIproductIsOne(GetProductListResultDto product) {
        if (product instanceof GetProductResultDto) {
            GetProductResultDto dto = (GetProductResultDto) product;

            assertThat(dto.getIuser()).isEqualTo(1);
            assertThat(dto.getDeposit()).isEqualTo(700000);
            assertThat(dto.getX()).isEqualTo(2.2);
            assertThat(dto.getContents()).isEqualTo("test contents1");
            assertThat(dto.getBuyDate()).isEqualTo(LocalDate.of(2022, 11, 30));
            assertThat(dto.getY()).isEqualTo(1.1);
        }
        assertThat(product.getNick()).isEqualTo("마루야노올자");
        assertThat(product.getAddr()).isEqualTo("경상남도 창원시 마산합포구 가포동 test addres1");
        assertThat(product.getRentalPrice()).isEqualTo(10000);
        assertThat(product.getTitle()).isEqualTo("test prod1");
        assertThat(product.getUserStoredPic()).isNull();
        assertThat(product.getUserRequestPic()).isNull();
        assertThat(product.getProdMainRequestPic()).isEqualTo("test pic1");
        assertThat(product.getProdMainStoredPic()).isEqualTo("test pic2");
        assertThat(product.getRentalStartDate()).isEqualTo(LocalDate.of(2023, 10, 22));
        assertThat(product.getRentalEndDate()).isEqualTo(LocalDate.of(2023, 12, 11));
    }
}