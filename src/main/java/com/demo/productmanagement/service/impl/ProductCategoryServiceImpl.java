/**
 * @mbg.generated generator on Fri Aug 26 13:35:33 GMT+07:00 2022
 */
package com.demo.productmanagement.service.impl;

import com.demo.productmanagement.dao.ProductCategoryMapper;
import com.demo.productmanagement.model.ProductCategory;
import com.demo.productmanagement.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryServiceImpl(ProductCategoryMapper productCategoryMapper) {
        this.productCategoryMapper = productCategoryMapper;
    }

    @Override
    public int deleteByPrimaryKey(Long categoryId, Long productId) {
        return productCategoryMapper.deleteByPrimaryKey(categoryId, productId);
    }

    @Override
    public int insert(long categoryId, Long productId, String blogId) {
        return productCategoryMapper.insert(categoryId,productId, blogId);
    }

    @Override
    public ProductCategory selectByPrimaryKey(Long categoryId, Long productId) {
        return productCategoryMapper.selectByPrimaryKey(categoryId, productId);
    }

    @Override
    public List<ProductCategory> selectAll() {
        return productCategoryMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ProductCategory row) {
        return productCategoryMapper.updateByPrimaryKey(row);
    }
}