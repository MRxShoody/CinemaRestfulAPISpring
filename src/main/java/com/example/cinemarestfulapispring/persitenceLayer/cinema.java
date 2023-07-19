package com.example.cinemarestfulapispring.persitenceLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class cinema {

    private final List<seat> seats = new ArrayList<>();

    @Autowired
    public cinema(@Qualifier("rows") int rows, @Qualifier("columns")int columns) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                if (i <= 4) {
                    seats.add(new seat(i, j, false, 10));
                } else {
                    seats.add(new seat(i, j, false, 8));
                }
            }
        }
    }

    @Bean
    public List<seat> getSeats() {
        return seats;
    }

    public static class seat{

        private final int row;
        private final int column;
        @JsonIgnore
        private boolean purchased;
        private int price;

        public seat(int row, int column, boolean purchased, int price) {
            this.row = row;
            this.column = column;
            this.purchased = purchased;
            this.price = price;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        public int getPrice() {
            return price;
        }

        public boolean isPurchased() {
            return purchased;
        }

        public void setPurchased(boolean b) {
            purchased = b;
        }
    }


}
