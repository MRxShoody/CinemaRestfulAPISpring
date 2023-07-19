package com.example.cinemarestfulapispring.businessLayer.records;

import com.example.cinemarestfulapispring.persitenceLayer.cinema.seat;

public record purchaseResponse(String token, seat ticket) {
}
