package com.company.calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class OperatorStack {
    public OperatorStack(List<Operator> operatorsType) {
        this.operators = new ArrayDeque<String>();
        this.operatorsType = operatorsType;
    }
    Deque<String> operators;
    List<Operator> operatorsType;

    public List<String> push(String operator) throws InvalidOperator{
        List<String> result = new ArrayList<String>();

        if(this.operators.isEmpty()) {
            this.operators.push(operator);  //Explicar
            return result;
        }

        if(operator.equals("(")) {
            this.operators.push(operator);
            return result;
        }

        String lastOperator = this.operators.getFirst();

        if(operator.equals(")") && lastOperator.equals("(")) {
            this.operators.pop();
            return result;
        }

        if(operator.equals(")")) {
            result.add(this.operators.pop());
            result.addAll(this.push(operator));
            return result;
        }

        if(this.getPrecedence(operator) <= this.getPrecedence(lastOperator)) {
            result.add(this.operators.pop());
            result.addAll(this.push(operator));
            return result;
        }

        if(this.getPrecedence(operator) > this.getPrecedence(lastOperator)) {
            this.operators.push(operator);
            return result;
        }

        return result;

    }

    public int getPrecedence(String operatorId) throws InvalidOperator {
        if(operatorId.equals("(")) { //Explicar
            return 1;
        }
        Operator operator = this.findOperator(operatorId);
        if(operator == null) {
            throw new InvalidOperator(operatorId);
        } else {
            return operator.getPrecedence();
        }
    }

    Operator findOperator(String identifier) {
        return this.operatorsType.stream()
                .filter(func -> func.getIdentifier().equals(identifier))
                .findAny()
                .orElse(null);
    }

    public List<String> popAll() {
        List<String> result = new ArrayList<String>();
        while(!operators.isEmpty()) {
            result.add(operators.pop());
        }

        return result;
    }
}