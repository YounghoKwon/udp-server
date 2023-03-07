package com.example.open_master_temp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OpenMasterTempApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenMasterTempApplication.class, args);
    }

}
