package com.zk.msgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class MsgoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsgoApplication.class, args);
    }

}
