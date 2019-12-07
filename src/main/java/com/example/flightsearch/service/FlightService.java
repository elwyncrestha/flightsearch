package com.example.flightsearch.service;

import java.util.List;

import com.example.flightsearch.model.entity.Flight;

public interface FlightService {
    List<Flight> getFlights(Object search);
    List<Flight> saveFlights(List<Flight> flights);
}
