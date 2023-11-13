package com.zhly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author 李腾
 * @datetime 2023/11/13 18:30
 * @description 类对象
 */
@SpringCloudApplication
@MapperScan("com.zhly.dao")
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class,args);
        System.out.println("core===========init ok");
    }
}
