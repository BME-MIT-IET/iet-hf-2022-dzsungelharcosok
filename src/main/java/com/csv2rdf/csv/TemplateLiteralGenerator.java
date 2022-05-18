package com.csv2rdf.csv;

import org.openrdf.model.Literal;
import org.openrdf.model.URI;

public class TemplateLiteralGenerator extends TemplateValueGenerator<Literal> {
    private final URI datatype;
    private final String lang;

    TemplateLiteralGenerator(Literal literal, ValueProvider[] providers) {
        super(literal.getLabel(), providers);

        this.datatype = literal.getDatatype();
        this.lang = literal.getLanguage();

    }

    public Literal generate(int rowIndex, String[] row) {
        String value = applyTemplate(rowIndex, row);
        if (datatype == null) {
            return lang == null ? CSV2RDF.FACTORY.createLiteral(value) : CSV2RDF.FACTORY.createLiteral(value, lang);
        } else return CSV2RDF.FACTORY.createLiteral(value, datatype);
    }
}