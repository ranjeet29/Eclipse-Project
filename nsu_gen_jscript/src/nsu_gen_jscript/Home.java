package nsu_gen_jscript;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home {
	
	
	 
    Logger log = Logger.getLogger(Home.class.getName());
    public String sname;
	
	public Home(){
		log.setLevel(Level.INFO);
		}

	public void homewhar() throws IOException{
		System.out.println(" ");
		String oldFileName = "/home/Script/Home.java";
	    String newFileName = ""+Main.path+"/Home.java";
	    log.info("NsFlowFile :"+newFileName);
	    
	      
	    String content = "package com.cavisson.scripts."+sname+"" ;
	   // log.info("content:"+content);

	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      try {
	          br = new BufferedReader(new FileReader(oldFileName));
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
	    		   log.info("capabilitySet file created successfully");
	    		   bw.close();}
	    	       
	       }
	
	}
	
	public void homewohar() throws IOException{
		System.out.println(" ");
		String oldFileName = "/home/Script/wohar/Home.java";
	    String newFileName = ""+Main.path+"/Home.java";
	    log.info("NsFlowFile :"+newFileName);
	    
	      
	    String content = "package com.cavisson.scripts."+sname+"" ;
	   // log.info("content:"+content);

	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      try {
	          br = new BufferedReader(new FileReader(oldFileName));
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
	    		      log.info("Home file created successfully");
	    		   bw.close();}
	    	       
	       }
	
	}
	
	public void home(String sname) throws IOException{
		this.sname = sname;
	    homewhar();  
	}

	public void homewohar(String sname) throws IOException{
		this.sname = sname;
	    homewohar();  
	}

}
