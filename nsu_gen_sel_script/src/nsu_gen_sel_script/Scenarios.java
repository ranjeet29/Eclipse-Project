package nsu_gen_sel_script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	
		    String newFileName = ""+Main.spath+""+sname+".conf";
		    log.info("newfile:"+newFileName);
		
		    
		    
		      String content = "SGRP G1 NA NA Internet 0 "+sname+" 1" ;
		      
		      BufferedReader br = null;
		      BufferedWriter bw = null;
		      try {
		    	  InputStream stream = Scenarios.class.getResourceAsStream("/scenario/Chrome_Browser.conf");
		          System.out.println(stream != null);
				  br = new BufferedReader(new InputStreamReader(stream));
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
