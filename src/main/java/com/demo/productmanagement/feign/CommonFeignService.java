package com.demo.productmanagement.feign;

import com.demo.productmanagement.feign.dto.UploadFileResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("common-service")
public interface CommonFeignService {

//    @RequestLine("POST /api/image/uploadFile")
    @PostMapping(value = "/api/image/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UploadFileResponse uploadFile(@RequestPart("file") MultipartFile file);
}
