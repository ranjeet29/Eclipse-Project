package nsu_gen_jscript;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class init_script {
	

	 Logger log = Logger.getLogger(init_script.class.getName());
	 public String sname;
	
	 public init_script(){
			log.setLevel(Level.INFO);
			}
	public void initscriptwhar(String device_name , String device_version , String Platform_name , String device_ip , String driver_ip ,int p_port,  String apactivity , String appath) throws IOException{
		
		System.out.println(" ");
		String oldFileName = "/home/Script/init_script.java";
	    String newFileName = ""+Main.path+"/init_script.java";
	    log.info("Init_Script :"+newFileName);
	    
	    String content = "package com.cavisson.scripts."+sname+"" ;
	   // log.info("content:"+content);
	    
	  //  String caps = "driver = capabilitySet.setCapabilities("+device_name+","+device_version+ ","+Platform_name+ ","+device_ip+","+driver_ip+","+p_port+","+apactivity+");";
       String capss = "driver = capabilitySet.setCapabilities(nsApi , \""+device_name+"\" , \""+device_version+"\" , \""+Platform_name+"\" , \""+device_ip+"\", \""+driver_ip+"\"  , \""+apactivity+"\" , \""+p_port+"\" , app, \""+Device_Selection.udid+"\");" ; 
	   String apppath1 = "File app=new File(\""+appath+"\");";
	    
	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      
	      try {
	    	  br = new BufferedReader(new FileReader(oldFileName));
	          bw = new BufferedWriter(new FileWriter(newFileName));
	          
	          String line;
	          while ((line = br.readLine()) != null) {
	             if (line.contains("package com.cavisson.scripts."))
	                line = line.replace("package com.cavisson.scripts.", ""+content);
	                line = line.replace("File app=new File(\"appApth\");", apppath1 );
	               line = line.replace("driver = capabilitySet.setCapabilities();", capss );
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
	
	
	public void initscriptwohar(String device_name , String device_version , String Platform_name , String device_ip , String driver_ip , String apactivity , String appath) throws IOException{
		
		System.out.println(" ");
		String oldFileName = "/home/Script/init_script.java";
	    String newFileName = ""+Main.path+"/init_script.java";
	    log.info("Init_Script :"+newFileName);
	    
	    String content = "package com.cavisson.scripts."+sname+"" ;
	   // log.info("content:"+content);
	    
       String capss = "driver = capabilitySet.setCapabilities(nsApi , \""+device_name+"\" , \""+device_version+"\" , \""+Platform_name+"\" , \""+device_ip+"\", \""+driver_ip+"\" , \""+apactivity+"\",null, app , \""+Device_Selection.udid+"\");" ; 
	   String apppath1 = "File app=new File(\""+appath+"\");";
	    
	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      
	      try {
	    	  br = new BufferedReader(new FileReader(oldFileName));
	          bw = new BufferedWriter(new FileWriter(newFileName));
	          
	          String line;
	          while ((line = br.readLine()) != null) {
	             if (line.contains("package com.cavisson.scripts."))
	                line = line.replace("package com.cavisson.scripts.", ""+content);
	                line = line.replace("File app=new File(\"appApth\");", apppath1 );
	               line = line.replace("driver = capabilitySet.setCapabilities();", capss );
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
	
	public init_script initScriptWOhar(String sname , String device_name , String device_version , String Platform_name , String device_ip , String driver_ip , String apactivity , String appath ) throws IOException{
		this.sname = sname;
		initscriptwohar( device_name , device_version , Platform_name , device_ip , driver_ip , apactivity , appath);
		return this;	
	}
	
	public init_script initScriptWhar(String sname , String device_name , String device_version , String Platform_name , String device_ip , String driver_ip ,int p_port,  String apactivity , String appath ) throws IOException{
		this.sname = sname;
		initscriptwhar( device_name , device_version , Platform_name , device_ip , driver_ip , p_port , apactivity , appath);
		return this;	
	}

}
