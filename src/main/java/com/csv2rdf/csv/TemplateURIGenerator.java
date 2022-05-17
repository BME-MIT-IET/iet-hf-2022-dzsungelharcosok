package com.csv2rdf.csv;

import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;

public class TemplateURIGenerator extends TemplateValueGenerator<URI> {


    TemplateURIGenerator(String template, ValueProvider[] providers) {
        super(template, providers);
    }

    public URI generate(int rowIndex, String[] row) {
        return CSV2RDF.FACTORY.createURI(applyTemplate(rowIndex, row));
    }
}
