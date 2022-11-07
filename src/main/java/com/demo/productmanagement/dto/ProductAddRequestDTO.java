package com.demo.productmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddRequestDTO {
    private String displayName;
    private Double price;
    private String description;
//    private MultipartFile[] images;
    private long[] categories;
}
