package com.example.mathparser.service;

import com.example.mathparser.dao.CalculationDataDao;
import com.example.mathparser.dao.InMemoryCalculationDataDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CalculationModuleConfiguration {

    @Bean
    CalculationService createCalculatorService(CalculationDataDao calculationDataDao) {
        final Calculator calculator = new Calculator(new TokenListCreator(), new ExpressionProcessor());
        return new CalculationService(calculator, calculationDataDao);
    }

    CalculationService createCalculatorService() {
        return createCalculatorService(new InMemoryCalculationDataDao());
    }
}
