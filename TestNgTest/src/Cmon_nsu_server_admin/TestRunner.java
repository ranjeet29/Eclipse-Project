package Cmon_nsu_server_admin;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
  
	public static void main(String arg[]){
		Result result = JUnitCore.runClasses(Demo.class);
		
		for(Failure failure: result.getFailures()){
			System.out.println("failure"+failure.toString());
		}
		System.out.println("result"+result.wasSuccessful());
	}
}
