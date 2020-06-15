package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"resources/Futures/HTTPComponent.feature"},
        glue = "StepDefs/MocklabStepDef.java",
        dryRun = true,
        monochrome =false
)
public class MocklabRunner {
}
