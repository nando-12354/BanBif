package support;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "definitions",
        tags = "@regresion",
        plugin = {"html:target/reporte-cucumber/index.html","json:target/reporte-cucumber/cucumber.json"})
        //plugin = {"pretty","html:target/cucumber-report.html","json:target/cucumber.json"})
public class runtest { }