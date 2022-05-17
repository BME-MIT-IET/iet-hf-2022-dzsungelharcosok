package com.csv2rdf.csv;

import org.openrdf.model.BNode;
import org.openrdf.model.ValueFactory;

public class BNodeGenerator implements ValueGenerator<BNode> {
    private BNode value = null;
    private int generatedRow = -1;

    public BNodeGenerator() {
    }

    public BNode generate(int rowIndex, String[] row) {
        if (value == null || generatedRow != rowIndex) {
            value = CSV2RDF.FACTORY.createBNode();
            generatedRow = rowIndex;
        }
        return value;
    }
}