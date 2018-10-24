import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

 
public class HeadLessChrome {
 
        public static void main(String[] args) throws IOException, InterruptedException {
                System.setProperty("webdriver.chrome.driver", "/home/netstorm/Desktop/chromedriver");   
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");     // For head less feature we need chrome 65 and above version 
                chromeOptions.addArguments("--no-sandbox");   // chromedriver should be 2.35 or latest 
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--ignore-certificate-errors");
                chromeOptions.addArguments("--window-size=1325x744");
                
                DesiredCapabilities  caps = new DesiredCapabilities();
                caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                caps.setCapability("acceptInsecureCert", true);
                caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
 
                WebDriver driver = new ChromeDriver(caps);
 
                driver.get("https://google.com");
                
                Thread.sleep(5000);
                
                System.out.println("Browser title :"+driver.getTitle());
 
                Thread.sleep(1000);
               
                TakesScreenshot srt = (TakesScreenshot) driver;
                File src = srt.getScreenshotAs(OutputType.FILE);
                File dest = new File("/home/netstorm/Desktop/google.png");
                if (dest.exists()){
                	dest.delete();
                }
                FileUtils.moveFile(src, dest);
                
                if (driver.getPageSource().contains("I'm Feeling Lucky")) {
                        System.out.println("Pass");
                } else {
                        System.out.println("Fail");
                }
                driver.quit();
        }
}