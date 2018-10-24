package Practice;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindAllLink {

	 public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "/home/netstorm/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://automationranjeet.blogspot.com/");
		
		List<WebElement> eles = driver.findElements(By.tagName("a"));
		
		int size = eles.size();
		
		System.out.println("size : "+size);
		  Iterator<WebElement> itr = eles.iterator();
		    
		    while(itr.hasNext()){
		    	System.out.println("itr next :"+itr.next().getText().toString());
		    }
		    
	    for(WebElement ele : eles){
	    	
	    	String data = ele.getText().toString();
	    	if ( data.contains("capture screenshot in selenium")){
	    		ele.click();
	    	}
	    	System.out.println("link text : "+ ele.getText());
	    }
		
		
	  
		driver.quit();
		
		
		
	}
}
