package com.cavisson.automation.RDTAutomation.testsuite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.cavisson.automation.RDTAutomation.drivers.*;
import com.cavisson.automation.RDTAutomation.generics.loggerLoad;
import com.cavisson.automation.RDTAutomation.utilities.DataproviderClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

public class RDTSuite {
	
   
	public WebDriver driver ;
	Logger log = loggerLoad.config("RDTSuite");
	
    @Parameters({"browsertype"})
	@BeforeSuite(alwaysRun=true , description="Going to register driver")
	public void startApp(@Optional("chrome") String browsertype) {
		driver = Driver.registerDriver(browsertype);
		
	}
    
    
    
    @Test(priority=1 , dataProvider="androidDeviceInfoProvider",dataProviderClass=DataproviderClass.class ,description="Going to run android devices test case")
    private void androidDeviceTest(String dname , String info , String demo) {
      log.info("androiddevice test " + dname +" " + " " + info +" " + demo);
	}
    
    @Test( priority=2 ,dataProvider="iosDeviceInfoProvider",dataProviderClass=DataproviderClass.class, description="Going to run ios device test case")
    private void iosDeviceTest(String dname , String info , String demo ){
    	log.info("iosdevice test " + dname +" " + " " + info +" " + demo);
    }
    
   
	
	@AfterSuite(alwaysRun=true , description = "Going to quit and close driver")
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.info("driver quit");
	}
}
