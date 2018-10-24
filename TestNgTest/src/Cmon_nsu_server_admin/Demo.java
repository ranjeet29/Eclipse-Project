package Cmon_nsu_server_admin;
import java.io.PrintStream;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import org.testng.Assert;
import com.cavisson.cavmon.client.AdminServer;
import com.cavisson.cavmon.client.AdminServerInterf;

public class Demo {
	
	public static AdminServerInterf admnSrv;
	public static PrintStream ps;
	public static PrintStream err;
	public static StringBuffer sout;
	
  
   @BeforeMethod
   public static void beforeClass() {
	   System.out.println("Before Methods");
	   sout = new StringBuffer();
		//public AdminServer(String serverAddress, int port ,StringBuffer outMsg, boolean isLoggingEnable) 
		 admnSrv = new AdminServer("10.10.30.13", 7889, sout, true);
	     ps = new PrintStream(System.out);
	     err = new PrintStream(System.err);
   }

   @AfterMethod(description="Going to close the connection")
   public static void  afterClass() {
	  System.out.println("after class");
	  try{
     Assert.assertNotNull(admnSrv);
     if(admnSrv != null)
    	System.exit(0);
	  }catch(AssertionError e){
		  e.printStackTrace();
	  }
   }

   @Test(description="Get Cmon Information",successPercentage=100)
   public void getCmonInformation() {
	  try{
      System.out.println("Get Cmon Information");
      boolean cmoninfo = admnSrv.getCmonInfo(ps);
      Assert.assertTrue(cmoninfo);
      Assert.assertEquals(cmoninfo, true); 
       if(!cmoninfo)
           System.err.print(sout.toString());
        else
           ps.println();
	  }catch(AssertionError e){
		  e.printStackTrace();
	  }
   }
   
   @Test(description="Going to upload File on Server" , successPercentage=100)
   public static void uploadFile(){
       try{
	   System.out.println("Giong to Upload file on Server");
	//    In Pass Case
       boolean status = admnSrv.uploadFile("/home/netstorm/work/server_Admin.txt", "/home/netstorm/");
       System.out.println("print status");
       Assert.assertTrue(status);
       Assert.assertEquals(true, status);
    //   In Fail Case
       boolean failstatus = admnSrv.uploadFile("/home/netstorm/work/Admin.log", "/home/netstorm");
       Assert.assertFalse(failstatus);
       Assert.assertEquals(failstatus, false);
       if(status){
           ps.println();
       }else
           System.err.println(sout.toString());
       }catch(Exception e){
    	   e.printStackTrace();
       }
   }

   
   @Test(description="Going to run Command on Server" ,successPercentage=100)
 
   public static void runcommand(String command1 , String command2){
       try{
       System.out.println("Going to run Command on Server");
       // In Pass Case
       boolean status = admnSrv.runCommand(command1 , ps);
       Assert.assertTrue(status);
       Assert.assertEquals(status , true);
       if(!status)
           System.err.println(sout.toString());
       else
           ps.println();
       }catch(AssertionError e){e.printStackTrace();}

       try{
       boolean st= true;
       boolean failstatus = admnSrv.runCommand(command2 , ps);
       byte status =admnSrv.downloadFile(null, null, false);
       boolean stauts=admnSrv.showJavaHeap(ps);
      
       admnSrv.checkPort("80", ps);
       }catch(AssertionError e){e.printStackTrace();}

   }
   
   
   

}
