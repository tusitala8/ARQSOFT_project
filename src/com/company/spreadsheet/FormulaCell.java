package com.company.spreadsheet;

import com.company.calculator.Calculator;
import com.company.calculator.InvalidOperator;
import com.company.function.PreProcessor;

public class FormulaCell extends BaseCell {

    String formula;
    String resultat;
    Spreadsheet sheet;
    PreProcessor preProc;
    Calculator calc;

    public FormulaCell(String formula, Spreadsheet sheet) {
        this.formula = formula;
        this.sheet = sheet;
        this.preProc = new PreProcessor();
        this.calc = new Calculator();
    }

    public String calculate() {
        try {
            return String.valueOf(calc.calculate(preProc.toOperations(sheet.mapCellToValue(formula))));
        } catch (InvalidOperator e) {
            return "invalid operation";
        }
    }

    @Override
    public String getValue() {
        if (this.resultat == null) {
            this.resultat = this.calculate();
        }
        return this.calculate();
    }

    @Override
    public String getSpreadsheetValue() {
        return "=" + formula.replaceAll(";", ",");
    }

}