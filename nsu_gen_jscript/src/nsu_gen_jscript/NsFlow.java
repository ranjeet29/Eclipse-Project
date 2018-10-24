package nsu_gen_jscript;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NsFlow {
	
	 
    Logger log = Logger.getLogger(NsFlow.class.getName());
    public String sname;
	
	public NsFlow(){
		log.setLevel(Level.INFO);
		}

	public void data() throws IOException{
		System.out.println(" ");
		String oldFileName = "/home/Script/NsFlow.java";
	    String newFileName = ""+Main.path+"/NsFlow.java";
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
	    		   log.info("NsFlow file created successfully");
	    		   bw.close();}
	    	       
	       }
	
	}
	
	public void nsflow(String sname) throws IOException{
		this.sname = sname;
	    data();  
	}

}
