package com.example.cinemarestfulapispring.presentationLayer;

import com.example.cinemarestfulapispring.businessLayer.records.purchaseRequest;
import com.example.cinemarestfulapispring.businessLayer.records.returnRequest;
import com.example.cinemarestfulapispring.businessLayer.services.cinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class controller {
    private final cinemaService cs;

    @Autowired
    controller(cinemaService cs) {
        this.cs = cs;
    }

    @GetMapping("/seats")
    public String get() {
        return cs.handleGet();
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(required = false) String password) {
        return cs.handleStats(password);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody purchaseRequest pr) {
        return cs.handlePurchase(pr);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody returnRequest rr) {
        return cs.handleReturn(rr.token());
    }


}
