package StepDefinitions;

import com.csv2rdf.csv.CSV2RDF;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class are_there_enough_argumentsSteps {
    private boolean itHasException;
    private final CSV2RDF testCSV2RDF = new CSV2RDF();

    @Given("generate an empty list to arguments")
    public void generataAnEmptyListToArguments() {
        testCSV2RDF.files = new ArrayList<>();
    }


    @Given("number of arguments is three")
    public void number_of_arguments_is_three() {
        testCSV2RDF.files.add("src/main/resources/input/examples/cars/template.ttl");
        testCSV2RDF.files.add("src/main/resources/input/examples/cars/cars.csv");
        testCSV2RDF.files.add("src/main/resources/output/cars.ttl");
    }

    @When("I ask whether three arguments are enough")
    public void i_ask_whether_arguments_are_enough() {
        try{
            testCSV2RDF.run();
            itHasException = false;
        }
        catch (IllegalArgumentException ex) {
            fail("shouldn't have gotten here...");
        }
        catch (Exception ex) {
            fail("Some other exception occurred, also shouldn't have gotten here...");
        }
    }


    @Then("I should be told Yes")
    public void i_should_be_told_yes() {
        assertFalse(itHasException);
    }

    @Given("number of arguments is {string}")
    public void number_of_arguments_is(String arguments) {
        for (int i = 0; i < Integer.parseInt(arguments); i++){
            testCSV2RDF.files.add("test_param");
        }
    }

    @When("I ask whether {string} arguments are enough")
    public void iAskWhetherArgumentsAreEnough(String arguments) {
        try {
            testCSV2RDF.run();
            fail("shouldn't have gotten here...");
        }
        catch (IllegalArgumentException ex) {
            itHasException = true;
        }
        catch (Exception ex) {
            fail("Some other exception occurred, also shouldn't have gotten here...");
        }
    }

    @Then("I should be told No")
    public void i_should_be_told_no() {
        assertTrue(itHasException);
    }

}
