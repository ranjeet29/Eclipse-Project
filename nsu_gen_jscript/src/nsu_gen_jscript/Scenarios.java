package nsu_gen_jscript;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scenarios {
	
	 public Scenarios() {
		// TODO Auto-generated constructor stub
		 log.setLevel(Level.INFO);
	}
		
	 Logger log = Logger.getLogger(Scenarios.class.getName());
	 public String sname;
	 
	 public void runsce() throws IOException{
		 System.out.println(" ");
			String oldFileName = "/home/Script/scenarios/demo.conf";
		    String newFileName = ""+Main.spath+""+sname+".conf";
		    log.info("newfile:"+newFileName);
		  //  log.info("oldfile:"+oldFileName);
		    
		    
		      String content = "SGRP G1 NA NA Internet 0 "+sname+" 1" ;
		      
		      BufferedReader br = null;
		      BufferedWriter bw = null;
		      try {
		          br = new BufferedReader(new FileReader(oldFileName));
		          bw = new BufferedWriter(new FileWriter(newFileName));
		       
		          String line;
		          while ((line = br.readLine()) != null) {
		             if (line.contains("SGRP G1 NA NA Internet"))
		                line = line.replace("SGRP G1 NA NA Internet", ""+content);
		                bw.write(line+"\n");
		               
		                
		          }
		       } catch (Exception e) {
		          e.printStackTrace();
		       }finally{
		    	   if(br != null)
		               br.close();
		    	   if(bw != null){
		    		   log.info("Scenario File Created Successfully");
		    		   bw.close();
		    	   }
		    	       
		       }
	 }
	 
	 public Scenarios scen(String sname) throws Exception{
		 this.sname = sname;
		 runsce();
		 
		 return this;
	 }
	 

}
