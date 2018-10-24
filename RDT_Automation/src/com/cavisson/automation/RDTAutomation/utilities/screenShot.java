package com.cavisson.automation.RDTAutomation.utilities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.cavisson.automation.RDTAutomation.generics.loggerLoad;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class screenShot {
     
	private WebDriver driver;
	
	public screenShot(WebDriver driver){
		this.driver = driver;
	}
	
	Logger log = loggerLoad.config("screenShot");
	
	public void captureShot( String name){
		
		 Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	     try {
			ImageIO.write(fpScreenshot.getImage(),"PNG",new File("/home/netstorm/workbench/RDT_Automation/images/"+name+".png"));
		 }catch (IOException e) {
			log.error("Error during screenshot : "+e);
			e.printStackTrace();
		}	
	}
}
