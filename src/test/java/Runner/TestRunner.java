package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/Features/Web/AddNewUser.feature",
        glue = {"Steps.Web"},
        plugin = {"pretty", "json:target/cucumber-reports/Cucumber.json"
                }
)
public class TestRunner {

}
