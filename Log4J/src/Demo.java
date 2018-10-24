import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class Demo {
	

	  
	   public static void main(String[] args)throws IOException,SQLException{
          Logger log = config("Demo");
	      log.debug("Hello this is a debug message");
	      log.info("Hello this is an info message");
	   }
	   
	   public static  Logger config(String classname ){
		    Logger log = Logger.getLogger(classname);
		    String log4jConfPath = "log4j.properties";
		    PropertyConfigurator.configure(log4jConfPath);
		    
		   return log;
	   }
}
