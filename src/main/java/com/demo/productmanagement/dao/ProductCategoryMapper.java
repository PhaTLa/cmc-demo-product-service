package com.demo.productmanagement.dao;

import com.demo.productmanagement.model.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductCategoryMapper {
    int deleteByPrimaryKey(@Param("categoryId") Long categoryId, @Param("productId") Long productId);

    int insert(long categoryId, Long productId, String blogId);

    ProductCategory selectByPrimaryKey(@Param("categoryId") Long categoryId, @Param("productId") Long productId);

    List<ProductCategory> selectAll();

    int updateByPrimaryKey(ProductCategory row);
}