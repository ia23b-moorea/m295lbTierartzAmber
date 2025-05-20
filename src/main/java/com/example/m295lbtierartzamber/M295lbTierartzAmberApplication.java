package com.example.m295lbtierartzamber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class M295lbTierartzAmberApplication {

    @RequestMapping("/")
    public String home() {
        return "Willkommen zum Tieratz von Amber! :)";
    }

    public static void main(String[] args) {
        SpringApplication.run(M295lbTierartzAmberApplication.class, args);
    }

}
