package nsu_gen_sel_script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	
		    String newFileName = ""+Main.path+"/HarBuilder.java";
		    log.info("HarBuilderFile:"+newFileName);
		    
		    String content = "package com.cavisson.scripts."+sname+"" ;
		      BufferedReader br = null;
		      BufferedWriter bw = null;
		      try {
		    	  InputStream stream = HarBuilder.class.getResourceAsStream("/HarBuilder.javaa");
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
