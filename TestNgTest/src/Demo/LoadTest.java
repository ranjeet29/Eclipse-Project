package Demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class LoadTest {

  @Test(invocationCount = 5, threadPoolSize = 3)
  public void loadTest() {

	System.out.printf("%n[START] Thread Id : %s is started!",
                                  Thread.currentThread().getId());

	WebDriver driver = new FirefoxDriver();
	driver.get("http://10.10.30.38/tours/index.html");

	//perform whatever actions, like login, submit form or navigation

	System.out.printf("%n[END] Thread Id : %s",
                                  Thread.currentThread().getId());

	driver.quit();

  }
}