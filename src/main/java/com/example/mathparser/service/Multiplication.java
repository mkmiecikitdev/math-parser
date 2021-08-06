package com.example.mathparser.service;

import java.math.BigDecimal;

class Multiplication implements Expression {
    private final Expression e1;
    private final Expression e2;

    private Multiplication(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    static Multiplication of(final Expression e1, final Expression e2) {
        return new Multiplication(e1, e2);
    }

    @Override
    public BigDecimal evaluate() {
        return e1.evaluate().multiply(e2.evaluate());
    }
}
