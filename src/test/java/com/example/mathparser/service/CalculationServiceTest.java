package com.example.mathparser.service;

import com.example.mathparser.exceptions.DivisionByZeroException;
import com.example.mathparser.exceptions.InvalidEquationFormatException;
import com.example.mathparser.exceptions.UnknownOperationException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CalculationServiceTest {

    private final CalculationService calculationService = new CalculationModuleConfiguration().createCalculatorService();

    @Test
    public void shouldDoSimpleAddition() {
        final String input = "5+10";

        final BigDecimal result = calculationService.calculate(input);

        assertThat(result.toString()).isEqualTo("15");
    }

    @Test
    public void shouldDoSimpleSubtraction() {
        final String input = "5-10";

        final BigDecimal result = calculationService.calculate(input);

        assertThat(result.toString()).isEqualTo("-5");
    }

    @Test
    public void shouldDoSimpleMultiplication() {
        final String input = "5*10";

        final BigDecimal result = calculationService.calculate(input);

        assertThat(result.toString()).isEqualTo("50");
    }

    @Test
    public void shouldDoSimpleDivision() {
        final String input = "5/10";

        final BigDecimal result = calculationService.calculate(input);

        assertThat(result.toString()).isEqualTo("0.50");
    }

    @Test
    public void shouldDoComplicatedOperation() {
        final String input = "54*7+4+6*9*3-4-4*2.5*4/2-50";

        final BigDecimal result = calculationService.calculate(input);

        assertThat(result.toString()).isEqualTo("510.00");
    }

    @Test
    public void shouldThrowInvalidEquationFormatException() {
        final String invalidInput1 = "*3";
        final String invalidInput2 = "5+";
        final String invalidInput3 = "5+3-";
        final String invalidInput4 = "3-+45*";
        final String invalidInput5 = "*/-";
        final String invalidInput6 = ".+.";

        assertAll(
                () -> assertThatThrownBy(() -> calculationService.calculate(invalidInput1)).isInstanceOf(InvalidEquationFormatException.class),
                () -> assertThatThrownBy(() -> calculationService.calculate(invalidInput2)).isInstanceOf(InvalidEquationFormatException.class),
                () -> assertThatThrownBy(() -> calculationService.calculate(invalidInput3)).isInstanceOf(InvalidEquationFormatException.class),
                () -> assertThatThrownBy(() -> calculationService.calculate(invalidInput4)).isInstanceOf(InvalidEquationFormatException.class),
                () -> assertThatThrownBy(() -> calculationService.calculate(invalidInput5)).isInstanceOf(InvalidEquationFormatException.class),
                () -> assertThatThrownBy(() -> calculationService.calculate(invalidInput6)).isInstanceOf(InvalidEquationFormatException.class)
        );
    }

    @Test
    public void shouldThrowUnknownOperationException() {
        final String invalidInput1 = "35=42";
        final String invalidInput2 = "4;2";
        final String invalidInput3 = "6^7";
        final String invalidInput4 = "8]2";
        final String invalidInput5 = "12%2";

        assertAll(
                () -> assertThatThrownBy(() -> calculationService.calculate(invalidInput1)).isInstanceOf(UnknownOperationException.class),
                () -> assertThatThrownBy(() -> calculationService.calculate(invalidInput2)).isInstanceOf(UnknownOperationException.class),
                () -> assertThatThrownBy(() -> calculationService.calculate(invalidInput3)).isInstanceOf(UnknownOperationException.class),
                () -> assertThatThrownBy(() -> calculationService.calculate(invalidInput4)).isInstanceOf(UnknownOperationException.class),
                () -> assertThatThrownBy(() -> calculationService.calculate(invalidInput5)).isInstanceOf(UnknownOperationException.class)
        );
    }

    @Test
    public void shouldThrowDivisionByZeroException() {
        final String invalidInput = "54+5/0";

        assertThatThrownBy(() -> calculationService.calculate(invalidInput)).isInstanceOf(DivisionByZeroException.class);
    }
}
