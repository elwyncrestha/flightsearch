package com.example.flightsearch.repository.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.data.jpa.domain.Specification;

import com.example.flightsearch.model.entity.Flight;

public class FlightSpecBuilder {

    private final Map<String, String> params;

    public FlightSpecBuilder(Map<String, String> params) {
        this.params = params;
    }

    public Specification<Flight> build() {
        if (MapUtils.isEmpty(params)) {
            return null;
        }

        final List<String> properties = new ArrayList<>(params.keySet());

        final String firstProperty = properties.get(0);

        Specification<Flight> spec = new FlightSpec(properties.get(0),
            params.get(firstProperty));

        for (int i = 1; i < properties.size(); i++) {
            final String property = properties.get(i);
            spec = Specification.where(spec)
                .and(new FlightSpec(property, params.get(property)));
        }

        return spec;

    }


}
