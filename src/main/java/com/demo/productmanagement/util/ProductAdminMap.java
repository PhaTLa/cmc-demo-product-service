package com.demo.productmanagement.util;


import com.demo.productmanagement.common.AppConstants;
import com.demo.productmanagement.dto.ProductAdminDTO;
import com.demo.productmanagement.feign.UserFeignService;
import com.demo.productmanagement.model.Product;
import com.demo.productmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ProductAdminMap {
    //        @Autowired
//        public UserMapper userMapper;
    @Value("${user-service.api-key}")
    String apiKey;
    @Autowired
    UserFeignService userFeignService;

    public List<ProductAdminDTO> dtoLstMapProductLst(List<Product> list) {
        List<ProductAdminDTO> listDTO = new ArrayList<>();
        for (Product item : list
        ) {
            ProductAdminDTO itemDTO = dtoMapProduct(item);
            listDTO.add(itemDTO);
        }
        return listDTO;
    }


    public ProductAdminDTO dtoMapProduct(Product product) {
        ProductAdminDTO dTO = new ProductAdminDTO();

        User creator = new User();
        User modifier = new User();

        String headerApiKey = AppConstants.API_KEY_PLACEHOLDER+" "+apiKey;

        if (product.getCreatedId() != null) {
//                 creator = userMapper.selectByPrimaryKey(item.getCreatedId());
            creator = userFeignService.getUserByPrimaryKey(product.getCreatedId(), headerApiKey);
        }
        if (product.getUpdatedId() != null) {
//                 modifier = userMapper.selectByPrimaryKey(item.getUpdatedId());
            modifier = userFeignService.getUserByPrimaryKey(product.getUpdatedId(), headerApiKey);
        }
        dTO = new ProductAdminDTO(product.getId(), product.getName(), product.getPrice(), product.getDesc(),
                ImageMap.MapImage(product.getListImg()), CategoryMap.mapCategory(product.getListCategory()),
                product.getDeleteYn(), UserMap.mapUser(creator), product.getCreatedDtm(), UserMap.mapUser(modifier), product.getUpdatedDtm());
        return dTO;
    }
}
