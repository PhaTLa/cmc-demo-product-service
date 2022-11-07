package com.demo.productmanagement.util;


import com.demo.productmanagement.dto.UserDTO;
import com.demo.productmanagement.model.User;

public class UserMap {


    public static UserDTO mapUser(User user){
        if(user.getId() != null) {
            UserDTO dto = new UserDTO(user.getId(), user.getName(), user.getEmail());
            return dto;
        }
        else
            return new UserDTO();
    }
}
