package com.demo.productmanagement.dto;

import com.demo.productmanagement.common.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductAdminDTO extends BaseVo {
    private Long id;
    private String displayName;
    private Double price;
    private String description;
    private List<ImageMapper> images;
    private List<CategoryMapper> categories;
    private int deletedYet;
    private UserDTO creator;
    private Date    createdDtm;
    private UserDTO modifier;
    private Date    updatedDtm;
}
