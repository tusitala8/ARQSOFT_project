package com.company.CSVFile;

public class UnableToLoadCSV extends Exception {
    public UnableToLoadCSV(String path) {
        super("Unable to Load CSV. Check path: " + path);
    }
}
