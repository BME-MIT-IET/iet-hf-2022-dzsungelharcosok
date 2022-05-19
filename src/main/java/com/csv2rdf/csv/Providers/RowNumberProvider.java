package com.csv2rdf.csv.Providers;


public class RowNumberProvider extends ValueProvider {

    protected String provideValue(int rowIndex, String[] row) {
        return String.valueOf(rowIndex);
    }
}