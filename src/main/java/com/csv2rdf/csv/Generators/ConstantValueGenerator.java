package com.csv2rdf.csv.Generators;

import org.openrdf.model.Value;

public class ConstantValueGenerator<V extends Value> implements ValueGenerator<V> {
    private final V value;

    public ConstantValueGenerator(V value) {
        this.value = value;
    }

    public V generate(int rowIndex, String[] row) {
        return value;
    }
}
