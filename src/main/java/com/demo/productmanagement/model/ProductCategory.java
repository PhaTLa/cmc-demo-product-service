package com.demo.productmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {
    private Long categoryId;

    private Long productId;

    private Date createdDtm;

    private Long createdId;

    private Date updatedDtm;

    private Long updatedId;

    public ProductCategory(Long categoryId, Long productId) {
        this.categoryId = categoryId;
        this.productId = productId;
    }
}