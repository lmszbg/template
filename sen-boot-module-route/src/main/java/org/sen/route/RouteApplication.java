package org.sen.route;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RouteApplication {

    @NacosValue("test")
    private String test;

    public static void main(String[] args) {
        SpringApplication.run(RouteApplication.class, args);
    }

}
