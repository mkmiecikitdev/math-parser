package com.example.mathparser.service;

import com.example.mathparser.exceptions.DivisionByZeroException;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Division implements Expression {
    private final Expression e1;
    private final Expression e2;

    private Division(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    static Division of(final Expression e1, final Expression e2) {
        if (e2.evaluate().compareTo(BigDecimal.ZERO) == 0) {
            throw new DivisionByZeroException();
        }

        return new Division(e1, e2);
    }

    @Override
    public BigDecimal evaluate() {
        return e1.evaluate().divide(e2.evaluate(), 2, RoundingMode.HALF_UP);
    }
}
