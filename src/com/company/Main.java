package com.company;

import com.company.calculator.Calculator;
import com.company.calculator.InvalidOperator;
import com.company.function.PreProcessor;


class Arqsoft {

    public static void main(String []args){
       try {
            Calculator calc = new Calculator();
            System.out.println(calc.calculate("4*(20min30)/2-3"));

            PreProcessor preProc = new PreProcessor();
            System.out.println(calc.calculate(preProc.toOperations("3+1*((SUM(2;5;AVERAGE(6;8);1;27)/4)+(6-8))")));
        } catch (InvalidOperator e) {
            System.out.println("invalid operator");
        }

    }

}





