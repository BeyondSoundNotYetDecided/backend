package com.example.barmi_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Barmi_backApplication {

    public static void main(String[] args) {
        SpringApplication.run(Barmi_backApplication.class, args);
    }

}
