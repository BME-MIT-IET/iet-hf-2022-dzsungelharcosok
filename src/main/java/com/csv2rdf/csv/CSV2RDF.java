// Copyright (c) 2014, Clark & Parsia, LLC. <http://www.clarkparsia.com>

package com.csv2rdf.csv;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import au.com.bytecode.opencsv.CSVReader;
import com.github.rvesse.airline.Cli;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.help.Help;
import org.openrdf.model.BNode;
import org.openrdf.model.Literal;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;
import org.openrdf.rio.*;
import org.openrdf.rio.helpers.BasicParserSettings;
import org.openrdf.rio.helpers.RDFHandlerBase;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import com.google.common.io.Files;

/**
 * Converts a CSV file to RDF based on a given template
 *
 * @author Evren Sirin
 */
@Command(name = "convert", description = "Runs the conversion.")
public class CSV2RDF implements Runnable {
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE_CHARACTER = '\"';
    private static final char DEFAULT_ESCAPE_CHARACTER = '\\';
    protected static final Charset INPUT_CHARSET = Charset.defaultCharset();
    protected static final Charset OUTPUT_CHARSET = Charsets.UTF_8;
    protected static final ValueFactory FACTORY = ValueFactoryImpl.getInstance();

    @Option(name = "--no-header", arity = 0, description = "If csv file does not contain a header row")
    boolean noHeader = false;

    @Option(name = {"-s", "--separator"}, description = "Seperator character used in the csv file or ',' by default.")
    String separator = String.valueOf(DEFAULT_SEPARATOR);

    @Option(name = {"-q", "--quote"}, description = "Quote character used in the csv file or '\"' by default.")
    String quote = String.valueOf(DEFAULT_QUOTE_CHARACTER);

    @Option(name = {"-e", "--escape"}, description = "Escape character used in the csv file or '\\' by default.")
    String escape = String.valueOf(DEFAULT_ESCAPE_CHARACTER);

    @Arguments(description = "File arguments. The extension of template file and output file determines the RDF format that will be used for them (.ttl = Turtle, .nt = N-Triples, .rdf = RDF/XML)", title = {
            "templateFile", "csvFile", "outputFile"})
    public List<String> files;
    private int inputRows = 0;
    private int outputTriples = 0;

    public void run() {
        Preconditions.checkArgument(files.size() >= 3, "Missing arguments");
        Preconditions.checkArgument(files.size() <= 3, "Too many arguments");

        File templateFile = new File(files.get(0));
        File inputFile = new File(files.get(1));
        File outputFile = new File(files.get(2));
        System.out.println("CSV to RDF conversion started...");
        System.out.println("Template: " + templateFile);
        System.out.println("Input   : " + inputFile);
        System.out.println("Output  : " + outputFile);

        try {
            Reader in = Files.newReader(inputFile, INPUT_CHARSET);
            CSVReader reader = new CSVReader(in, toChar(separator), toChar(quote), toChar(escape));
            String[] row = reader.readNext();

            Preconditions.checkNotNull(row, "Input file is empty!");

            Writer out = Files.newWriter(outputFile, OUTPUT_CHARSET);
            RDFWriter writer = Rio.createWriter(RDFFormat.forFileName(outputFile.getName(), RDFFormat.TURTLE), out);

            Template template = new Template(Arrays.asList(row), templateFile, writer, noHeader);

            if (noHeader) {
                template.generate(row, writer, inputRows, outputTriples);
            }

            while ((row = reader.readNext()) != null) {
                template.generate(row, writer, inputRows, outputTriples);
            }

            writer.endRDF();

            reader.close();
            in.close();
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Converted %,d rows to %,d triples%n", inputRows, outputTriples);
    }

    private static char toChar(String value) {
        Preconditions.checkArgument(value.length() == 1, "Expecting a single character but got %s", value);
        return value.charAt(0);
    }

    private static ParserConfig getParserConfig() {
        ParserConfig config = new ParserConfig();

        Set<RioSetting<?>> aNonFatalErrors = Sets.<RioSetting<?>>newHashSet(
                BasicParserSettings.FAIL_ON_UNKNOWN_DATATYPES, BasicParserSettings.FAIL_ON_UNKNOWN_LANGUAGES);

        config.setNonFatalErrors(aNonFatalErrors);

        config.set(BasicParserSettings.FAIL_ON_UNKNOWN_DATATYPES, false);
        config.set(BasicParserSettings.FAIL_ON_UNKNOWN_LANGUAGES, false);
        config.set(BasicParserSettings.VERIFY_DATATYPE_VALUES, false);
        config.set(BasicParserSettings.VERIFY_LANGUAGE_TAGS, false);
        config.set(BasicParserSettings.VERIFY_RELATIVE_URIS, false);

        return config;
    }
}