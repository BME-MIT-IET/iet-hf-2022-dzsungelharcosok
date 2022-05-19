package com.csv2rdf.csv.Providers;

import java.util.UUID;

public class UUIDProvider extends ValueProvider {
    private String value = null;
    private int generatedRow = -1;

    protected String provideValue(int rowIndex, String[] row) {
        if (value == null || generatedRow != rowIndex) {
            value = UUID.randomUUID().toString();
            generatedRow = rowIndex;
        }
        return value;
    }
}