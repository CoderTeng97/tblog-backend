package com.tg.blog;

import com.tg.blog.boot.ApplicationStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@ComponentScan(basePackages = "com.tg.blog")
@EnableSwagger2
@SpringBootApplication
public class TblogApplication extends ApplicationStarter{
    public static  void main(String[] args){
        start(args);
        SpringApplication.run(TblogApplication.class, args);
    }
}
