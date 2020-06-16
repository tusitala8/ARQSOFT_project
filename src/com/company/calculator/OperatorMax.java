package com.company.calculator;

class OperatorMax implements Operator{
    public int getPrecedence() {
        return 4;
    }

    public String getIdentifier() {
        return "max";
    }

    public double calculate(double num1, double num2) {
        if(num1>num2) {
            return num1;
        } else {
            return num2;
        }
    }
}
