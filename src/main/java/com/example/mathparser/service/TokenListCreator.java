package com.example.mathparser.service;

import com.example.mathparser.exceptions.InvalidEquationFormatException;
import com.example.mathparser.exceptions.UnknownOperationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class TokenListCreator {

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char STAR = '*';
    private static final char SLASH = '/';

    private static final Set<Character> DIGITS = Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.');
    private static final Set<Character> OPERATION_CHARS = Set.of(PLUS, MINUS, STAR, SLASH);

    NonEmptyTokenList create(final String input) {
        final StringBuilder sb = new StringBuilder();
        final List<Token> tokens = new ArrayList<>();

        for (char c : input.toCharArray()) {
            if (OPERATION_CHARS.contains(c)) {
                if (isStringBuilderNotEmpty(sb)) {
                    tokens.add(Token.asValue(sb.toString()));
                    sb.setLength(0);
                }

                tokens.add(resolveTokenByChar(c));
            } else if (DIGITS.contains(c)) {
                sb.append(c);
            } else {
                throw new UnknownOperationException();
            }
        }

        if (isStringBuilderNotEmpty(sb)) {
            tokens.add(Token.asValue(sb.toString()));
        }

        if (tokens.isEmpty()) {
            throw new InvalidEquationFormatException();
        }

        return NonEmptyTokenList.of(tokens);
    }

    private Token resolveTokenByChar(char character) {
        switch (character) {
            case PLUS:
                return Token.ADDITION;
            case MINUS:
                return Token.SUBTRACTION;
            case STAR:
                return Token.MULTIPLICATION;
            case SLASH:
                return Token.DIVISION;
        }

        throw new UnknownOperationException();
    }

    private boolean isStringBuilderNotEmpty(final StringBuilder sb) {
        return !(sb.length() == 0);
    }
}
