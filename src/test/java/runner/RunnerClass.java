package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        glue = {"seleniumTesting"},
        features = {"src/test/resources/demoQA.feature"}
)
public class RunnerClass extends AbstractTestNGCucumberTests {
}
