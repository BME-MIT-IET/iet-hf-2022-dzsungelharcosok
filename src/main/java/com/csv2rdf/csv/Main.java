package com.csv2rdf.csv;

import com.github.rvesse.airline.Cli;
import com.github.rvesse.airline.help.Help;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        try {
            Cli.<Runnable>builder("csv2rdf").withDescription("Converts a CSV file to RDF based on a given template")
                    .withDefaultCommand(CSV2RDF.class).withCommand(CSV2RDF.class).withCommand(Help.class)
                    .build().parse(args).run();
        } catch (Exception e) {
            logger.log(Level.FINE, "ERROR: {0}", e.getMessage());
            e.printStackTrace();
        }
    }
}
