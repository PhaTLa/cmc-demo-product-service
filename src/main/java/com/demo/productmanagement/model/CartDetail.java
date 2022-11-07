package com.demo.productmanagement.model;

import lombok.Data;

import java.util.Date;

@Data
public class CartDetail {
    private Long cartId;

    private Long productId;

    private Integer quantity;

    private Date createdDtm;

    private Long createdId;

    private Date updatedDtm;

    private Long updatedId;
}