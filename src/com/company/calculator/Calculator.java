package com.company.calculator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class Calculator {

    Pattern pattern;
    Pattern isNumberPattern;
    List<Operator> operators;

    public Calculator() {
        this.operators = new ArrayList<Operator>();
        this.operators.add(new OperatorSum());
        this.operators.add(new OperatorMinus());
        this.operators.add(new OperatorDivision());
        this.operators.add(new OperatorMultiplication());
        this.operators.add(new OperatorMax());
        this.operators.add(new OperatorMin());
        this.pattern = Pattern.compile(this.getTokensRegex());
        this.isNumberPattern = Pattern.compile(this.numberRegex());
    }

    String numberRegex() { //regex per detectar numeros
        return "\\d+(\\.\\d+)?";
    }

    String getTokensRegex() { //crea una expresio regex
        List<String> regex = this.operators.stream()
                .map(Operator::getIdentifier)
                .map(n -> this.scopeRegexToken(n))
                .collect(toList());  //array de simbolets
        regex.add(this.numberRegex());
        regex.add("\\(");
        regex.add("\\)");
        return String.join("|", regex);
    }

    String scopeRegexToken(String token) {
        String[] regexCharacters = {"+","-","*","/"};
        if(Arrays.asList(regexCharacters).contains(token)) {
            return "\\".concat(token);
        }
        return token;
    }

    public double calculate(String formula) throws InvalidOperator{
        List<String> formulatrossets = this.parse(formula);
        List<String> formulaAmbPostFix = this.toPostfix(formulatrossets);
        double resultatFormula = this.operatePostfix(formulaAmbPostFix);
        return resultatFormula;
    }

    List<String> parse(String formula) {
        List<String> result = new ArrayList<String>();

        Matcher m = this.pattern.matcher(formula);

        while(m.find()) {
            result.add(m.group(0));
        }

        return result;
    }

    boolean isNumber(String operator) {
        if (operator == null) {
            return false;
        }
        return this.isNumberPattern.matcher(operator).matches();
    }

    List<String> toPostfix(List<String> tokenizedFormula) throws InvalidOperator{
        OperatorStack stack = new OperatorStack(this.operators);
        List<String> cua = new ArrayList<String>();

        for(String token: tokenizedFormula) {
            if(this.isNumber(token)) {
                cua.add(token);
            } else {
                List<String> operacionsExpulsadesDelPush = stack.push(token);
                cua.addAll(operacionsExpulsadesDelPush);
            }
        }

        cua.addAll(stack.popAll());
        return cua;
    }

    double operatePostfix(List<String> postfixFormula) throws InvalidOperator{
        Deque<Double> numbers = new ArrayDeque<Double>();

        for(String trosDeFormula: postfixFormula) {
            if(this.isNumber(trosDeFormula)) {
                numbers.push(Double.parseDouble(trosDeFormula));
            } else {
                String simbolet=trosDeFormula;
                Double num2 = numbers.pop();
                Double num1 = numbers.pop();
                Double resultatOperacio=this.operate(num1, num2, simbolet);
                numbers.push(resultatOperacio);
            }
        }

        return numbers.pop();
    }

    double operate(Double num1, Double num2, String operatorId) throws InvalidOperator {
        Operator operator = this.findOperator(operatorId);
        if(operator == null) {
            throw new InvalidOperator(operatorId);
        } else {
            return operator.calculate(num1, num2);
        }
    }

    Operator findOperator(String identifier) {
        return this.operators.stream()
                .filter(func -> func.getIdentifier().equals(identifier))
                .findAny()
                .orElse(null);
    }
}