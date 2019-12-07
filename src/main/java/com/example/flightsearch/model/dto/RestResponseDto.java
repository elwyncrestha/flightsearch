package com.example.flightsearch.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponseDto {

    private String message;
    private Object detail;

    public ResponseEntity<?> successModel(Object o) {
        RestResponseDto r = new RestResponseDto();
        r.setDetail(o);
        r.setMessage("SUCCESS");
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    public ResponseEntity<?> successModel(Object o, String message) {
        RestResponseDto r = new RestResponseDto();
        r.setDetail(o);
        r.setMessage(message);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    public ResponseEntity<?> failureModel(HttpStatus httpStatus, String message, Object o) {
        RestResponseDto r = new RestResponseDto();
        r.setDetail(o);
        r.setMessage(message);
        return new ResponseEntity<>(r, httpStatus);
    }
}
