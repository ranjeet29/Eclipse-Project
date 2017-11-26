import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TC1 {
	
	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;
	
	@BeforeClass
	public void M1(){
		extent = ExtentManager.GetExtent();
//		System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void checkHome()
	{
		try{
		driver.get("http://www.qavalidation.com/");
		
		//test = extent.startTest("OpenUT", "Verify HomePage");//earlier version
		test = extent.createTest("QAVsite", "Verify HomePage");
		
		if(driver.getTitle().contains("QA manual")){
			//test.log(LogStatus.PASS, driver.getTitle() +" contain "+"QA & Validation" );//earlier version
			//test.log(Status.PASS, driver.getTitle() +" contain "+"QA & Validation");
			//or
		    test.pass(driver.getTitle() +" contain "+"QA manual");
		    //test.log(Status.INFO, "Snapshot" +  test.addScreenCaptureFromPath("./1.jpg"));
		}
		else
			//test.log(LogStatus.FAIL, driver.getTitle() +" doesn't contain "+"QA & Validation" );//earlier version
			test.log(Status.FAIL, driver.getTitle() +" doesn't contain "+"QA manual" );
		}catch(Exception e){test.log(Status.ERROR, e.getMessage());}
	}
	
	@Test
	public void checkFail(){
		test = extent.createTest("Testing how fail works");
		//test.log(Status.INFO, "fail check started");
		test.fail("Test fail");
	}
		
	@AfterClass
	public void tear()
	{
		//extent.endTest(test);//earlier version
		extent.flush();
		driver.quit();
	}
}