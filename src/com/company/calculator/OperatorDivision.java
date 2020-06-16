package com.company.calculator;

class OperatorDivision implements Operator{
    public int getPrecedence() {
        return 3;
    }

    public String getIdentifier() {
        return "/";
    }

    public double calculate(double num1, double num2) {
        return num1 / num2;
    }
}