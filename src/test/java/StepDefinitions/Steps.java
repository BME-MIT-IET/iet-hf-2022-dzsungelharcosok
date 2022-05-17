package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.SortedMap;

public class Steps {
    @Given("the user is on login page")
    public void the_user_is_on_login_page() {
        System.out.println("The user is on login page");

    }
    @When("the user valid credentials")
    public void the_user_valid_credentials() {
        System.out.println("Entered username and password");
    }
    @When("hits submit")
    public void hits_submit() {
        System.out.println("Clicked on submit");
    }
    @Then("user should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        System.out.println("Yeah I an logged in");
    }

    @Given("the user is in project")
    public void the_user_is_on_project_main() {
        System.out.println("the user is in project");
    }
    @When("the user start the test")
    public void the_user_start_the_test() {
        System.out.println("start the test");
    }
    @Then("user should be see the test result")
    public void user_should_be_see_the_test_result() {
        System.out.println("User see the resuls");
    }
}
