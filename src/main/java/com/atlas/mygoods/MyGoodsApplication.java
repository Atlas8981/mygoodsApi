package com.atlas.mygoods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyGoodsApplication{

    public static void main(String[] args) {
        SpringApplication.run(MyGoodsApplication.class, args);
    }

    @GetMapping(path = "/")
    public String greeting() {
        return "This is my good backend";
    }

}
