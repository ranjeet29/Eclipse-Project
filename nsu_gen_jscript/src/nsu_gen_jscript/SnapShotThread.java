package nsu_gen_jscript;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SnapShotThread {
	
	Logger log = Logger.getLogger(SnapShotThread.class.getName());
	public String sname;
	  
	public SnapShotThread(){
		log.setLevel(Level.INFO);
	}
	
	public void Snapshot() throws IOException{
		System.out.println(" ");
		String oldFileName = "/home/Script/SnapShotThread.java";
	    String newFileName = ""+Main.path+"/SnapShotThread.java";
	    log.info("HarBuilderFile:"+newFileName);
	    
	    String content = "package com.cavisson.scripts."+sname+"" ;
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
	    		   log.info("SnapShot File Created Successfully");
	    		   bw.close();}
	    	       
	       }
	}
	
	public SnapShotThread snapshot(String sname) throws IOException{
		this.sname = sname ;
		Snapshot();
		return this;
	}

}
