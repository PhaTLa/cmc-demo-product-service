package com.demo.productmanagement.controller;

import com.demo.productmanagement.model.Image;
import com.demo.productmanagement.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ImagesController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/save-image")
    public ResponseEntity<?> saveImage(@RequestBody Image image){
        int rowInserted = imageService.insert(image);
        if(rowInserted > 0){
            return ResponseEntity.ok().body(image);
        }else{
            return ResponseEntity.internalServerError().body("Insert image failed!");
        }
    }

    @GetMapping("/get-image")
    public ResponseEntity<?> getImageByBlogId(@RequestParam("blogId") String blogId){
        return ResponseEntity.ok(imageService.findByBlogId(blogId));
    }
}
