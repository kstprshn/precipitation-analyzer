package com.example.project3.util;

import lombok.Data;

@Data
public class MeasurementErrorResponse {

    private String message;
    private long timestamp;

    public MeasurementErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
