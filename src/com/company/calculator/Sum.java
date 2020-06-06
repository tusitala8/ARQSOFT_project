package com.company.calculator;

class Sum extends Operator {
    public int getPrecedence() {
        return 2;
    }

    double operate(double number1, double number2) {
        return number1 + number2;
    }
}
