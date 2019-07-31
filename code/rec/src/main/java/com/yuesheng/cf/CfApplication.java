package com.yuesheng.cf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CfApplication {

    public static void main(String[] args) {
        SpringApplication.run(CfApplication.class, args);
    }

}
