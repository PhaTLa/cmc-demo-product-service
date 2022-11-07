package com.demo.productmanagement.dao;


import com.demo.productmanagement.model.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Image row);

    Image selectByPrimaryKey(Long id);

    List<Image> selectAll();

    List<Image> selectAllByProductID(Long productID);

    int updateByPrimaryKey(Image row);

    List<Image> selectByBlogId(String blogId);
}