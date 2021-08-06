package com.example.mathparser.model;

import com.example.mathparser.dto.CalculationDto;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "CALCULATION")
@Immutable
public class CalculationEntity {

    @Id
    private String id;

    private LocalDateTime dateTime;

    private String input;

    private String result;

    public CalculationEntity(String id, LocalDateTime dateTime, String input, String result) {
        this.id = id;
        this.dateTime = dateTime;
        this.input = input;
        this.result = result;
    }

    private CalculationEntity() {
        // JPA ONLY
    }

    public CalculationDto dto() {
        return new CalculationDto(id, dateTime, input, result);
    }
}
