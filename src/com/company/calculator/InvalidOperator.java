package com.company.calculator;

public class InvalidOperator extends Exception {
    public InvalidOperator(String operator) {
        super("Invalid operator: " + operator);
    }
}