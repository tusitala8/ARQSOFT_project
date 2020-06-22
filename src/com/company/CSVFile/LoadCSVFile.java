package com.company.CSVFile;

import com.company.spreadsheet.Spreadsheet;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LoadCSVFile {

    Spreadsheet spreadsheet;
    String path;
    String fileName;

    public LoadCSVFile(String path, String fileName, Spreadsheet spreadsheet) {

        this.path = path;
        this.fileName = fileName;
        this.spreadsheet = spreadsheet;

    }

    public Spreadsheet LoadCSV() throws IOException {

        File myObj = new File(this.path + this.fileName);
        Scanner myReader = new Scanner(myObj);

        int j = 0;

        while (myReader.hasNextLine()) {

            String cell = myReader.nextLine();
            String[] cells = cell.split(";");

            for (int i = 0; i < cells.length; i++) {

                if (cells[i].contains("=")) {
                    spreadsheet.updateCell(i, j, cells[i].replaceAll(",", ";"));
                } else {
                    spreadsheet.updateCell(i, j, cells[i]);
                }

            }

            j++;

        }

        spreadsheet.printMatrix();

        return spreadsheet;

    }

}
