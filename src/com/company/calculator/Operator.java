package com.company.calculator;

interface Operator {
    int getPrecedence();
    String getIdentifier();
    double calculate(double num1, double num2);
}







