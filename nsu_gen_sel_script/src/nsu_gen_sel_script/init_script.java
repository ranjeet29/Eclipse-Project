package nsu_gen_sel_script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class init_script {
	

	 Logger log = Logger.getLogger(init_script.class.getName());
	 public String sname;
	
	 public init_script(){
			log.setLevel(Level.INFO);
			}
	public void initscriptwhar(String browser, String host, int s_port , int p_port ) throws IOException{
		
		System.out.println(" ");
		String capss = null;
		
	    String newFileName = ""+Main.path+"/init_script.java";
	    log.info("Init_Script :"+newFileName);
	    
	    String content = "package com.cavisson.scripts."+sname+"" ;
	    // log.info("content:"+content);
	    
	    if(browser.equalsIgnoreCase("firefox")){
	    	log.info("Creating Init_script for firefox browser");
	    	String hub_url = "http://"+host+":"+s_port+"/wd/hub"; 
	    	capss = "driver = capabilitySet.initiateRemoteFirefoxBrowser(nsApi, \""+hub_url+"\",\""+host+"\" ,"+p_port+" , true);";
	    }else if(browser.equalsIgnoreCase("chrome")){
	    	log.info("Creating init_script for chrome browser");
	    	String hub_url = "http://"+host+":"+s_port+"/wd/hub";
	    	capss = "driver = capabilitySet.initiateRemoteChromeBrowser(nsApi, \""+hub_url+"\" ,"+p_port+" , true);";
	    }else if (browser.equalsIgnoreCase("IE")){
	    	log.info("Creating init_script for Intrenet Explorer browser");
	    	String hub_url = "http://"+host+":"+s_port+"/wd/hub";
	    	capss = "driver = capabilitySet.initiateRemoteInternetExplorerBrowser(nsApi, \""+hub_url+"\" ,"+p_port+" , true);";	    	
	    }
	    else{
	    	System.out.println("Nothing to print ");
	    }
	      
	  
	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      
	      try {
	    	  InputStream stream = init_script.class.getResourceAsStream("/init_script.javaa");
	          System.out.println(stream != null);
			  br = new BufferedReader(new InputStreamReader(stream));
	          bw = new BufferedWriter(new FileWriter(newFileName));
	          
	          String line;
	          while ((line = br.readLine()) != null) {
	             if (line.contains("package com.cavisson.scripts."))
	                line = line.replace("package com.cavisson.scripts.", ""+content);
	               line = line.replace("driver = capabilitySet.", capss );
	               bw.write(line+"\n");
	              
	                
	          }
	          
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }finally{
	    	   if(br != null)
	               br.close();
	    	   if(bw != null){
	    		   log.info("init_script file created successfully");
	    		   bw.close();}
	       }
		
	}
	
	
	
	
	public init_script initScriptWhar(String sname , String browser, String host, int s_port , int p_port) throws IOException{
		this.sname = sname;
		initscriptwhar(browser , host , s_port , p_port);
		return this;	
	}

}
