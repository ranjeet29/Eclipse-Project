package nsu_gen_sel_script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Flow {
	
	
	 
    Logger log = Logger.getLogger(Flow.class.getName());
    public String sname;
	
	public Flow(){
		log.setLevel(Level.INFO);
		}
    
	ActionFile actionflow = new ActionFile(sname);
	
	public void homewhar() throws IOException{
		System.out.println(" ");
		
	    String newFileName = ""+Main.path+"/Flow.java";
	    log.info("FlowFile :"+newFileName);
	    
	      
	    String content = "package com.cavisson.scripts."+sname+"" ;
	   // log.info("content:"+content);

	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      try {
	    	  InputStream stream = Flow.class.getResourceAsStream("/Home.javaa");
	          System.out.println(stream != null);
			  br = new BufferedReader(new InputStreamReader(stream));
	          bw = new BufferedWriter(new FileWriter(newFileName));
	          String line;
	          while ((line = br.readLine()) != null) {
	             if (line.contains("package com.cavisson.scripts."))
	                line = line.replace("package com.cavisson.scripts.", ""+content);
	                bw.write(line+"\n");
	                
	                
	          }
	       } catch (Exception e) {
	          e.printStackTrace();
	       }finally{
	    	   if(br != null)
	               br.close();
	    	   if(bw != null){
	    		   log.info("Flow file created successfully");
	    		   bw.close();}
	    	       
	       }
	
	}
	
	
	
	public void flowfile(String sname , String actionfile) throws IOException{
		this.sname = sname;
		System.out.println("action file:"+actionfile);
		if ( actionfile == null ){
			 homewhar(); 
		}else if (actionfile != null ){
			readjsonFile(actionfile);
		}
		
		
	    
	}
     
	
	public Flow readjsonFile(String actionfile){
		
		 try {
			    
			    actionflow.createOrigFlowFromTemplate();
			    actionflow.packagename(sname);
			    
	        	Object obj = new JSONParser().parse(new FileReader(actionfile));
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
		            	case "Scroll":inputFunction(itr1 , "scroll");break;	// done
		            	
		            	default:System.out.println("Action Not Implemented");break;
		            	}
		               
		            }
		            
		            System.out.println(" ");
		        }
	            actionflow.writallaction();
		        actionflow.flowend();
	        } catch (Exception e) {
	        	System.out.println("Please pass a valid json file");
	           // e.printStackTrace();
	        }
	        
		
		return this;
		
	}
	
    public  void browserFunction(Iterator<Map.Entry> itr1 , String eventtype){
    	
    	String url = null ;
        String page = null ;
        boolean har = false ;
        boolean trans = false;
       
        
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
            if (pair.getKey().equals("trans")){
           	 trans = (boolean)pair.getValue();
           }
          // System.out.println(pair.getKey() + " from function : " + pair.getValue());
    	}
    	
    	if (eventtype.equalsIgnoreCase("browse")){
    		/*System.out.println("url :"+url);
    		System.out.println("page :"+page);
    		System.out.println("har capture :" +har);
    		System.out.println("trans :" +trans);*/
    		actionflow.browseAction(url , page , har , trans);
    		
    		// browser function call 
    	}
    	
    }
    
	
	 public void inputFunction(Iterator<Map.Entry> itr1 , String eventtype){
     	
     	String xpath = null;
         String page = null;
         boolean har = false ;
         String data = null;
         String axis = null;
         boolean trans = false ;
         
         
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
             if (pair.getKey().equals("trans")){
               	 trans = (boolean)pair.getValue();
             }
           // System.out.println(pair.getKey() + " from function : " + pair.getValue());     
     	}
     	
     	if (eventtype.equalsIgnoreCase("click")){
     		/*System.out.println("Click event");
     		System.out.println("xpath :"+xpath);
     		System.out.println("page :"+page);
     		System.out.println("har :"+har);
     		System.out.println("trans :" +trans);*/
     		
     		actionflow.clickAction(xpath , page , har , trans);
     	    	
     	
     		
     	}else if (eventtype.equalsIgnoreCase("input")){
     		/*System.out.println("Input event");
     		System.out.println("xpath :"+xpath);
     		System.out.println("page :"+page);
     		System.out.println("har :"+har);
     		System.out.println("data :"+data);
     		System.out.println("trans :" +trans)*/;
     		actionflow.inputAction(xpath , page , data ,har , trans);
     		
     	// input function call
     		
     	}else if (eventtype.equalsIgnoreCase("scroll")){
     		/*System.out.println("Scroll event");
     		System.out.println("page :"+page);
     		System.out.println("har :"+har);
     		System.out.println("axis :"+axis);
     		System.out.println("trans :" +trans);*/
     		actionflow.scrollAction(axis , page , data ,har , trans);
     		//scroll function call
     	}else{
     		System.out.println("Not Found");
     	}
     	
     	
     	
     	
     }
	
	

}
