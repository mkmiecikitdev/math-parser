package com.example.mathparser.service;

import com.example.mathparser.dao.CalculationDataDao;
import com.example.mathparser.model.CalculationEntity;
import com.example.mathparser.dto.CalculationDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CalculationService {

    private final Calculator calculator;
    private final CalculationDataDao calculationDataDao;

    public CalculationService(Calculator calculator, CalculationDataDao calculationDataDao) {
        this.calculator = calculator;
        this.calculationDataDao = calculationDataDao;
    }

    public BigDecimal calculate(final String input) {
        final BigDecimal result = calculator.calculate(input);
        final CalculationEntity calculationEntity = createEntityFrom(input, result.toString());
        calculationDataDao.save(calculationEntity);
        return result;
    }

    public List<CalculationDto> findSortedByDate() {
        return calculationDataDao.findAll()
                .stream()
                .map(CalculationEntity::dto)
                .sorted(Comparator.comparing(CalculationDto::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    private CalculationEntity createEntityFrom(final String input, final String result) {
        return new CalculationEntity(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                input,
                result
        );
    }
}
