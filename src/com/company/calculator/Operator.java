package com.company.calculator;

public abstract class Operator {
    public abstract int getPrecedence();
    abstract double operate(double number1, double number2);
}







