package com.demo.productmanagement.controller;

import com.demo.productmanagement.dto.CategoryMapper;
import com.demo.productmanagement.model.Category;
import com.demo.productmanagement.model.Image;
import com.demo.productmanagement.service.CategoryService;
import com.demo.productmanagement.util.CategoryMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class CategoryRestController {

//    CategoryMap categoryMap = new CategoryMap();

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/categories")
    public ResponseEntity<?> getAllCategories(){
        try {
            List<Category> list = categoryService.selectAll();
            List<CategoryMapper> listDTO = CategoryMap.mapCategory(list);
            return  new ResponseEntity<>(listDTO, HttpStatus.OK);
        }
        catch (Exception e){
             return  new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-category")
    public ResponseEntity<?> saveCategory(@RequestBody Category cat){
        int rowInserted = categoryService.insert(cat);
        if(rowInserted > 0){
            return ResponseEntity.ok().body(cat);
        }else{
            return ResponseEntity.internalServerError().body("Insert category failed!");
        }
    }

    @GetMapping("/get-categories")
    public ResponseEntity<?> getCategoriesByBlogId(@RequestParam("blogId") String blogId){
        return ResponseEntity.ok(categoryService.findByBlogId(blogId));
    }
}
