package nsu_gen_sel_script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WaitTime {
	
	 Logger log = Logger.getLogger(registrations.class.getName());
	 public String sname;
	
	 public WaitTime(){
			log.setLevel(Level.INFO);
			}
	public void waittime() throws IOException{
		
		System.out.println(" ");
		
	    String newFileName = ""+Main.path+"/WaitTime.java";
	    log.info("WaitTimeFile :"+newFileName);
	    
	    String content = "package com.cavisson.scripts."+sname+"" ;
	   // log.info("content:"+content);

	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      
	      try {
	    	  InputStream stream = WaitTime.class.getResourceAsStream("/WaitTime.javaa");
	          System.out.println(stream != null);
			  br = new BufferedReader(new InputStreamReader(stream));
	          bw = new BufferedWriter(new FileWriter(newFileName));
	          
	          String line;
	          while ((line = br.readLine()) != null) {
	             if (line.contains("package com.cavisson.scripts."))
	                line = line.replace("package com.cavisson.scripts.", ""+content);
	                bw.write(line+"\n");      
	          }
	          
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }finally{
	    	   if(br != null)
	               br.close();
	    	   if(bw != null){
	    		   log.info("WaitTime file created successfully");
	    		   bw.close();}
	       }
		
	}
	
	

	
	public WaitTime wtime(String sname) throws IOException{
		this.sname = sname;
		waittime();
		return this;
		
	}


}
