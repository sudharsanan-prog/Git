package org.example.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "C:\\Users\\S122\\Downloads\\courses\\java-selenium\\java-sel-proj\\TestNGFramework\\src\\main\\java\\org\\example\\Cucumber", glue = "org.example.StepDefinition",
monochrome = true, tags = "@Regression", plugin = {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
