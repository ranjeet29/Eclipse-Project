
import org.openqa.selenium.By;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainClas  extends BaseAndroidTest {
    
	public static void main(String[] args) throws Exception {
		MainClas cls = new MainClas();
		cls.setUp();
		cls.mainPageTest();
		cls.tearDown();
		
		
	} 
	
    public void setUp() throws Exception {
        setUpTest();
    }
   
    public void tearDown()
    {
        quitAppiumSession();
    }


   
    public void mainPageTest() throws IOException, InterruptedException {
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        takeScreenshot("start");
        wd.findElement(By.xpath("//android.widget.RadioButton[@text='Use Testdroid Cloud']")).click();
        wd.findElement(By.xpath("//android.widget.EditText[@resource-id='com.bitbar.testdroid:id/editText1']")).sendKeys("John Doe");
        takeScreenshot("after_adding_name");
        wd.navigate().back();
        wd.findElement(By.xpath("//android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[1]")).click();
        takeScreenshot("after_answer");
    }

}

