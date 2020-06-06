package com.company.stack;

import com.company.calculator.Operator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OperatorStack {
    public OperatorStack() {
        this.openBracketStack = new ArrayList<>();
        this.stack = new ArrayList<>();
    }

    List<Integer> openBracketStack;
    public List<Operator> stack;

    public List<Operator> push(Operator operator) {
        List<Operator> result = new ArrayList<>();

        if (this.stack.isEmpty()) {
            System.out.println("push " + operator.getClass().getName());
            this.stack.add(operator);
            return result;
        }

        Operator lastOperator = this.stack.get(this.stack.size() - 1);

        if (lastOperator.getPrecedence() >= operator.getPrecedence() || (!this.openBracketStack.isEmpty() && this.stack.size() - 1 == this.openBracketStack.get(this.openBracketStack.size() - 1))) {

            System.out.println("push " + operator.getClass().getName());
            this.stack.add(operator);
            return result;
        }

        if (lastOperator.getPrecedence() < operator.getPrecedence()) {
            System.out.println("pops " + lastOperator.getClass().getName());
            result.add(lastOperator);
            this.stack.remove(this.stack.size() - 1);
            result.addAll(this.push(operator));
            return result;
        }

        return result;
    }

    public void addOpenBracket() {
        this.openBracketStack.add(this.stack.size() - 1);
    }

    public List<Operator> addCloseBracket() {
        List<Operator> result;
        Integer lastOpenBracket = this.openBracketStack.get(this.openBracketStack.size() - 1);
        result = this.stack.subList(lastOpenBracket, this.stack.size() - 1);
        Collections.reverse(result);
        this.openBracketStack.remove(this.openBracketStack.size() - 1);
        return result;
    }

    public List<Operator> popAll() {
        Collections.reverse(this.stack);

        return this.stack;
    }
}
