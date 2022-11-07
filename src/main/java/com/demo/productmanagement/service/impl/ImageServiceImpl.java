/**
* @mbg.generated
* generator on Mon Aug 29 08:54:41 GMT+07:00 2022
*/
package com.demo.productmanagement.service.impl;

import com.demo.productmanagement.dao.ImageMapper;
import com.demo.productmanagement.model.Image;
import com.demo.productmanagement.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageMapper imageMapper;

    public ImageServiceImpl(ImageMapper imageMapper) {
        this.imageMapper=imageMapper;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return imageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Image row) {
        return imageMapper.insert(row);
    }

    @Override
    public Image selectByPrimaryKey(Long id) {
        return imageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Image> selectAll() {
        return imageMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Image row) {
        return imageMapper.updateByPrimaryKey(row);
    }

    @Override
    public List<Image> findByBlogId(String blogId) {
        return imageMapper.selectByBlogId(blogId);
    }
}