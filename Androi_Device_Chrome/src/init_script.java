



import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class init_script {
    public static WebDriver driver;

    public static int execute() throws Exception
    {
        try {
             
        	try{
                JavascriptExecutor js = ((JavascriptExecutor) driver);
                HashMap<String, String> tapObject = new HashMap<String, String>();
                tapObject.put("action", "accept");
                tapObject.put("label", "Allow");
                js.executeScript("mobile:alert", tapObject);
                System.out.println("alert accepted");
                WebElement ele = driver.findElement(By.xpath(""));
                
                
                
                
            }catch(Exception e){ e.printStackTrace();}
            driver.findElement(By.xpath("//XCUIElementTypeAlert//XCUIElementTypeButton[@name='Allow']")).click();

           	
            driver = capabilitySet.initiateRemoteChromeBrowserMobile("http://10.10.30.37:5555/wd/hub" ,7991 , true);

        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
