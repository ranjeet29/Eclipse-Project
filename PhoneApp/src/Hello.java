
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import com.applitools.eyes.Eyes;

class Hello{
    public static void main(String[] args) throws MalformedURLException {
        Eyes eyes = new Eyes();
        // This is your api key, make sure you use it in all your tests.
        eyes.setApiKey("rhKQLBWChQdjg8q8NU0yXNgQM77Fr2Qz5pIBOfaI128110");

        // Setup appium - Make sure the capabilities meets your environment.
        // Refer to http://appium.io documentation if required.
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        // The original app from Appium github project.
        dc.setCapability("app", "https://store.applitools.com/download/Android.apiDemo.apk");

        WebDriver driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        try {
            // Start visual testing
            eyes.open(driver, "Android test application", "test");

            // Visual validation point #1
            eyes.checkWindow("Initial view");
            driver.findElement(By.name("Text")).click();
            driver.findElement(By.name("LogTextBox")).click();
            driver.findElement(By.name("Add")).click();
            // Visual validation point #2
            eyes.checkWindow("After one click");
            driver.findElement(By.name("Add")).click();
            // Visual validation point #3
            eyes.checkWindow("After second click");

            // End visual testing. Validate visual correctness.
            eyes.close();
        } finally {
            eyes.abortIfNotClosed();
            driver.quit();
        }
    }
}