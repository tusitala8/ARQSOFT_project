package com.company.spreadsheet;

public class TextCell extends BaseCell {
    public TextCell(String value) {
        this.value = value;
    }

    String value;

    @Override
    public String getValue() {
        return value;
    }
}
