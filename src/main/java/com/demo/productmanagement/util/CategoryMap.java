package com.demo.productmanagement.util;

import com.demo.productmanagement.dto.CategoryMapper;
import com.demo.productmanagement.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMap {
    public static List<CategoryMapper> mapCategory(List<Category> ctg){
        List<CategoryMapper> listDTO = new ArrayList<>();
        for (Category item:ctg
        ) {
            listDTO.add(new CategoryMapper(item.getId(), item.getName()));

        }
        return listDTO;
    }
}
