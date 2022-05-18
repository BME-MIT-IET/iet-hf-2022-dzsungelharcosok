package com.csv2rdf.csv;

import com.github.rvesse.airline.Cli;
import com.github.rvesse.airline.help.Help;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        CSV2RDF csv2RDF = new CSV2RDF();
        try {
            Cli.<Runnable>builder("csv2rdf").withDescription("Converts a CSV file to RDF based on a given template")
                    .withDefaultCommand(csv2RDF.getClass()).withCommand(csv2RDF.getClass()).withCommand(Help.class)
                    .build().parse(args).run();
        } catch (Exception e) {
            logger.log(Level.FINE, "ERROR: {0}", e.getMessage());
            e.printStackTrace();
        }
    }
}
