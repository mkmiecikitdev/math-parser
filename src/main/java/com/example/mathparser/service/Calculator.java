package com.example.mathparser.service;

import java.math.BigDecimal;

class Calculator {

    private final TokenListCreator tokenListCreator;
    private final ExpressionProcessor expressionProcessor;

    Calculator(TokenListCreator tokenListCreator, ExpressionProcessor expressionProcessor) {
        this.tokenListCreator = tokenListCreator;
        this.expressionProcessor = expressionProcessor;
    }

    BigDecimal calculate(final String input) {
        final NonEmptyTokenList tokenList = tokenListCreator.create(input);
        final Expression expression = expressionProcessor.process(tokenList);
        return expression.evaluate();
    }
}
