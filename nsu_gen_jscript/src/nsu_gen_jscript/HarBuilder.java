package nsu_gen_jscript;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HarBuilder {

	  public HarBuilder(){
		  log.setLevel(Level.INFO);
	  }
	  
	  Logger log = Logger.getLogger(HarBuilder.class.getName());
	  public String sname;
	  
	  public void harbuildercall() throws IOException{
		    System.out.println(" ");
			String oldFileName = "/home/Script/HarBuilder.java";
		    String newFileName = ""+Main.path+"/HarBuilder.java";
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
		    		   log.info("HarBuilder File Created Successfully");
		    		   bw.close();}
		    	       
		       }
  
	  }
	  
        
	  
	  public HarBuilder buildercall(String sname) throws Exception{
		  this.sname = sname;
		  harbuildercall();
		  
		  return this;
	  }
	  
	  
}
