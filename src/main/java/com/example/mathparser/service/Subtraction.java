package com.example.mathparser.service;

import java.math.BigDecimal;

class Subtraction implements Expression {
    private final Expression e1;
    private final Expression e2;

    private Subtraction(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    static Subtraction of(final Expression e1, final Expression e2) {
        return new Subtraction(e1, e2);
    }

    @Override
    public BigDecimal evaluate() {
        return e1.evaluate().subtract(e2.evaluate());
    }
}
