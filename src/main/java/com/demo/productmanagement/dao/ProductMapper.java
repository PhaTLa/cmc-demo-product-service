package com.demo.productmanagement.dao;

import com.demo.productmanagement.dto.ProductUserDTO;
import com.demo.productmanagement.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product row);

    Product selectByPrimaryKey(Long id);

    Product latestCreatedProduct();

    List<Product> selectAll(int current_page, int page_size, @Param(value = "input") ProductUserDTO input);

    int countAll(@Param(value = "input") ProductUserDTO input);

    int updateByPrimaryKey(Product row);

    List<Product> search(@Param("nameSearch") String nameSearch,@Param("current_page") int current_page,@Param("page_size") int page_size);
}