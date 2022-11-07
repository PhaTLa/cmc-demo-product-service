package com.demo.productmanagement.dao;


import com.demo.productmanagement.model.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Category row);

    Category selectByPrimaryKey(Long id);

    List<Category> selectAll();

    int updateByPrimaryKey(Category row);

    List<Category> selectByBlogId(String blogId);
}