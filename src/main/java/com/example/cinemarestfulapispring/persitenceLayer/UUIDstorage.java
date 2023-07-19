package com.example.cinemarestfulapispring.persitenceLayer;

import com.example.cinemarestfulapispring.persitenceLayer.cinema.seat;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UUIDstorage {

    private final Map<String, seat> storage;

    public UUIDstorage() {
        this.storage = new ConcurrentHashMap<>();
    }

    @Bean
    public Map<String, seat> storage(){
        return storage;
    }
}
