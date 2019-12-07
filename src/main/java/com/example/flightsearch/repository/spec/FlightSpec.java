package com.example.flightsearch.repository.spec;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.flightsearch.model.entity.Flight;

public class FlightSpec implements Specification<Flight> {

    private static final String FILTER_BY_FLIGHT_NUMBER = "flightNumber";
    private static final String FILTER_BY_ORIGIN = "origin";
    private static final String FILTER_BY_DESTINATION = "destination";
    private static final String FILTER_BY_DEPARTURE = "departure";
    private static final String FILTER_BY_ARRIVAL = "arrival";

    private final String property;
    private final String value;

    public FlightSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Flight> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_FLIGHT_NUMBER:
            case FILTER_BY_DESTINATION:
            case FILTER_BY_ORIGIN:
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(property)),
                    "%" + value.toLowerCase() + "%");

            case FILTER_BY_DEPARTURE:
            case FILTER_BY_ARRIVAL:
                try {
                    return criteriaBuilder.and(criteriaBuilder.between(root.get(property),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .parse(value + " 00:00:00"),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .parse(value + " 23:59:59")));
                } catch (ParseException e) {
                    return null;
                }

            default:
                return null;
        }
    }
}
