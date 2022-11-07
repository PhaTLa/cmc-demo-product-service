package com.demo.productmanagement.model;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Byte id;

    private String name;

    private Date createdDtm;

    private Long createdId;

    private Date updatedDtm;

    private Long updatedId;
}