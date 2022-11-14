package com.demo.productmanagement.feign.feign_interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.uri(
                requestTemplate.request().url()
                        .replaceAll("%40","@")
        );
    }
}
