package com.example.flightsearch.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.flightsearch.model.entity.Flight;
import com.example.flightsearch.repository.FlightRepository;
import com.example.flightsearch.repository.spec.FlightSpecBuilder;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> getFlights(Object search) {
        final ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> s = objectMapper.convertValue(search, Map.class);
        final FlightSpecBuilder builder = new FlightSpecBuilder(s);
        final Specification<Flight> spec = builder.build();
        return flightRepository.findAll(spec);
    }

    @Override
    public List<Flight> saveFlights(List<Flight> flights) {
        return flightRepository.saveAll(flights);
    }
}
