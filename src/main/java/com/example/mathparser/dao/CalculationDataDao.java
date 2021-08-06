package com.example.mathparser.dao;

import com.example.mathparser.model.CalculationEntity;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface CalculationDataDao extends Repository<CalculationEntity, String> {

    CalculationEntity save(final CalculationEntity calculationEntity);

    Set<CalculationEntity> findAll();

}
