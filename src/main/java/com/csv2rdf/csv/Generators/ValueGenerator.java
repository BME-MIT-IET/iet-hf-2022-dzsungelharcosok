package com.csv2rdf.csv.Generators;

import org.openrdf.model.Value;

public interface ValueGenerator<V extends Value> {
    V generate(int rowIndex, String[] row);
}
