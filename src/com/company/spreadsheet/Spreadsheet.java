package com.company.spreadsheet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spreadsheet {
    public Spreadsheet(int width, int height) {
        this.matrixCell = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            List<BaseCell> row = new ArrayList<>();
            for (int y = 0; y < height; y++) {
                row.add(new TextCell(""));
            }
            matrixCell.add(row);
        }
        this.isNumberPattern = Pattern.compile("\\d+(\\.\\d+)?");
        this.cellIdPattern = Pattern.compile("[A-z]\\d(:)?([A-z]\\d)?");
    }

    List<List<BaseCell>> matrixCell;
    Pattern isNumberPattern;
    Pattern cellIdPattern;

    boolean isNumber(String operator) {
        if (operator == null) {
            return false;
        }
        return this.isNumberPattern.matcher(operator).matches();
    }

    public Integer[] getCoordinatesFromCellId(String cellId) {
        Integer[] coordinates = new Integer[2];
        coordinates[1] = String.valueOf(cellId.charAt(0)).toLowerCase().charAt(0) - 97;
        coordinates[0] = Integer.parseInt(cellId.substring(1)) - 1;
        return coordinates;
    }

    public String getValueFromCellId(String cellId) {
        if (cellId.length() < 3) {
            Integer[] coordinates = this.getCoordinatesFromCellId(cellId);
            return this.matrixCell.get(coordinates[0]).get(coordinates[1]).getValue();
        } else {
            Integer[] firstCoord = this.getCoordinatesFromCellId(cellId.substring(0, 2));
            Integer[] secondCoord = this.getCoordinatesFromCellId(cellId.substring(3, 5));
            List<String> values = new ArrayList<>();

            if (firstCoord[0] > secondCoord[0]) {
                int temp = firstCoord[0];
                firstCoord[0] = secondCoord[0];
                secondCoord[0] = temp;
            }

            if (firstCoord[1] > secondCoord[1]) {
                int temp = firstCoord[1];
                firstCoord[1] = secondCoord[1];
                secondCoord[1] = temp;
            }

            if (firstCoord[0] < secondCoord[0] && firstCoord[1] < secondCoord[1]) {
                for (int x = firstCoord[0]; x <= secondCoord[0]; x++) {
                    for (int y = firstCoord[1]; y <= secondCoord[1]; y++) {
                        values.add(this.matrixCell.get(x).get(y).getValue());
                    }
                }
            }
            if (firstCoord[0] == secondCoord[0]) {
                for (int y = firstCoord[1]; y <= secondCoord[1]; y++) {
                    values.add(this.matrixCell.get(firstCoord[0]).get(y).getValue());
                }
            }
            if (firstCoord[1] == secondCoord[1]) {
                for (int x = firstCoord[0]; x <= secondCoord[0]; x++) {
                    values.add(this.matrixCell.get(x).get(firstCoord[1]).getValue());
                }
            }

            return String.join(";", values);
        }
    }

    public String mapCellToValue(String formula) {
        Matcher m = this.cellIdPattern.matcher(formula);

        while (m.find()) {
            String key = m.group(0);
            String value = this.getValueFromCellId(key);
            formula = formula.replace(key, value);
        }
        return formula;
    }

    String cellIdFromCoords(int x, int y) {
        return String.valueOf((char) (y + 96)) + String.valueOf(x + 1);
    }

    void notifyCellChanged(String cellId) {
        for (List<BaseCell> cells : this.matrixCell) {
            for (BaseCell cell : cells) {
                cell.updateCell(cellId);
            }
        }
    }

    public void updateCell(int x, int y, String value) {
        BaseCell cell;
        if (value.startsWith("=")) {
            cell = new FormulaCell(value.substring(1), this);
        } else if (this.isNumber(value)) {
            cell = new NumberCell(value);
        } else {
            cell = new TextCell(value);
        }
        this.matrixCell.get(y).set(x, cell);
        notifyCellChanged(this.cellIdFromCoords(x, y));
    }

    public void printMatrix() {
        System.out.println("------------".repeat(this.matrixCell.size()));
        for (int x = 0; x < this.matrixCell.size(); x++) {
            System.out.print(String.valueOf((char) (x + 97)));
            System.out.print(" ".repeat(50));
            System.out.print("|");
        }
        System.out.println("");
        System.out.println("------------------------------------------------------------------".repeat(this.matrixCell.size()));
        System.out.println("");
        for (List<BaseCell> cells : this.matrixCell) {
            for (BaseCell cell : cells) {
                String value = cell.getValue();
                int padding = 51 - value.length();

                if (cell instanceof FormulaCell) {
                    System.out.print(cell.getSpreadsheetValue() + "       " + cell.getValue());
                } else {
                    System.out.print(cell.getValue() + " ".repeat(padding));
                }

                System.out.print("|");
            }
            System.out.println("");
            System.out.println("------------------------------------------------------------------".repeat(this.matrixCell.size()));
        }
    }

    public List<List<BaseCell>> getMatrixCell() {
        return this.matrixCell;
    }
}
