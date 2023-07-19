package com.example.cinemarestfulapispring.businessLayer.services;


import com.example.cinemarestfulapispring.businessLayer.DTOs.cinemaDTO;
import com.example.cinemarestfulapispring.businessLayer.records.purchaseRequest;
import com.example.cinemarestfulapispring.businessLayer.records.purchaseResponse;
import com.example.cinemarestfulapispring.businessLayer.records.responseBodyError;
import com.example.cinemarestfulapispring.persitenceLayer.cinema.seat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class cinemaService {

    private final cinemaDTO cinemaDTO;
    private final int row;
    private final int column;
    private final UUIDService UUIDService;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String adminPassword;

    @Autowired
    public cinemaService( cinemaDTO cinemaDTO, UUIDService UUIDService, String adminPassword) {
        this.adminPassword = adminPassword;
        this.cinemaDTO = cinemaDTO;
        row = cinemaDTO.getTotal_rows();
        column = cinemaDTO.getTotal_columns();
        this.UUIDService = UUIDService;
    }

    public ResponseEntity<?> handlePurchase(purchaseRequest pr) {
        if(pr.row() > row || pr.row() < 1 || pr.column() > column || pr.column() < 1) {
            return ResponseEntity.badRequest().body(new responseBodyError("The number of a row or a column is out of bounds!"));
        }

        seat seat = cinemaDTO.getAvailable_seats().get(((pr.row() - 1) * column) + pr.column() - 1);

        if(seat.isPurchased()) {
            return ResponseEntity.badRequest().body(new responseBodyError("The ticket has been already purchased!"));
        }

        String token = UUIDService.getRandomUUID();
        while(!UUIDService.storeSeat(token,seat)) {
            token = UUIDService.getRandomUUID();
        }

        seat.setPurchased(true);
        return ResponseEntity.ok(new purchaseResponse(token,seat));
    }

    public String handleGet() {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cinemaDTO);
        } catch (Exception e) {
            return "error";
        }
    }

    public ResponseEntity<?> handleReturn(String token){
        Map<String,seat> response =  new HashMap<>();
        seat seat = UUIDService.getSeat(token);
        if(seat == null){
            return ResponseEntity.badRequest().body(new responseBodyError("Wrong token!"));
        }
        UUIDService.removeSeat(token);
        response.put("returned_ticket",seat);
        seat.setPurchased(false);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> handleStats(String password) {
        if(password == null || !password.equals(adminPassword)) {
            return ResponseEntity.status(401).body(new responseBodyError("The password is wrong!"));
        }

        Map<String, Integer> response = new HashMap<>();
        response.put("current_income", UUIDService.getRevenue());
        response.put("number_of_available_seats", column * row - UUIDService.getSeatsBought());
        response.put("number_of_purchased_tickets", UUIDService.getSeatsBought());
        return ResponseEntity.ok(response);
    }

}

