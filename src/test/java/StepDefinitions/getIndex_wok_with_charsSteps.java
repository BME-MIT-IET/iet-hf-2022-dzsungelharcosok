package StepDefinitions;

import com.csv2rdf.csv.Template;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class getIndex_wok_with_charsSteps {
    Template template;
    String[] cols;
    int actualIndex;
    String varName;
    List<String> argList;
    int expectedIndex;

    @Given("generate test environment")
    public void generateTestEnvironment()  {
        template = new Template(true);
        cols = new String[]{"test", "test", "test"};
    }


    @Given("a number as a string and an initialized list of columns")
    public void a_number_as_a_string_and_an_initialized_list_of_columns() {
        expectedIndex = 10;
        varName = "10";
        argList = Arrays.asList(cols);
    }

    @When("I ask whether the function is working with number")
    public void i_ask_whether_the_function_is_working_with_number() {
        actualIndex = template.getIndex(varName, argList);
    }

    @Given("a string and an initialized list of columns")
    public void a_string_and_an_initialized_list_of_columns() {
        expectedIndex = 12;
        varName = "M";
        argList = Arrays.asList(cols);
    }
    @When("I ask whether the function is working with string")
    public void i_ask_whether_the_function_is_working_with_string() {
        actualIndex = template.getIndex(varName, argList);
    }

    @Then("I should be told it is working")
    public void i_should_be_told_it_is_working() {
        assertEquals(expectedIndex, actualIndex);
    }


}
