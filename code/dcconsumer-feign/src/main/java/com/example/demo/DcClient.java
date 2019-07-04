package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("dc-service")
public interface DcClient {
    @GetMapping("/dc")
    String consumer();
}
