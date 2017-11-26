import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ReportGenrate {
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	private BufferedReader br;
	
	
	
	
	public void startReport()
	{   System.out.println("Inside before test");
		htmlReporter = new ExtentHtmlReporter("/home/netstorm/MyOwnReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS","Ubuntu12");
		extent.setSystemInfo("Host Name","automation");
		extent.setSystemInfo("Environment", "QA-Automation");
		extent.setSystemInfo("Test Cycle Number", "170911_175217");
		extent.setSystemInfo("Total TestCase ", "361");
		System.out.println("extent set");
		htmlReporter.config().setDocumentTitle("Automation Smoke Test Result");
		htmlReporter.config().setReportName("NetStorm Smoke Result");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		System.out.println("config set");
	}

   

	
   public void genTestCase() throws Exception{
	   FileReader file = new FileReader("testcase.txt");	
		BufferedReader br  =  new BufferedReader(file);	
		String ln = null;
		while((ln = br.readLine())!= null){
			if (ln.startsWith("TEST_CASE_NAME")){
				System.out.println(ln);
				String [] ln1 = ln.split(" ");
				extent.createTest(ln1[1]);
				
			}
		}
			     
		  extent.flush();
		  System.out.println("gen complte");
   }
   public void genReport() throws Exception{
	  System.out.println("inside gen report");
         
		FileReader file = new FileReader("data.txt");	
		BufferedReader br  =  new BufferedReader(file);	
		String ln = null;
        test = extent.createTest("NS SMOKE REPORT", "Smoke Result testcases");
        test.getModel().setStatus(Status.PASS);
        
 	   String startDateString = "10/11/17 12:11:22";
 	   DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss"); 
 	   Date startDate = df.parse(startDateString);
       test.getModel().setStartTime(startDate);
	  
     
        while((ln = br.readLine())!= null){	
	         
	    	if (ln.contains("pass")){	
	    		String [] ln1 =ln.split(",");	
	    		String line = ln1[0] + " : " + ln1[5];
	    		test.log(Status.PASS, MarkupHelper.createLabel(line, ExtentColor.GREEN));
	    		System.out.println("Pass:"+line);
	    	}else if (ln.contains("fail")){		
	    		String [] ln1 =ln.split(",");	
		        String line = ln1[0] + " : " + ln1[5];
		        test.log(Status.ERROR, MarkupHelper.createLabel(line, ExtentColor.RED));
	            System.out.println("Fail:"+line);			
	        }else{	
	        	System.out.println("NSFail");			
	        }	
	     }   
      
         String endDateString = "10/11/17 02:45:22";
	     DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss"); 
	  	 Date endtDate = df1.parse(endDateString);
	     test.getModel().setEndTime(endtDate); 

   }

 
   
   public static void main(String argp[]) throws Exception{
	   ReportGenrate gen = new ReportGenrate();
	   gen.startReport();
	   gen.genReport();
	   gen.genTestCase();
	 
	   
   }
}
