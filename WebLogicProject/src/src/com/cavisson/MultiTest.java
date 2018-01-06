package src.com.cavisson;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;       
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class MultiTest {
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
	  System.out.println("Data provider test :" + n  +s);
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Before Method run");
  }

  
  @AfterMethod
  public void afterMethod() {
	  System.out.println("After Method run");
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  
  @BeforeClass
  public void beforeClass() {
     System.out.println("in beforeClass");
  }

  @AfterClass
  public void afterClass() {
     System.out.println("in afterClass");
  }

  @BeforeTest
  public void beforeTest1() {
     System.out.println("in beforeTest");
  }

  @AfterTest
  public void afterTest1() {
     System.out.println("in afterTest");
  }

  @BeforeSuite
  public void beforeSuite1() {
     System.out.println("in beforeSuite");
  }

  @AfterSuite
  public void afterSuite() {
     System.out.println("in afterSuite");
  }
  
  
  @BeforeTest
  public void beforeTest() {
	  System.out.println("Before Test run");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("After test run");
  }
  
  @Test()
  
  public void One() {
 
      System.out.println("This is the Test Case number One");
 
  }
 
  @Test()
 
  public void Two() {
 
	  System.out.println("This is the Test Case number Two");
 
  }
 
  @Test()
 
  public void Three() {
 
	  System.out.println("This is the Test Case number Three");
 
  }
 
  @Test()
 
  public void Four() {
 
	  System.out.println("This is the Test Case number Four");
 
  }
 
}


