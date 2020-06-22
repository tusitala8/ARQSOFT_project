package com.company.CSVFile;

import com.company.spreadsheet.BaseCell;
import com.company.spreadsheet.Spreadsheet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveCSVFile {

    Spreadsheet spreadsheet;
    String path;
    String fileName;

    public SaveCSVFile(String path, String fileName, Spreadsheet spreadsheet) {

        this.path = path;
        this.fileName = fileName;
        this.spreadsheet = spreadsheet;

    }

    public void SaveCSV() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(path + fileName));
        List<List<BaseCell>> matrix = this.spreadsheet.getMatrixCell();

        for (List<BaseCell> cells : matrix) {
            for (BaseCell cell : cells) {
                writer.write(cell.getSpreadsheetValue() + ";");
            }
            writer.newLine();
        }

        writer.close();

    }
}
