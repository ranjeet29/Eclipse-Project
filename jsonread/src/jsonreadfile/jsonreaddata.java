package jsonreadfile;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

 
public class jsonreaddata {
 
    public static void main(String[] args) throws Exception  {
 
        try {
    
        	Object obj = new JSONParser().parse(new FileReader("action.json"));
    		JSONObject jo = (JSONObject) obj;
    		  
    		JSONArray ja = (JSONArray) jo.get("actions");
    		Iterator<Map.Entry> itr2 = ja.iterator();
	        Iterator<Map.Entry> itr1;
	        
	        while (itr2.hasNext()) 
	        {
	         itr1 = ((Map) itr2.next()).entrySet().iterator();
	            while (itr1.hasNext()) {
	                Map.Entry pair = itr1.next();
	                
	                String action = (String) pair.getValue();
	                switch(action)
	            	{
	            	
	            	case "Browse":browserFunction(itr1, "browse");break;  // done
	            	case "Click":inputFunction(itr1 , "click");break;     // done
	            	case "Input":inputFunction(itr1 ,"input");break;   // done
	            	case "Scroll":inputFunction(itr1 , "scroll");break;	
	            	
	            	default:System.out.println("");break;
	            	}
	               
	            }
	            
	            System.out.println(" ");
	        }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
    
        public static void browserFunction(Iterator<Map.Entry> itr1 , String eventtype){
        	
        	String url = null ;
            String page = null ;
            boolean har = false ;
           
            
        	while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                
                if (pair.getKey().equals("url")){
                	 url = (String) pair.getValue();
                }
                if (pair.getKey().equals("page")){
                	 page = (String) pair.getValue();
                }
                if (pair.getKey().equals("archive")){
                	 har = (boolean)pair.getValue();
                }
              // System.out.println(pair.getKey() + " from function : " + pair.getValue());
        	}
        	
        	if (eventtype.equalsIgnoreCase("browse")){
        		System.out.println("url :"+url);
        		System.out.println("page :"+page);
        		System.out.println("har capture :" +har);
        	}
        	
        }
        
        
        
        public static void inputFunction(Iterator<Map.Entry> itr1 , String eventtype){
        	
        	String xpath = null;
            String page = null;
            boolean har = false ;
            String data = null;
            String axis = null;
            
        	while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                
                if (pair.getKey().equals("byxpath")){
                	xpath = (String) pair.getValue();
                }
                if (pair.getKey().equals("page")){
                	 page =(String) pair.getValue();
                }
                if (pair.getKey().equals("archive")){
                	har =(boolean) pair.getValue();
                }
                if (pair.getKey().equals("info")){
                	 data =(String) pair.getValue();
                }
                if (pair.getKey().equals("option")){
                	 axis = (String) pair.getValue();
                }
              // System.out.println(pair.getKey() + " from function : " + pair.getValue());     
        	}
        	
        	if (eventtype.equalsIgnoreCase("click")){
        		System.out.println("Click event");
        		System.out.println("xpath :"+xpath);
        		System.out.println("page :"+page);
        		System.out.println("har :"+har);
        		
        	}else if (eventtype.equalsIgnoreCase("input")){
        		System.out.println("Input event");
        		System.out.println("xpath :"+xpath);
        		System.out.println("page :"+page);
        		System.out.println("har :"+har);
        		System.out.println("data :"+data);
        		
        	}else if (eventtype.equalsIgnoreCase("scroll")){
        		System.out.println("Scroll event");
        		System.out.println("page :"+page);
        		System.out.println("har :"+har);
        		System.out.println("axis :"+axis);
        		
        	}else{
        		System.out.println("Not Found");
        	}
        	
        	
        	
        	
        }
        
  
        
}
