package com.example.flightsearch.model.entity;

import java.util.Date;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Flight extends BaseEntity<Long> {
    private String flightNumber;
    private String carrier;
    private String origin;
    private Date departure;
    private String destination;
    private Date arrival;
    private String aircraft;
    private Double distance;
    private String travelTime;
    private String status;
}
