package Demo;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriverException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import java.util.HashMap;
import java.util.Map.Entry;
import org.openqa.selenium.WebDriver;

public class CommandBrowserCommand {
	
	public static WebDriver driver ;
	public WebElement element;
	
	
	 /* SelectorType is a enum which contian list of all loctors using 'By' strategies*/
    public enum SelectorType {
                XPATH, ID, CLASS_NAME, CSS_SELECTOR, LINK_TEXT, NAME, PARTIAL_LINK_TEXT, TAG_NAME,;
    }

    /*This method is used to return time in seconds when pass milliseconds*/
    public static int convertMilisecToSec(int milliseconds){
         int seconds = (int) (milliseconds / 1000) % 60 ;
         System.out.println(seconds);
         return seconds;
     }
    
    
	
	public CommandBrowserCommand(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println(driver);
	}
	
	public void openUrl(String Url){
        driver.get(Url);
    }
	

	     //This method returns the title of hit url.
	    public String getTitle(){
	        return driver.getTitle();
	    }

	    //This method returns the url currently opened on the target browser window.
	    public String getCurrentURL(){
	        return driver.getCurrentUrl();
	     }

	    //This method returns the source of webpage opened in current browser window.
	    public String getPageSource(){
	        return driver.getPageSource();
	     }
	   
	    public void navigate(String id){
	        driver.navigate().to(id);
	    }
	    //This method is used to clicking on the Forward Button of any browser.
	     public void navigateForward(){
	         driver.navigate().forward();
	     }
	    
	    //This method is used to clicking on the Back Button of any browser.
	     public void navigateBack(){
	         driver.navigate().back();
	     }

	     //This method Refresh the current page.
	     public void navigateRefresh(){
	         driver.navigate().refresh();
	     }
	  
	    //This method finds the element by xpath given as argument and return it as an object of type WebElement.
	    public WebElement findElementByXpath(String xpath){
	        try{
//	            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	            WebElement element = driver.findElement(By.xpath(xpath));
	            return element;
	        }catch(WebDriverException e){
	            e.printStackTrace();
	            return null;
	        }
	    }
       
	    //This method is used to choose or select an option given under any dropdowns.It takes a parameter of String which is one of the Value of Select element
	    public void selectByVisibleText(SelectorType type, String value, String text){
	        try{
	            switch(type){
	                case NAME:
	                    Select oselect1 = new Select(driver.findElement(By.name(value)));
	                    oselect1.selectByVisibleText(text);
	                    break;
	                case ID:
	                    Select oselect2 = new Select(driver.findElement(By.id(value)));
	                    oselect2.selectByVisibleText(text);
	                    break;
	                case CLASS_NAME:
	                    Select oselect3 = new Select(driver.findElement(By.className(value)));
	                    oselect3.selectByVisibleText(text);
	                    break;
	                case XPATH:
	                    Select oselect4 = new Select(driver.findElement(By.xpath(value)));
	                    oselect4.selectByVisibleText(text);
	                    break;
	                case LINK_TEXT:
	                    Select oselect5 = new Select(driver.findElement(By.linkText(value)));
	                    oselect5.selectByVisibleText(text);
	                    break;
	                case TAG_NAME:
	                    Select oselect6 = new Select(driver.findElement(By.tagName(value)));
	                    oselect6.selectByVisibleText(text);
	                default:
	                    break;
	            }

	            }catch(WebDriverException e){
	                e.printStackTrace();
	            }
	        }

	   //This method is used to choose or select an option given under any dropdowns.It takes a parameter of String and int which is one of the Value of Select element.
	    public void selectByIndex(SelectorType type, String name, int index){
	        try{
	            switch(type){
	                case NAME:
	                    Select oselect1 = new Select(driver.findElement(By.name(name)));
	                    oselect1.selectByIndex(index);
	                    break;
	                case ID:
	                    Select oselect2 = new Select(driver.findElement(By.id(name)));
	                    oselect2.selectByIndex(index);
	                    break;
	                case CLASS_NAME:
	                    Select oselect3 = new Select(driver.findElement(By.className(name)));
	                    oselect3.selectByIndex(index);
	                    break;
	                case XPATH:
	                    Select oselect4 = new Select(driver.findElement(By.xpath(name)));
	                    oselect4.selectByIndex(index);
	                    break;
	                case LINK_TEXT:
	                    Select oselect5 = new Select(driver.findElement(By.linkText(name)));
	                    oselect5.selectByIndex(index);
	                    break;
	                case TAG_NAME:
	                    Select oselect6 = new Select(driver.findElement(By.tagName(name)));
	                    oselect6.selectByIndex(index);
	                    break;
	                default:
	            }
	        }catch(WebDriverException e){
	            e.printStackTrace();
	        }
	    }

	   //This method is used to choose or select an option given under any dropdowns.It takes a parameter of String which is one of the Value of Select element.
	    public void selectByValue(SelectorType type, String name, String value){
	        try{
	            switch(type){
	                case NAME:
	                    Select oselect1 = new Select(driver.findElement(By.name(name)));
	                    oselect1.selectByValue(value);
	                    break;
	                case ID:
	                    Select oselect2 = new Select(driver.findElement(By.id(name)));
	                    oselect2.selectByValue(value);
	                    break;
	                case CLASS_NAME:
	                    Select oselect3 = new Select(driver.findElement(By.className(name)));
	                    oselect3.selectByValue(value);
	                    break;
	                case XPATH:
	                    Select oselect4 = new Select(driver.findElement(By.xpath(name)));
	                    oselect4.selectByValue(value);
	                    break;
	                case LINK_TEXT:
	                    Select oselect5 = new Select(driver.findElement(By.linkText(name)));
	                    oselect5.selectByValue(value);
	                    break;
	                case TAG_NAME:
	                    Select oselect6 = new Select(driver.findElement(By.tagName(name)));
	                    oselect6.selectByValue(value);
	                    break;
	                default:
	                    break;
	            }
	        }catch(WebDriverException e){
	            e.printStackTrace();
	        }
	    }

	   //This method is used to javascript given as username and password
	    public void JavascriptExecutor(String username , String password){
	        JavascriptExecutor executor = (JavascriptExecutor)driver;
	        executor.executeScript(username);
	        executor.executeScript(password);
	    }
	   
	    //This method is used to get the window handle of the current window.
	    public String getWindowhandle(){
	        try{
	            String handle= driver.getWindowHandle();
	            return handle;
	        }catch(WebDriverException e){
	            e.printStackTrace();
	            return null;
	        }
	    }

	    //This method is used to get the window handle of all the current windows.
	    public String getWindowhandles(){
	        String handles= driver.getWindowHandle();
	        return handles;
	   }
	  

	     public int getListSizeOfElements(String xpath){
	         try{
	         List<WebElement> elementList = driver.findElements(By.xpath(xpath));
	         return elementList.size();
	         }catch(Exception e){
	             e.printStackTrace();
	             return 0;
	         }
	     }
	   
	    
	    public String getListNameOfElements(String xpath){
	         System.out.println("inside name list of size");
	         try{
	        List<WebElement> elementList = driver.findElements(By.xpath(xpath));
	         System.out.println("cross name");
	        return elementList.toString();
	         }catch(Exception e){
	             e.printStackTrace();
	             return null;
	         }
	    }

	   //This method is used to accept the alert.
	    public Alert alert(){
	        Alert myAlert = driver.switchTo().alert();
	        return myAlert;
	    }
	  
	    //This method is used to return element to be Clickable with webDriverwait and pass 'By' and time.
	    public WebElement elementToBeClickable(By by, int millisec ){
	        WebElement element = null;
	        int sec = convertMilisecToSec(millisec);
	        WebDriverWait wait = new WebDriverWait(driver, sec);
	        element= wait.until(ExpectedConditions.elementToBeClickable(by));
	        return element;
	    }
	    
	    ////This method is used to return presence of element located  with webDriverwait and pass 'By' and time.
	    public WebElement presenceOfElementLocated(By by, int millisec ){
	        WebElement element = null;
	        int sec = convertMilisecToSec(millisec);
	        WebDriverWait wait = new WebDriverWait(driver, sec);
	        element= wait.until(ExpectedConditions.presenceOfElementLocated(by));
	        return element;
	    }
	    
	    //This method is used to return WebElement of visibility of Element located with webDriverwait and pass 'By' and time.
	    public WebElement visibilityOfElementLocated(By by, int millisec ){
	        WebElement element = null;
	        int sec = convertMilisecToSec(millisec);
	        WebDriverWait wait = new WebDriverWait(driver, sec);
	        element= wait.until(ExpectedConditions.elementToBeClickable(by));
	        return element;
	    }
	    
	    //This method is used to return Boolean of element to be Selected with webDriverwait and pass 'By' and time.
	    public Boolean elementToBeSelected(By by, int millisec ){
	        Boolean element = null;
	        int sec = convertMilisecToSec(millisec);
	        WebDriverWait wait = new WebDriverWait(driver, sec);
	        element= wait.until(ExpectedConditions.elementToBeSelected(by));
	        return element;
	    }
	    
	    //This method is used to return WebDriver of Frame to be Available and switch to it  with webDriverwait and pass 'By' and time.
	    public WebDriver frameToBeAvailableAndSwitchToIt(By by, int millisec ){
	        WebDriver element = null;
	        int sec = convertMilisecToSec(millisec);
	        WebDriverWait wait = new WebDriverWait(driver, sec);
	        element= wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
	        return element;
	    }
	    
	    
	    public List<WebElement> presenceOfAllElementsLocatedBy(By by, int millisec ){
	        List<WebElement> element = null;
	        int sec = convertMilisecToSec(millisec);
	        WebDriverWait wait = new WebDriverWait(driver, sec);
	        element= wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	        return element;
	    }

	 
	    public List<WebElement> visibilityOfAllElementsLocatedBy(By by, int millisec ){
	        List<WebElement> element = null;
	        int sec = convertMilisecToSec(millisec);
	        WebDriverWait wait = new WebDriverWait(driver, sec);
	        element= wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	        return element;
	    }

	    //This method is used to wait some time when element is clickable by list of all loctaors using 'By' strategies.
	    public WebElement waitForElementToBeClickable(SelectorType type, String name, int millisec){
	        WebElement element= null;
	        switch(type){
	            case XPATH:
	                element = elementToBeClickable(By.xpath(name), millisec);
	                break;
	            case ID:
	                element = elementToBeClickable(By.id(name), millisec);
	                break;
	            case CLASS_NAME:
	                element = elementToBeClickable(By.className(name), millisec);            
	                break;            
	            case CSS_SELECTOR:
	                element = elementToBeClickable(By.cssSelector(name), millisec);            
	                break;            
	            case LINK_TEXT:
	                element = elementToBeClickable(By.linkText(name), millisec);            
	                break;        
	            case NAME:
	                element = elementToBeClickable(By.name(name), millisec);            
	                break;            
	            case PARTIAL_LINK_TEXT:
	                element = elementToBeClickable(By.partialLinkText(name), millisec);            
	                break;            
	            case TAG_NAME:
	                element = elementToBeClickable(By.tagName(name), millisec);
	                break;
	            default:
	                
	        }
	        return element;
	    }


	    //This method is used to wait for presence Of Element Located by list of all locators using 'By' strategies.
	    public WebElement waitForPresenceOfElementToBeLocated(SelectorType type, String name, int millisec) {
	        WebElement element = null;
	        switch(type){
	            case ID:
	                element = presenceOfElementLocated(By.id(name), millisec);
	                break;
	            case XPATH:
	                element = presenceOfElementLocated(By.xpath(name), millisec);  
	                break;
	            case CLASS_NAME:
	                element = presenceOfElementLocated(By.className(name), millisec);
	                break; 
	            case LINK_TEXT:
	                element = presenceOfElementLocated(By.linkText(name), millisec);  
	                break;
	            case NAME:
	                element = presenceOfElementLocated(By.name(name), millisec);  
	                break;
	            case TAG_NAME:
	                element = presenceOfElementLocated(By.tagName(name), millisec);
	                break;
	            case PARTIAL_LINK_TEXT:
	                element = presenceOfElementLocated(By.partialLinkText(name), millisec);
	                break;
	            default:
	        }
	        return element;
	    }
	    
	    //This method is used to wait for visibility Of Element Located by list of all locators using 'By' strategies.
	    public WebElement waitForVisibilityOfElementToBeLocated(SelectorType type, String name, int millisec) {
	        WebElement element = null;
	        switch(type){
	            case NAME:
	                element = visibilityOfElementLocated(By.name(name), millisec);
	                break;
	            case ID:
	                element = visibilityOfElementLocated(By.id(name), millisec);       
	                break;
	            case LINK_TEXT:
	                element = visibilityOfElementLocated(By.linkText(name), millisec);       
	                break;
	            case PARTIAL_LINK_TEXT:
	                element = visibilityOfElementLocated(By.partialLinkText(name), millisec);       
	                break;
	            case TAG_NAME:
	                element = visibilityOfElementLocated(By.tagName(name), millisec);
	                break;
	            case CLASS_NAME:
	                element = visibilityOfElementLocated(By.className(name), millisec);       
	                break;
	            case XPATH:
	                element = visibilityOfElementLocated(By.xpath(name), millisec);       
	                break;
	            default:
	                break;
	        }
	        return element;
	    }
	   
	    //This method is used to wait for Element to be Selected by list of all locators using 'By' strategies.
	    public Boolean waitForElementToBeSelected(SelectorType type, String name, int millisec) {
	        Boolean element = null;
	        switch(type){
	            case NAME:
	                element = elementToBeSelected(By.name(name), millisec);
	                break;
	            case ID:
	                element = elementToBeSelected(By.id(name), millisec);       
	                break;
	            case LINK_TEXT:
	                element = elementToBeSelected(By.linkText(name), millisec);       
	                break;
	            case PARTIAL_LINK_TEXT:
	                element = elementToBeSelected(By.partialLinkText(name), millisec);       
	                break;
	            case TAG_NAME:
	                element = elementToBeSelected(By.tagName(name), millisec);
	                break;
	            case CLASS_NAME:
	                element = elementToBeSelected(By.className(name), millisec);       
	                break;
	            case XPATH:
	                element = elementToBeSelected(By.xpath(name), millisec);       
	                break;
	            default:
	        }
	        return element;
	    }

	     //This method is used to wait for frame To Be Available And Switch To It by list of all locators using 'By' strategies and return driver.
	    public WebDriver waitForFrameToBeAvailableAndSwitchToIt(SelectorType type, String name, int millisec) {
	        WebDriver driver = null;
	        switch(type){
	            case NAME:
	                driver = frameToBeAvailableAndSwitchToIt(By.name(name), millisec);
	                break;
	            case ID:
	                driver = frameToBeAvailableAndSwitchToIt(By.id(name), millisec);       
	                break;
	            case LINK_TEXT:
	                driver = frameToBeAvailableAndSwitchToIt(By.linkText(name), millisec);       
	                break;
	            case PARTIAL_LINK_TEXT:
	                driver = frameToBeAvailableAndSwitchToIt(By.partialLinkText(name), millisec);       
	                break;
	            case TAG_NAME:
	                driver = frameToBeAvailableAndSwitchToIt(By.tagName(name), millisec);
	                break;
	            case CLASS_NAME:
	                driver = frameToBeAvailableAndSwitchToIt(By.className(name), millisec);       
	                break;
	            case XPATH:
	                driver = frameToBeAvailableAndSwitchToIt(By.xpath(name), millisec);       
	                break;
	            default:
	        }
	        return driver;
	    }

	   //This method is used to wait for presence Of All Elements Located by list of all locators using 'By' strategies and return List<WebElement>.
	    public List<WebElement> waitForPresenceOfAllElementsLocatedBy(SelectorType type, String name, int millisec) {
	        List<WebElement> element = null;
	        switch(type){
	            case NAME:
	                element = presenceOfAllElementsLocatedBy(By.name(name), millisec);
	                break;
	            case ID:
	                element = presenceOfAllElementsLocatedBy(By.id(name), millisec);       
	                break;
	            case LINK_TEXT:
	                element = presenceOfAllElementsLocatedBy(By.linkText(name), millisec);       
	                break;
	            case PARTIAL_LINK_TEXT:
	                element = presenceOfAllElementsLocatedBy(By.partialLinkText(name), millisec);       
	                break;
	            case TAG_NAME:
	                element = presenceOfAllElementsLocatedBy(By.tagName(name), millisec);
	                break;
	            case CLASS_NAME:
	                element = presenceOfAllElementsLocatedBy(By.className(name), millisec);       
	                break;
	            case XPATH:
	                element = presenceOfAllElementsLocatedBy(By.xpath(name), millisec);       
	                break;
	            default:
	        }
	        return element;
	    }
	    
	    //This method is used to wait for visibility Of All Elements Located by list of all locators using 'By' strategies and return List<WebElement>.
	    public List<WebElement> waitForVisibilityOfAllElementsLocatedBy(SelectorType type, String name, int millisec) {
	        List<WebElement> element = null;
	        switch(type){
	            case NAME:
	                element = visibilityOfAllElementsLocatedBy(By.name(name), millisec);
	                break;
	            case ID:
	                element = visibilityOfAllElementsLocatedBy(By.id(name), millisec);       
	                break;
	            case LINK_TEXT:
	                element = visibilityOfAllElementsLocatedBy(By.linkText(name), millisec);       
	                break;
	            case PARTIAL_LINK_TEXT:
	                element = visibilityOfAllElementsLocatedBy(By.partialLinkText(name), millisec);       
	                break;
	            case TAG_NAME:
	                element = visibilityOfAllElementsLocatedBy(By.tagName(name), millisec);
	                break;
	            case CLASS_NAME:
	                element = visibilityOfAllElementsLocatedBy(By.className(name), millisec);       
	                break;
	            case XPATH:
	                element = visibilityOfAllElementsLocatedBy(By.xpath(name), millisec);       
	                break;
	            default:
	        }
	        return element;
	    }

	    public void javascriptExecutorWithArguments(String text, String id){
	        JavascriptExecutor js = (JavascriptExecutor)driver;
	        js.executeScript("arguments[0].setAttribute('value', '" +text+ "')", id);
	    }

	   //This method is used to dismiss the alert
	    public Alert dismiss(){
	        Alert confAlert = driver.switchTo().alert();
	        return confAlert;
	    }
	    

	   //This method is used to gets the all options belonging to the Select tag. It takes no parameter and returns List<WebElements>.
	    public List<WebElement> getOptions(String id){
	        Select oSelect = new Select(driver.findElement(By.id(id)));
	        List <WebElement> elementCount = oSelect.getOptions();
	        return elementCount;
	    }


	   //This method is used to quit the driver.
	    public void driverQuit(){
	        driver.quit();
	    }
	
}
