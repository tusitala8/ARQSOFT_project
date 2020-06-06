package com.company;

import com.company.calculator.Calculator;

import java.util.List;

class ArqSoft {

    public static void main(String []args){
        Calculator calc = new Calculator();
        List<Object> result = calc.calcular("1-2/3*(44+30)-4");
    }

}





