package jsonreadfile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class newFileread {
	
	public static void main(String arg[]) throws FileNotFoundException, IOException, ParseException{
		Object obj = new JSONParser().parse(new FileReader("action.json"));
		JSONObject jo = (JSONObject) obj;
		  
		  JSONArray ja = (JSONArray) jo.get("actions");
		        
		        // iterating actions
		        Iterator<Map.Entry> itr2 = ja.iterator();
		        Iterator<Map.Entry> itr1;
		        
		        while (itr2.hasNext()) 
		        {
		         itr1 = ((Map) itr2.next()).entrySet().iterator();
		            while (itr1.hasNext()) {
		                Map.Entry pair = itr1.next();
		              //  System.out.print(pair);
		                System.out.println(pair.getKey() + " : " + pair.getValue());
		               
		            }
		            System.out.println("hello");
		        }
	}

}
