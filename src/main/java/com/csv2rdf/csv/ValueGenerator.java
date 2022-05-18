package com.csv2rdf.csv;

import org.openrdf.model.Value;

interface ValueGenerator<V extends Value> {
    V generate(int rowIndex, String[] row);
}
