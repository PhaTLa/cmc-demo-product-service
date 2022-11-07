package com.demo.productmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTOUSER {
    private long id;
    private String name;
    private Double price;
    private String desc;
    private List<String> imgUrl;
}

