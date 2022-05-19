package com.csv2rdf.csv.Providers;

public class RowValueProvider extends ValueProvider {
    private final int colIndex;

    public RowValueProvider(int colIndex) {
        this.colIndex = colIndex;
    }

    protected String provideValue(int rowIndex, String[] row) {
        return row[colIndex];
    }
}