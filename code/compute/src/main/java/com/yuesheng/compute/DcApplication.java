package com.yuesheng.compute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DcApplication.class, args);
    }

}
