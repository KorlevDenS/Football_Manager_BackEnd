package com.den.korolev.football_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class FootballManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballManagerApplication.class, args);
    }

}
