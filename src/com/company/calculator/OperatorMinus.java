package com.company.calculator;

class OperatorMinus implements Operator{
    public int getPrecedence() {
        return 2;
    }

    public String getIdentifier() {
        return "-";
    }

    public double calculate(double num1, double num2) {
        return num1 - num2;
    }
}