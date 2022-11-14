package com.demo.productmanagement.feign;

import com.demo.productmanagement.feign.dto.UserIdAndNameRespDto;
import com.demo.productmanagement.feign.dto.UsernameRequest;
import com.demo.productmanagement.model.User;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user-management-service")
public interface UserFeignService {

//    @RequestLine("GET /api/get-user/{id}")
    @GetMapping("/api/get-user/{id}")
    User getUserByPrimaryKey(@PathVariable("id") Long id, @RequestHeader("api_key") String apiKey);

    @GetMapping("/get-id/{username}")
    UserIdAndNameRespDto getIdByUserName(@PathVariable("username") UsernameRequest username, @RequestHeader("api_key") String apiKey);
}
