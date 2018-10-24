package Demo;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import com.applitools.eyes.selenium.Eyes;

public class ios_eamulator_app {
    public static void main(String[] args) throws MalformedURLException {
        Eyes eyes = new Eyes();
        // This is your api key, make sure you use it in all your tests.
        eyes.setApiKey("rhKQLBWChQdjg8q8NU0yXNgQM77Fr2Qz5pIBOfaI128110");

        // Setup appium - Make sure the capabilities meets your environment.
        // Refer to http://appium.io documentation if required.
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "iOS");
        // The original app from Appium github project.
        dc.setCapability("app", "https://store.applitools.com/download/iOS.TestApp.app.zip");

        WebDriver driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        try {
            // Start visual testing
            eyes.open(driver, "iOS test application", "test");

            // Visual validation point #1
            eyes.checkWindow("Initial view");
            driver.findElement(By.name("TextField1")).sendKeys("3");
            driver.findElement(By.name("TextField2")).sendKeys("5");
            driver.findElement(By.name("ComputeSumButton")).click();
            // Visual validation point #2
            eyes.checkWindow("After compute");

            // End visual testing. Validate visual correctness.
            eyes.close();
        } finally {
            eyes.abortIfNotClosed();
            driver.quit();
        }
    }
}