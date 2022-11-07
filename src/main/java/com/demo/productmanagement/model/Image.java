package com.demo.productmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private Long id;

    private Long productId;

    private String url;

    private String desc;

    private Date createdDtm;

    private Long createdId;

    private Date updatedDtm;

    private Long updatedId;
    
    private String blogId;

    public Image(Long productId, String url, String blogId) {
        this.productId = productId;
        this.url = url;
        this.blogId = blogId;
    }
}