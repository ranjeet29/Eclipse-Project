package Demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.io.PrintStream;
import com.cavisson.cavmon.client.*;

public class Cmon_Utility_Check {
	
	public static AdminServerInterf admnSrv;
	public static PrintStream ps;
	public static PrintStream err;
	public static StringBuffer sout;
	
	
	 
    @Test(description="going to get info about CmonInfo")
    public void getCmonInformation() {
	   boolean cmoninfo = admnSrv.getCmonInfo(ps);
	  
	    if(!cmoninfo){
	        System.out.println(sout.toString());
	      }
	     else {
	        ps.println();
	        
	      } 
  }
    
    
  public static void uploadFile(){
	  boolean status = admnSrv.runCommand("ps -ef",ps);
	  if(status){
		  ps.println();
	  }else
		  System.out.println(sout.toString());
  }
  
  @BeforeMethod
  public void beforeMethod() {
	 sout = new StringBuffer();
	//public AdminServer(String serverAddress, int port ,StringBuffer outMsg, boolean isLoggingEnable) 
	 admnSrv = new AdminServer("10.10.30.13", 7889, sout, true);
     ps = new PrintStream(System.out);
     err= new PrintStream(System.err);

  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("this is hello ");
  }

}
