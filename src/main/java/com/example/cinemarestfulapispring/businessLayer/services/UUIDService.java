package com.example.cinemarestfulapispring.businessLayer.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.cinemarestfulapispring.persitenceLayer.cinema.seat;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class UUIDService {

    private Map<String,seat> storage;

    @Autowired
    public UUIDService(Map<String,seat> storage){
        this.storage = storage;
    }

    public String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    public boolean storeSeat(String uuid, seat seat){
        if (storage.containsKey(uuid)){
            return false;
        }
        storage.put(uuid,seat);
        return true;
    }

    public seat getSeat(String uuid){
        return storage.get(uuid);
    }

    public void removeSeat(String uuid){
        storage.remove(uuid);
    }

    public int getSeatsBought(){
        return storage.size();
    }

    public int getRevenue(){
        return storage.values().stream().mapToInt(seat::getPrice).sum();
    }

}
