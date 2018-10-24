import java.io.File;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;


public class Notepad {
     public static void main(String[] args)  throws Exception{
		 File fl = new File("");
    	 WiniumDriverService service = new WiniumDriverService.Builder().usingDriverExecutable(fl).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
    	 DesktopOptions options = new DesktopOptions();
	     options.setApplicationPath("/opt/jee-oxygen/eclipse/eclipse");
	     WiniumDriver driver = new WiniumDriver(service,options);
	     Thread.sleep(1111);
	     driver.findElement(By.name("File")).click();
	     WebElement ele = driver.findElementByClassName("");
	       
	       
	}
}
