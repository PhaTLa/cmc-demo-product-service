/**
 * @mbg.generated generator on Fri Aug 26 13:35:33 GMT+07:00 2022
 */
package com.demo.productmanagement.service;


import com.demo.productmanagement.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    int deleteByPrimaryKey(Long categoryId, Long productId);

    int insert(long categoryId, Long productId, String blogId);

    ProductCategory selectByPrimaryKey(Long categoryId, Long productId);

    List<ProductCategory> selectAll();

    int updateByPrimaryKey(ProductCategory row);
}