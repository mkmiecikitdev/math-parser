package com.example.mathparser.controller;

import com.example.mathparser.dto.CalculationDto;
import com.example.mathparser.dto.ErrorDto;
import com.example.mathparser.dto.InputDto;
import com.example.mathparser.dto.ResultDto;
import com.example.mathparser.service.CalculationService;
import com.example.mathparser.exceptions.DivisionByZeroException;
import com.example.mathparser.exceptions.InvalidEquationFormatException;
import com.example.mathparser.exceptions.UnknownOperationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
class RestApiController {

    private final CalculationService calculationService;

    RestApiController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/calculate")
    ResultDto calculate(@RequestBody InputDto inputDto) {
        final BigDecimal calculate = calculationService.calculate(inputDto.getInput());
        return new ResultDto(calculate.toString());
    }

    @GetMapping("/history")
    List<CalculationDto> history() {
        return calculationService.findSortedByDate();
    }

    @ExceptionHandler(value = DivisionByZeroException.class)
    ResponseEntity<ErrorDto> handle(DivisionByZeroException e) {
        return ResponseEntity.badRequest().body(new ErrorDto("Cannot divide by 0."));
    }

    @ExceptionHandler(value = InvalidEquationFormatException.class)
    ResponseEntity<ErrorDto> handle(InvalidEquationFormatException e) {
        return ResponseEntity.badRequest().body(new ErrorDto("Invalid equation format."));
    }

    @ExceptionHandler(value = UnknownOperationException.class)
    ResponseEntity<ErrorDto> handle(UnknownOperationException e) {
        return ResponseEntity.badRequest().body(new ErrorDto("Unknown operation."));
    }
}
