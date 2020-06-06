package com.company.calculator;

import com.company.stack.OperatorStack;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public Calculator() {
        String regex = "\\d+|\\+|\\*|/|\\(|\\)|-";
        this.pattern = Pattern.compile(regex);
    }

    public List<Object> calcular(String formula) {
        return this.order(this.parse(formula));
    }


    public List<Object> order(List<String> parsedFormula) {
        OperatorStack stack = new OperatorStack();
        List<Object> result = new ArrayList<>();

        parsedFormula.forEach((elem) -> {
            switch (elem) {
                case "+":
                    result.addAll(stack.push(new Sum()));
                    break;
                case "-":
                    result.addAll(stack.push(new Minus()));
                    break;
                case "*":
                    result.addAll(stack.push(new Multiplication()));
                    break;
                case "/":
                    result.addAll(stack.push(new Division()));
                    break;
                case "(":
                    stack.addOpenBracket();
                    break;
                case ")":
                    result.addAll(stack.addCloseBracket());
                    break;
                default:
                    result.add(Double.parseDouble(elem));
            }

            result.addAll(stack.popAll());

        });

        return result;
    }


    List<String> parse(String formula) {

        List<String> result = new ArrayList<>();

        Matcher m = pattern.matcher(formula);

        while (m.find()) {
            result.add(m.group(0));
        }

        return result;
    }


    Pattern pattern;
}
