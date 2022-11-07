package com.demo.productmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String password;

    private String email;

    private Byte deleteYn;

    private Date createdDtm;

    private Long createdId;

    private Date updatedDtm;

    private Long updatedId;

    private Byte roleId;
}