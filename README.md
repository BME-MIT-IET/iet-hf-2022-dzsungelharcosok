csv2rdf
=======

csv2rdf is a simple tool for generating RDF output from CSV/TSV files. The conversion is done by a template file
that shows how the RDF output will look for one row. See [examples/cars](examples/cars) for details. 

Building
--------

`mvn clean install` will build the project.

Running
-------

You can run the tool with the command `mvn exec:java` followed by arguments (`-Dexec.args="arg1 arg2 arg3"`).

The running command template is the following:

```
mvn exec:java -Dexec.args="<templateFile> <csvFile> <outputFile>" 
``` 

You can run the conversion for the example using: 
```
mvn exec:java -Dexec.args="src/main/resources/input/examples/cars/template.ttl src/main/resources/input/examples/cars/cars.csv src/main/resources/output/cars.ttl" 
``` 
