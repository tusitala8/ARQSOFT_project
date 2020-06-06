package com.company.calculator;

class Division extends Operator {
    public int getPrecedence() {
        return 1;
    }

    double operate(double number1, double number2) {
        return number1 / number2;
    }
}