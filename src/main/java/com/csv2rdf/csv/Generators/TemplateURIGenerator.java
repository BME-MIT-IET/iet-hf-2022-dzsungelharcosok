package com.csv2rdf.csv.Generators;

import com.csv2rdf.csv.CSV2RDF;
import com.csv2rdf.csv.Providers.ValueProvider;
import org.openrdf.model.URI;

public class TemplateURIGenerator extends TemplateValueGenerator<URI> {


    public TemplateURIGenerator(String template, ValueProvider[] providers) {
        super(template, providers);
    }

    public URI generate(int rowIndex, String[] row) {
        return CSV2RDF.FACTORY.createURI(applyTemplate(rowIndex, row));
    }
}
