package com.example.cinemarestfulapispring.businessLayer.DTOs;

import com.example.cinemarestfulapispring.persitenceLayer.cinema.seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class cinemaDTO  {
    private final int total_rows;
    private final int total_columns;
    private final List<seat> available_seats;

    @Autowired
    public cinemaDTO(@Qualifier("rows") int rows , @Qualifier("columns")int columns, List<seat> available_seats) {
        this.total_rows = rows;
        this.total_columns = columns;
        this.available_seats = available_seats;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<seat> getAvailable_seats() {
        return available_seats;
    }
}
