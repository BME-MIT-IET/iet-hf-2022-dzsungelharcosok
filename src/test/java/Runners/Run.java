package Runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/resources",
        glue = {"StepDefinitions"}
)
public class Run extends AbstractTestNGCucumberTests {
}
