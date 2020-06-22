package com.company.Menu;

import com.company.CSVFile.LoadCSVFile;
import com.company.CSVFile.SaveCSVFile;
import com.company.spreadsheet.Spreadsheet;

import java.awt.*;
import java.util.Scanner;

public class Menu {

    private boolean salir = false;

    public Menu() {
    }

    Scanner sn = new Scanner(System.in);
    Spreadsheet spr = new Spreadsheet(26, 26);

    public void showMenu() {

        try {

            while (!this.salir) {

                System.out.println("1. Load file");
                System.out.println("2. Add new cell");
                System.out.println("3. Save file");
                System.out.println("4. Exit");

                System.out.println("Select option:");
                int opcion = sn.nextInt();

                switch (opcion) {

                    case 1 -> {

                        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
                        dialog.setMode(FileDialog.LOAD);
                        dialog.setVisible(true);
                        String path = dialog.getDirectory();
                        String name = dialog.getFile();

                        System.out.println("Loading file...");

                        LoadCSVFile loadCSVFile = new LoadCSVFile(path, name, spr);
                        spr = loadCSVFile.LoadCSV();

                    }

                    case 2 -> {

                        System.out.println("Adding new cell...");

                        System.out.println("Add cell's coordinate:");
                        Scanner myCoordinate = new Scanner(System.in);  // Create a Scanner object
                        String cellId = myCoordinate.nextLine();  // Read user input

                        Integer[] coordinate = spr.getCoordinatesFromCellId(cellId);

                        System.out.println("Add cell's content:");
                        Scanner myContent = new Scanner(System.in);  // Create a Scanner object
                        String content = myContent.nextLine();  // Read user input

                        spr.updateCell(coordinate[1], coordinate[0], content);
                        System.out.println("Creating cell...");
                        spr.printMatrix();

                    }

                    case 3 -> {

                        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
                        dialog.setMode(FileDialog.SAVE);
                        dialog.setVisible(true);
                        String path = dialog.getDirectory();
                        String name = dialog.getFile();
                        SaveCSVFile saveCSVFile = new SaveCSVFile(path, name, spr);
                        saveCSVFile.SaveCSV();

                    }

                    case 4 -> this.salir = true;
                    default -> System.out.println("Valid options between 1 and 4");

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            showMenu();
        }

    }
}

