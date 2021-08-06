package com.example.mathparser.service;

import java.math.BigDecimal;

@FunctionalInterface
interface Expression {

    BigDecimal evaluate();


}
