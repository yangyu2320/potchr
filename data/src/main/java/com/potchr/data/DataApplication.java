package com.potchr.data;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableCaching
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@SpringBootApplication(scanBasePackages = {"com.potchr.data", "com.potchr.ncode"})
//@EnableDiscoveryClient
//@EnableFeignClients
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplicationBuilder(DataApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .build();
        ConfigurableApplicationContext applicationContext = springApplication.run(args);
    }
}
