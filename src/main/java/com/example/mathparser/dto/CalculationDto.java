package com.example.mathparser.dto;

import java.time.LocalDateTime;

public class CalculationDto {

    private final String id;

    private final LocalDateTime dateTime;

    private final String operation;

    private final String result;

    public CalculationDto(String id, LocalDateTime dateTime, String operation, String result) {
        this.id = id;
        this.dateTime = dateTime;
        this.operation = operation;
        this.result = result;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getOperation() {
        return operation;
    }

    public String getResult() {
        return result;
    }

    public String getId() {
        return id;
    }
}
