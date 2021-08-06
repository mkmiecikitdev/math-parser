package com.example.mathparser.service;

import java.util.ArrayList;
import java.util.List;

class ExpressionProcessor {

    Expression process(final NonEmptyTokenList tokenList) {
        List<Token> tokens = tokenList.asList();

        Expression expression = Value.of(tokens.get(0).getValue());

        while (tokens.size() > 1) {
            final Token operation = tokens.get(1);

            if (operation.getType() == Token.Type.MULTIPLICATION) {
                expression = Multiplication.of(expression, Value.of(tokens.get(2).getValue()));
                tokens = mergeAfterPrimaryOperation(tokens, expression);
            } else if (operation.getType() == Token.Type.DIVISION) {
                expression = Division.of(expression, Value.of(tokens.get(2).getValue()));
                tokens = mergeAfterPrimaryOperation(tokens, expression);
            } else if (operation.getType() == Token.Type.ADDITION) {
                return Addition.of(expression, process(NonEmptyTokenList.of(tokens.subList(2, tokens.size()))));
            } else {
                return Subtraction.of(expression, process(NonEmptyTokenList.of(tokens.subList(2, tokens.size()))));
            }
        }

        return expression;
    }

    private List<Token> mergeAfterPrimaryOperation(List<Token> tokens, Expression expression) {
        final String first = expression.evaluate().toString();
        final List<Token> subList = tokens.subList(3, tokens.size());
        final ArrayList<Token> newList = new ArrayList<>();
        newList.add(Token.asValue(first));
        newList.addAll(subList);
        return newList;
    }

}
