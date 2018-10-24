package jsonreadfile;

import java.io.File;
import java.io.FileReader;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonRead {
	
	public static void main(String arg []) throws Exception {
		
		JSONParser prs = new JSONParser() ;
		JSONArray arr = (JSONArray) prs.parse(new FileReader(new File("hello.json")));
		
		for(Object obj : arr){
			System.out.println(obj);
			JSONObject obj1  = (JSONObject) obj;
		    System.out.println("name : " + obj1.get("name"));
		    System.out.println("Car : "+ obj1.get("cars"));
		    
		  
		   JSONArray cararr = (JSONArray) obj1.get("cars");
		   System.out.println(cararr);
		   
		   for ( Object objcar : cararr)
		   {
			  System.out.println(objcar); 
		   }
   	
		    }		    
			
		}
			
		}


