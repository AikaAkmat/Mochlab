package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class BrowserUtils {
    public static String takeScreenShot() throws IOException {

        File src = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/ScreenShotFile/" + System.currentTimeMillis() + ".png";
        File desFile = new File(destination);

        FileUtils.copyFile(src, desFile);
        return destination;
    }
}
