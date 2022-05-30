package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableSwagger2
public class ReggieApplication {
    public static void main(String[] args){
        SpringApplication.run(ReggieApplication.class,args);
        log.info("Spring boot 启动成功");
    }
}
