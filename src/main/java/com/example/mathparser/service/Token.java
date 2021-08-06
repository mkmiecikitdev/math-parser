package com.example.mathparser.service;

class Token {

    public static final Token ADDITION = new Token(Type.ADDITION, "+");
    public static final Token SUBTRACTION = new Token(Type.SUBTRACTION, "-");
    public static final Token MULTIPLICATION = new Token(Type.MULTIPLICATION, "*");
    public static final Token DIVISION = new Token(Type.DIVISION, "/");

    private final Type type;
    private final String value;

    private Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    static Token asValue(String value) {
        return new Token(Type.VALUE, value);
    }

    Type getType() {
        return type;
    }

    String getValue() {
        return value;
    }

    enum Type {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        VALUE
    }
}
