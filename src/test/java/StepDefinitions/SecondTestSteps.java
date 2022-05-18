package StepDefinitions;

import com.complexible.common.csv.CSV2RDF;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SecondTestSteps {
    private String actualAnswer;
    private final CSV2RDF testCSV2RDF = new CSV2RDF();

    private String areThereEnoughArguments(int numberOfArguments) {
        int expectedArgumentsNumber = 3;
        return (expectedArgumentsNumber == numberOfArguments) ? "Yes" : "No";
    }

    @Given("a list to arguments")
    public void aListToArguments() {
        testCSV2RDF.files = new ArrayList<String>();
    }

    @Given("number of arguments is {string}")
    public void number_of_arguments_is(String arguments) {
        for (int i = 0; i < Integer.parseInt(arguments); i++){
            testCSV2RDF.files.add("test_param");
        }
    }
    @When("I ask are there enough arguments")
    public void i_ask_are_there_enough_arguments() {
        actualAnswer = areThereEnoughArguments(testCSV2RDF.files.size());
    }

    @Then("Then I should be told {string}")
    public void then_i_should_be_told(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }


}
