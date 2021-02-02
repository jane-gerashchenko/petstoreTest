import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"io/cucumber/petstore/stepdefs"},
        plugin = {"pretty"},
        publish = true
        )
public class TestRunner {
}
