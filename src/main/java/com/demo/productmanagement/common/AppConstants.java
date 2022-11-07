package com.demo.productmanagement.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
public class AppConstants {
    public static final String API_KEY_HEADER = "api_key";
    public static final String API_KEY_PLACEHOLDER = "Key ";
}
