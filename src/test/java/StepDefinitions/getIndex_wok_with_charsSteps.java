package StepDefinitions;

import com.csv2rdf.csv.CSV2RDF;
import com.csv2rdf.csv.Template;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;

import java.io.IOException;
import java.util.Arrays;

public class getIndex_wok_with_charsSteps {


    @Given("generate test environment")
    public void generateTestEnvironment() {
//        String[] row = {"Year", "Make", "Model", "Description", "Price"};
//        try {
//            Template template = new Template(Arrays.asList(row), "src/main/resources/input/examples/cars/template.ttl", null, false);
//        } catch (IOException | RDFHandlerException | RDFParseException e) {
//            throw new RuntimeException(e);
//        }
    }


    @Given("a number as a string and an initialized list of columns")
    public void a_number_as_a_string_and_an_initialized_list_of_columns() {
        //    varName="Make"
        //    cols (arraylist): Year, Make, model, description, price
        //
        //    navheader = false
    }
    @When("I ask whether the function is working with number")
    public void i_ask_whether_the_function_is_working_with_number() {

    }

    @Given("a string and an initialized list of columns")
    public void a_string_and_an_initialized_list_of_columns() {

    }
    @When("I ask whether the function is working with string")
    public void i_ask_whether_the_function_is_working_with_string() {

    }

    @Then("I should be told the it is working")
    public void i_should_be_told_the_it_is_working() {

    }


}
