package nsu_gen_sel_script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandFile {
    
	Logger log = Logger.getLogger(exit_script.class.getName());
	public String sname;
	  
	public CommandFile(){
		log.setLevel(Level.INFO);
	}
	
	public void command() throws IOException{
		System.out.println(" ");
	
	    String newFileName = ""+Main.path+"/CommandFile.java";
	    log.info("CommandFile:"+newFileName);
	    
	    String content = "package com.cavisson.scripts."+sname+"" ;
	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      try {
	    	  InputStream stream = exit_script.class.getResourceAsStream("/CommandFile.javaa");
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
	    		   log.info("Command File Created Successfully");
	    		   bw.close();}
	    	       
	       }
	}
	
	public CommandFile commandfile(String sname) throws IOException{
		this.sname = sname ;
		command();
		return this;
	}
}