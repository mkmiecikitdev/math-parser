package com.example.mathparser.dao;

import com.example.mathparser.model.CalculationEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryCalculationDataDao implements CalculationDataDao {

    private final Map<String, CalculationEntity> map = new HashMap<>();

    @Override
    public CalculationEntity save(CalculationEntity calculationEntity) {
        map.put(calculationEntity.dto().getId(), calculationEntity);
        return calculationEntity;
    }

    @Override
    public Set<CalculationEntity> findAll() {
        return new HashSet<>(map.values());
    }
}
