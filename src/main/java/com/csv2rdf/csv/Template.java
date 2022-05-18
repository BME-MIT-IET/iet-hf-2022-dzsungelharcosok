package com.csv2rdf.csv;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import org.openrdf.model.*;
import org.openrdf.rio.*;
import org.openrdf.rio.helpers.BasicParserSettings;
import org.openrdf.rio.helpers.RDFHandlerBase;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.csv2rdf.csv.CSV2RDF.inputRows;
import static com.csv2rdf.csv.CSV2RDF.outputTriples;

public class Template {
    private static List<StatementGenerator> stmts = Lists.newArrayList();
    private final List<ValueProvider> valueProviders = Lists.newArrayList();
    final boolean noHeader;

    Template(List<String> cols, File templateFile, RDFWriter writer, boolean noHeader) throws IOException, RDFHandlerException, RDFParseException {
        parseTemplate(cols, templateFile, writer);
        this.noHeader = noHeader;
    }

    private String insertPlaceholders(List<String> cols, File templateFile) throws IOException {
        Pattern p = Pattern.compile("([\\$|\\#]\\{[^}]*\\})");
        Matcher m = p.matcher(Files.toString(templateFile, CSV2RDF.INPUT_CHARSET));

        StringBuilder sb = new StringBuilder();
        findInMatcher(cols, m, sb);
        m.appendTail(sb);
        return sb.toString();
    }

    private void findInMatcher(List<String> cols, Matcher m, StringBuilder sb) {
        while (m.find()) {
            String res = m.group(1);
            String varName = res.substring(2, res.length() - 1);
            ValueProvider valueProvider = valueProviderFor(varName, cols);
            Preconditions.checkArgument(valueProvider != null, "Invalid template variable", res);
            valueProvider.isHash = (res.charAt(0) == '#');
            m.appendReplacement(sb, valueProvider.placeholder);
            valueProviders.add(valueProvider);
        }
    }

    private ValueProvider valueProviderFor(String varName, List<String> cols) {
        if (varName.equalsIgnoreCase("_ROW_")) {
            return new RowNumberProvider();
        }
        if (varName.equalsIgnoreCase("_UUID_")) {
            return new UUIDProvider();
        }

        int index = getIndex(varName, cols);
        return index == -1 ? null : new RowValueProvider(index);

    }

    private int getIndex(String varName, List<String> cols) {
        int index = -1;
        if (!noHeader) {
            index = cols.indexOf(varName);
        } else {
            try {
                index = Integer.parseInt(varName);
            } catch (NumberFormatException e) {
                if (varName.length() == 1) {
                    char c = Character.toUpperCase(varName.charAt(0));
                    if (c >= 'A' && c <= 'Z') {
                        index = c - 'A';
                    }
                }
            }
        }
        return index;
    }

    private void parseTemplate(List<String> cols, File templateFile, final RDFWriter writer) throws IOException, RDFHandlerException, RDFParseException {
        String templateStr = insertPlaceholders(cols, templateFile);

        RDFParser parser = Rio.createParser(RDFFormat.forFileName(templateFile.getName()));
        parser.setParserConfig(getParserConfig());
        parser.setRDFHandler(new RDFHandlerBase() {
            @SuppressWarnings("rawtypes")
            private final Map<Value, ValueGenerator> generators = Maps.newHashMap();

            @Override
            public void startRDF() throws RDFHandlerException {
                writer.startRDF();
            }

            @Override
            public void handleNamespace(String prefix, String uri) throws RDFHandlerException {
                writer.handleNamespace(prefix, uri);
            }

            @Override
            public void handleStatement(Statement st) {
                ValueGenerator<Resource> subject = generatorFor(st.getSubject());
                ValueGenerator<URI> predicate = generatorFor(st.getPredicate());
                ValueGenerator<Value> object = generatorFor(st.getObject());
                stmts.add(new StatementGenerator(subject, predicate, object));
            }

            @SuppressWarnings({"unchecked", "rawtypes"})
            private <V extends Value> ValueGenerator<V> generatorFor(V value) {
                ValueGenerator<V> generator = generators.get(value);
                if (generator != null) {
                    return generator;
                }
                if (value instanceof BNode) {
                    generator = (ValueGenerator<V>) new BNodeGenerator();
                } else {
                    String str = value.toString();
                    ValueProvider[] providers = providersFor(str);
                    if (providers.length == 0) {
                        generator = new ConstantValueGenerator(value);
                    } else if (value instanceof URI) {
                        generator = (ValueGenerator<V>) new TemplateURIGenerator(str, providers);
                    } else {
                        Literal literal = (Literal) value;
                        generator = (ValueGenerator<V>) new TemplateLiteralGenerator(literal, providers);
                    }
                }
                generators.put(value, generator);
                return generator;
            }

            private ValueProvider[] providersFor(String str) {
                List<ValueProvider> result = Lists.newArrayList();
                for (ValueProvider provider : valueProviders) {
                    if (str.contains(provider.placeholder)) {
                        result.add(provider);
                    }
                }
                return result.toArray(new ValueProvider[0]);
            }
        });

        parser.parse(new StringReader(templateStr), "urn:");
    }

    public static void generate(String[] row, RDFHandler handler) throws RDFHandlerException {
        inputRows++;
        for (StatementGenerator stmt : stmts) {
            outputTriples++;
            handler.handleStatement(stmt.generate(inputRows, row));
        }
    }

    private static ParserConfig getParserConfig() {
        ParserConfig config = new ParserConfig();

        Set<RioSetting<?>> aNonFatalErrors = Sets.newHashSet(
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