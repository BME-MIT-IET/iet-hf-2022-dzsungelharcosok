package com.csv2rdf.csv;

import org.openrdf.model.BNode;

public class BNodeGenerator implements ValueGenerator<BNode> {
    private BNode value = null;
    private int generatedRow = -1;

    public BNode generate(int rowIndex, String[] row) {
        if (generatedRow != rowIndex) {
            value = CSV2RDF.FACTORY.createBNode();
            generatedRow = rowIndex;
        }
        return value;
    }
}