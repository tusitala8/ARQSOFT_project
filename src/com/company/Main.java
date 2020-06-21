package com.company;

import com.company.calculator.Calculator;
import com.company.calculator.InvalidOperator;
import com.company.function.PreProcessor;
import com.company.spreadsheet.Spreadsheet;


class Arqsoft {

    public static void main(String []args){
        Spreadsheet spr = new Spreadsheet(5, 5);
        spr.updateCell(0,0, "2");
        spr.updateCell(1,0, "3");
        spr.updateCell(0,1, "4");
        spr.updateCell(1,1, "5");
        spr.updateCell(2,0,"=AVERAGE(A1:B1;3;3;1)");
        spr.printMatrix();
    }

}





