package com.csv2rdf.csv.Providers;

import com.csv2rdf.csv.CSV2RDF;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;

import java.util.UUID;

public abstract class ValueProvider {
    public final String placeholder = UUID.randomUUID().toString();
    public boolean isHash;


    public String provide(int rowIndex, String[] row) {
        String value = provideValue(rowIndex, row);
        if (value != null && isHash) {
            HashCode hash = Hashing.sha1().hashString(value, CSV2RDF.OUTPUT_CHARSET);
            value = BaseEncoding.base32Hex().omitPadding().lowerCase().encode(hash.asBytes());
        }
        return value;
    }

    protected abstract String provideValue(int rowIndex, String[] row);
}