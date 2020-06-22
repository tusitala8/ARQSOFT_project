package com.company.spreadsheet;

import com.company.calculator.Calculator;
import com.company.calculator.InvalidOperator;
import com.company.function.PreProcessor;

import java.util.List;

public class FormulaCell extends BaseCell {
    public FormulaCell(String formula, Spreadsheet sheet) {
        this.formula = formula;
        this.sheet = sheet;
        this.preProc = new PreProcessor();
        this.calc = new Calculator();
    }

    String formula;
    String resultat;
    Spreadsheet sheet;
    PreProcessor preProc;
    Calculator calc;

    public String calculate() {
        try {
            return String.valueOf(calc.calculate(preProc.toOperations(sheet.mapCellToValue(formula))));
        } catch (InvalidOperator e) {
            return "invalid operation";
        }
    }

    public void update(String cellId) {
        if (formula.contains(cellId)) {
            this.resultat = this.calculate();
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