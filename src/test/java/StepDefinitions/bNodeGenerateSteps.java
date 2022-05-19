package StepDefinitions;

import com.csv2rdf.csv.BNodeGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openrdf.model.BNode;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class bNodeGenerateSteps {
    BNodeGenerator bNodeGenerator;
    int rowIndex;
    String[] row;
    BNode actualBNode;

    @Given("a string array and set the rowIndex to {int}")
    public void a_string_array_and_set_the_row_index_to(Integer index) {
        bNodeGenerator = new BNodeGenerator();
        row = new String[]{"1997", "Ford", "E350", "ac, abs, moon", "3000.00"};
        rowIndex = index;
    }
    @When("I ask whether the function return is null")
    public void i_ask_whether_the_function_return_is_null() {
        actualBNode = bNodeGenerator.generate(rowIndex, row);
    }
    @Then("I should be told the return value is null")
    public void i_should_be_told_the_return_value_is_null() {
        assertNull(actualBNode);
    }

    @Then("I should be told the return value is not null")
    public void i_should_be_told_the_return_value_is_not_null() {
        assertNotNull(actualBNode);
    }
}
