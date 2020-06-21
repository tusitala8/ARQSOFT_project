package com.company.spreadsheet;


public class NumberCell extends BaseCell {
    public NumberCell(String value) {
        this.value = value;
    }

    String value;

    @Override
    public String getValue() {
        return value;
    }
}
