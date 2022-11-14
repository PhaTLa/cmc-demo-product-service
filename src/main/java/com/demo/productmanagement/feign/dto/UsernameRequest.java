package com.demo.productmanagement.feign.dto;

public class UsernameRequest {
    String username;

    public UsernameRequest(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }
}
