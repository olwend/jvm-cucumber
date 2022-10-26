import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resource"},
        tags = {},
        plugin ="json:target/jsonReports/cucumber-report.json"

)

public class TestRunner {}