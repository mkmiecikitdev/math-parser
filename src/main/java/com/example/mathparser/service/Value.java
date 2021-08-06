package com.example.mathparser.service;

import com.example.mathparser.exceptions.InvalidEquationFormatException;

import java.math.BigDecimal;


class Value implements Expression {

    private final BigDecimal value;

    private Value(BigDecimal value) {
        this.value = value;
    }

    static Value of(String input) {
        return new Value(bigDecimalFromString(input));
    }

    private static BigDecimal bigDecimalFromString(final String input) {
        try {
            return new BigDecimal(input);
        } catch (Exception e) {
            throw new InvalidEquationFormatException();
        }
    }

    @Override
    public BigDecimal evaluate() {
        return value;
    }
}
