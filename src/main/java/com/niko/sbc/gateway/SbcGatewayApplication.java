package com.niko.sbc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SbcGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbcGatewayApplication.class, args);
    }

}
