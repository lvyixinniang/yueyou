package com.yueyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class YueyouApplication {

    public static void main(String[] args) {
        SpringApplication.run(YueyouApplication.class, args);
    }

}
