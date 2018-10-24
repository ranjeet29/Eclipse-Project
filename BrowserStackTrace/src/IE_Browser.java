
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;

public class IE_Browser {

	public static final String USERNAME = "saishubham1";
	public static final String AUTOMATE_KEY = "NsURxkBQRy59HuZHeeZP";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
  
public static void main(String[] args) throws Exception {

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("browser", "IE");
    caps.setCapability("browser_version", "11.0");
    caps.setCapability("os", "Windows");
    caps.setCapability("os_version", "10");
    caps.setCapability("resolution", "1024x768");
    caps.setCapability("browserstack.debug", true);
    caps.setCapability("browserstack.networkLogs", true);
    caps.setCapability("enablePerformanceLogging",true);
    caps.setCapability("build", "chrome");
    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
    driver.get("http://www.4wheelparts.com");
    SessionId session = ((RemoteWebDriver)driver).getSessionId();
    System.out.println("Session id: " + session.toString());
  
    System.out.println(driver.getTitle());
    driver.findElement(By.id(""));
    String apiurl=String.format("https://api.browserstack.com/automate/builds/ie/sessions/%s/networklogs" , session.toString());
    System.out.println(apiurl);
    
   // IE_Browser ie = new IE_Browser();
 //   ie.harFile(session.toString());
 //   ie.calculateHarStats(driver);
    
   
    driver.quit();

  }

   private void harFile(String sessionid) throws Exception {
	//   String url = "https://api.browserstack.com/automate/builds/ie/sessions/"+sessionid+"/networklogs";
       String url = "https://api.browserstack.com/automate/builds/ie/sessions/db2c0c6efaccf004a47cb5be18a0c8467c83c43d/networklogs";
	   String HarFileName = "/home/netstorm/Desktop/IEHar.har";
       String name = "saishubham1";
       String password = "NsURxkBQRy59HuZHeeZP";
       String authString = name + ":" + password;
       //String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
       //String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
       String authStringEnc = new String(Base64.encodeBase64(authString.getBytes()));
       System.out.println("Base64 encoded auth string: " + authStringEnc);
       Client restClient = Client.create();
       WebResource webResource = restClient.resource(url);
       ClientResponse resp = webResource.accept("application/json")
                                        .header("Authorization", "Basic " + authStringEnc)
                                        .get(ClientResponse.class);
       if(resp.getStatus() != 200){
           System.err.println("Unable to connect to the server");
       }
       String output = resp.getEntity(String.class);

       System.out.println("Output from Server .... \n");
    //   System.out.println(output);
       
       FileOutputStream fos = new FileOutputStream(new File(HarFileName));
       fos.write(output.getBytes());
       
       FileReader file = new FileReader(HarFileName);
       BufferedReader br = new BufferedReader(file);
       FileWriter outfile= null;
       BufferedWriter buffwriter = null;

       String ln = null;
       String ln_new = null;
       String ln3 = null;
       ln = br.readLine();
       if (ln.contains("serverIPAddress")) {
           ln3=(new StringBuilder()).append("\"_cav_cache_provider\": \"Origin\",").append("\"_cav_cache_state\": \"Hit\",").append("\"serverIPAddress\"").toString();
           System.out.println("ln_new: "+ln3);
       }
       if ( ln.contains("_error")) {
           ln_new = ln.replaceAll(",\"_error\":\"No response received\"", " ").replaceAll(",\"_error\":\"Unable to connect to host\"", " ").replaceAll(",\"_error\":\"Unable to resolve host: [a-z,A-Z,0-9,.,:,-]*\""," ").replaceAll("\"serverIPAddress\"", ln3);

       }else{
             ln_new = ln.replaceAll("\"serverIPAddress\"", ln3);
            }
       System.out.println("ln_new: "+ln_new);
       outfile = new FileWriter(HarFileName);
       buffwriter = new BufferedWriter(outfile);
       buffwriter.write(ln_new);
       buffwriter.close();

       
       String harStr = "";
     
       try {
           br = new BufferedReader(new FileReader(HarFileName));
           StringBuilder sb = new StringBuilder();

           while (true) {
               String line = br.readLine();
               if(line == null)
                   break;
               sb.append(line);
           }
           harStr = sb.toString();
           br.close();

       } catch(Exception e){
           try{
               br.close();
           }catch(Exception ex){
           }
           harStr = "";
       }
   
       harStr = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(harStr));
       harStr = "onInputData(\n" +harStr+ "\n);";

       //name of harp file
       String harpFileName = HarFileName +"p";

       //write harp file
       BufferedWriter writer = null;
       try{
           writer = new BufferedWriter(new FileWriter(harpFileName));
           writer.write(harStr);
           writer.close();
       }catch(Exception e){
            e.printStackTrace(); 
        } 
        
       
   }
   
   
   private void calculateHarStats(WebDriver driver) {
	
	   long onContentLoadTime = 0L;
       long onLoadTime = 0L;
	   PerformanceTiming pt = new PerformanceTiming((JavascriptExecutor)driver);
	    onLoadTime= pt.getOnLoadTime();
       onContentLoadTime = pt.getOnContentLoadTime();
       System.out.println("onContentLoadTime "+onContentLoadTime);
       System.out.println("onLoadTime "+onLoadTime);

}
      
   
}
