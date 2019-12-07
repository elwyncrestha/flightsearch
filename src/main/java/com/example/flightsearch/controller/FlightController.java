package com.example.flightsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightsearch.model.dto.RestResponseDto;
import com.example.flightsearch.model.entity.Flight;
import com.example.flightsearch.service.FlightService;

@RestController
@RequestMapping(FlightController.API)
public class FlightController {

    static final String API = "/v1/flight";
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<?> saveFlights(@RequestBody List<Flight> flights) {
        return new RestResponseDto().successModel(flightService.saveFlights(flights));
    }

    @PostMapping("/list")
    public ResponseEntity<?> getFlights(@RequestBody Object search) {
        return new RestResponseDto().successModel(flightService.getFlights(search));
    }

}
