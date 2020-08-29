package org.zhd.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.xy.api.utils.GlobalExceptionController;

/**
 * @author juny
 */
@SpringBootApplication
@EnableFeignClients(basePackageClasses = {})
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
@ComponentScan(basePackages = {"org.zhd.basic"}, basePackageClasses = {GlobalExceptionController.class})
public class ScpBasicServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScpBasicServerApplication.class, args);
    }

}
