package nsu_gen_jscript;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class registrations {
	
	
	
	 Logger log = Logger.getLogger(registrations.class.getName());
	 public String sname;
	
	 public registrations(){
			log.setLevel(Level.INFO);
			}
	public void rspec() throws IOException{
		
		System.out.println(" ");
		String oldFileName = "/home/Script/registrations.spec";
	    String newFileName = ""+Main.path+"/registrations.spec";
	    log.info("registraction spec :"+newFileName);
	    
	    String content = "package com.cavisson.scripts."+sname+"" ;
	   // log.info("content:"+content);

	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      
	      try {
	    	  br = new BufferedReader(new FileReader(oldFileName));
	          bw = new BufferedWriter(new FileWriter(newFileName));
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }finally{
	    	   if(br != null)
	               br.close();
	    	   if(bw != null){
	    		   log.info("registraction.spec file created successfully");
	    		   bw.close();}
	       }
		
	}
	
	

	
	public registrations spec(String sname) throws IOException{
		this.sname = sname;
		rspec();
		return this;
		
	}

}
