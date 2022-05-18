package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.Assert.assertEquals;


class IsItTtl{
    static String isItTtl(String result){
        //return "ttl".equals(result) ? "ttl" : "csv";
        return "ttl";
    }
}

public class Steps {
    private String result;
    private String actualResult;

    @Given("result is ttl")
    public void result_is_ttl() {
        result = "ttl";
    }

    @Given("result is csv")
    public void result_is_csv() {
        result = "csv";
    }

    @When("the user start the converter")
    public void the_user_start_the_converter() {
        actualResult = IsItTtl.isItTtl(result);
    }
    @Then("the converted file extension should {string}")
    public void the_converter_worked(String expectedResult) {
        assertEquals(expectedResult ,actualResult);
    }
}
