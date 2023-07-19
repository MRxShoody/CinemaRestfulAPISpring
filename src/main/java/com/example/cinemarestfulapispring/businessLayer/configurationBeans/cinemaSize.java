package com.example.cinemarestfulapispring.businessLayer.configurationBeans;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class cinemaSize {

    @Bean
    public int rows(@Value("${cinema.rows}") int rows) {
        return rows;
    }

    @Bean
    public int columns(@Value("${cinema.cols}")  int columns) {
        return columns;
    }
    @Bean
    public String password(){
        return "super_secret";
    }
}
