package com.demo.productmanagement.controller;

import com.demo.productmanagement.common.AppConstants;
import com.demo.productmanagement.common.ResponseVo;
import com.demo.productmanagement.dto.ProductAddRequestDTO;
import com.demo.productmanagement.dto.ProductAdminDTO;
import com.demo.productmanagement.dto.ProductUserDTO;
import com.demo.productmanagement.feign.CommonFeignService;
import com.demo.productmanagement.feign.UserFeignService;
import com.demo.productmanagement.feign.dto.UploadFileResponse;
import com.demo.productmanagement.feign.dto.UserIdAndNameRespDto;
import com.demo.productmanagement.model.Image;
import com.demo.productmanagement.model.Product;
import com.demo.productmanagement.service.ImageService;
import com.demo.productmanagement.service.ProductCategoryService;
import com.demo.productmanagement.service.ProductService;
import com.demo.productmanagement.util.ProductAdminMap;
import com.demo.productmanagement.util.ProductMap;
import com.demo.productmanagement.util.UploadFileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class ProductRestController {

    @Value("${user-service.api-key}")
    String apiKey;
    @Autowired
    ProductAdminMap adminMap;
    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    ImageService imageService;
    @Autowired
    ProductService productService;

    @Autowired
    UserFeignService userFeignService;

    @Autowired
    CommonFeignService commonFeignService;

    @GetMapping(value = "/products")
    public ResponseEntity<ResponseVo> getAllProductsUser(@RequestParam(value = "page") int currentPage,
                                                         @RequestParam(value = "size") int pageSize, ProductUserDTO input) {
        List<Product> listProduct = new ArrayList<>();
        ResponseVo response = new ResponseVo("OK");


        List<ProductAdminDTO> listDTO = new ArrayList<>();
        listProduct = productService.selectAll(currentPage, pageSize, input);
        listDTO = adminMap.dtoLstMapProductLst(listProduct);
        response.setVoList(listProduct);
        response.setTotal(productService.countAll(input));

        return new ResponseEntity<>(response, listDTO == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @GetMapping(value = "/admin/products")
    public ResponseEntity<ResponseVo> getAllProductsAdmin(@RequestParam(value = "page") int currentPage,
                                                          @RequestParam(value = "size") int pageSize, ProductUserDTO input) {
        List<Product> listProduct = new ArrayList<>();
        ResponseVo response = new ResponseVo("OK");
        List<ProductAdminDTO> listDTO = new ArrayList<>();
        listProduct = productService.selectAll(currentPage, pageSize, input);
        listDTO = adminMap.dtoLstMapProductLst(listProduct);
        response.setVoList(listDTO);
        response.setTotal(productService.countAll(input));
        return new ResponseEntity<>(response, listDTO == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductUserDTO> getDetailProductUser(@PathVariable("id") Long id) {
        Product prd = productService.selectByPrimaryKey(id);
        ProductUserDTO prdDTO = ProductMap.dtoMapProduct(prd);
        return new ResponseEntity<>(prdDTO, prdDTO == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);

    }

    @GetMapping(value = "/admin/product/{id}")
    public ResponseEntity<ProductAdminDTO> getDetailProductAdmin(@PathVariable("id") Long id) {
        Product prd = productService.selectByPrimaryKey(id);
        ProductAdminDTO prdDTO = adminMap.dtoMapProduct(prd);
        return new ResponseEntity<>(prdDTO, prdDTO == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);

    }

    @PostMapping(value = "/admin/addProduct")
    public ResponseEntity<String> addProduct(
            @RequestParam(value = "files", required = false) MultipartFile[] multipartFile,
            String jsonFile, HttpServletRequest request) {
        List<String> listAddedImg = new ArrayList<>();
        try {
            String usernameRq = request.getHeader("username");
            if(!StringUtils.hasLength(usernameRq)){
                return new ResponseEntity<>("Something bị ốm!", HttpStatus.BAD_REQUEST);
            }

            String headerApiKey = AppConstants.API_KEY_PLACEHOLDER+" "+apiKey;
            UserIdAndNameRespDto userReq = userFeignService.getIdByUserName(usernameRq,headerApiKey);

            ProductAddRequestDTO productDTO = new ObjectMapper().readValue(jsonFile, ProductAddRequestDTO.class);

            Product newProduct = new Product(productDTO.getDisplayName(), productDTO.getPrice(), productDTO.getDescription());

            if(userReq!=null && userReq.getId()!=null){
                newProduct.setCreatedId(userReq.getId());
            }

            newProduct.setDeleteYn(Short.valueOf("0"));
            newProduct.setCreatedDtm(new Date());

            int id = productService.insert(new Product(productDTO.getDisplayName(), productDTO.getPrice(), productDTO.getDescription()));
            log.info("productID from mybatis builtIn: {}", id);
            Product newestProduct = productService.latestCreatedProduct();
            log.info("productID from constructor- latestCreatedProduct Function: {}", newestProduct.getId());

            for (MultipartFile file : multipartFile) {
                UploadFileResponse fileResponse = commonFeignService.uploadFile(file);
//                String name = fileStorageService.storeFile(file);
//                listAddedImg.add(name);
                listAddedImg.add(fileResponse.getFileName());
            }

            // insert image for product
            for (String name : listAddedImg) {
                Image newImg = new Image(newestProduct.getId(), name, null);
                imageService.insert(newImg);
            }

            for (long cateID : productDTO.getCategories()) {
                productCategoryService.insert(cateID, newestProduct.getId(), null);
            }


            return new ResponseEntity<>("Chạy Không có lỗi gì cả.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something bị ốm!", HttpStatus.BAD_REQUEST);
        }


    }
}
