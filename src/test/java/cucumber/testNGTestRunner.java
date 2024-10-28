package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= "src/test/java/cucumber",glue="gyuri.stepDefinitions", monochrome=true, plugin= {"html:target/cucumber.html"}, tags="@Regression")
public class testNGTestRunner extends AbstractTestNGCucumberTests {

}
