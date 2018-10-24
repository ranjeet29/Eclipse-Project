package com.cavisson.automation.RDTAutomation.generics;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class loggerLoad {
   
	 public static  Logger config(String classname ){
		    Logger log = Logger.getLogger(classname);
		    String log4jConfPath = "/home/netstorm/workbench/RDT_Automation/src/log4j.properties";
		    PropertyConfigurator.configure(log4jConfPath);
		    
		   return log;
	   }
}
