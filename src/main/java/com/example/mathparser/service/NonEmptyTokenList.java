package com.example.mathparser.service;

import com.example.mathparser.exceptions.InvalidEquationFormatException;

import java.util.ArrayList;
import java.util.List;

class NonEmptyTokenList {

    private final List<Token> list;

    private NonEmptyTokenList(List<Token> list) {
        this.list = list;
    }

    static NonEmptyTokenList of(List<Token> list) {
        if (isListSizeEven(list)) {
            throw new InvalidEquationFormatException();
        }

        boolean even = false;

        for (Token token : list) {
            validateToken(token, even);
            even = !even;
        }

        return new NonEmptyTokenList(list);
    }

    List<Token> asList() {
        return new ArrayList<>(list); // to prevent from mutation
    }

    private static boolean isListSizeEven(final List<Token> list) {
        return list.size() % 2 == 0;
    }

    private static void validateToken(final Token token, boolean even) {
        if (!even && token.getType() != Token.Type.VALUE) {
            throw new InvalidEquationFormatException();
        }

        if (even && token.getType() == Token.Type.VALUE) {
            throw new InvalidEquationFormatException();
        }
    }
}
