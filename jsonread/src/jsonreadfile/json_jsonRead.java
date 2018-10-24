package jsonreadfile;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class json_jsonRead {
	
	public static void main(String arg []) throws Exception{
		JSONParser prs = new JSONParser() ;
		JSONArray arr = (JSONArray) prs.parse(new FileReader(new File("x.json")));
		
		for(Object o: arr){
			JSONObject obj = (JSONObject) o; 
		//	System.out.println(arr);
			System.out.println("**************************");
			System.out.println("Browser : " + obj.get("browser"));
			System.out.println("Browser Version : "+obj.get("browser_version"));
			System.out.println("OS : "+obj.get("os"));
			System.out.println("OS Version : "+obj.get("os_version"));
			System.out.println("*********************************");
			
			
		}
		
	}

}
