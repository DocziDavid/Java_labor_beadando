package com.example.java_labor_beadando;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class JavaLaborBeadandoApplication {

   // @Autowired
    //private dataRepository dataRepository;
    @Autowired
    private dataIIRepository dataIIRepository;
    @Autowired
    private dataIIIRepository dataIIIRepository;

    public static void main(String[] args) {
        SpringApplication.run(JavaLaborBeadandoApplication.class, args);
    }

}
