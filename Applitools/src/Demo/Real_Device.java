package Demo;





import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.concurrent.TimeUnit;
import com.applitools.eyes.selenium.Eyes;


public class Real_Device {

    public static void main(String[] args) throws MalformedURLException {

        // Initialize the eyes SDK and set your private API key.
        Eyes eyes = new Eyes();
        eyes.setApiKey("rhKQLBWChQdjg8q8NU0yXNgQM77Fr2Qz5pIBOfaI128110");

        // Set the desired capabilities.
        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability("platformName", "Android");
        dc.setCapability("platformVersion", "PLATFORM_VERSION");
        dc.setCapability("browserName", "BROWSER_NAME");
        dc.setCapability("deviceName", "DEVICE_NAME");

        // Open browser.
        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        try {

            // Start the test.
            eyes.open(driver, "Hello World!", "My first Appium web Java test!");

            // Navigate the browser to the "hello world!" web-site.
            driver.get("https://applitools.com/helloworld");

            // Visual validation point #1.
            eyes.checkWindow("Hello!");

            // Click the "Click me!" button.
            driver.findElement(By.tagName("button")).click();

            // Visual validation point #2.
            eyes.checkWindow("Click!");

            // End the test.
            eyes.close();

        } finally {

            // Close the browser.
            driver.quit();

            // If the test was aborted before eyes.close was called, ends the test as aborted.
            eyes.abortIfNotClosed();

        }
    }
}