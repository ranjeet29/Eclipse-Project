package nsu_gen_sel_script;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	
	    String newFileName = ""+Main.path+"/NsFlow.java";
	    log.info("NsFlowFile :"+newFileName);
	    
	      
	    String content = "package com.cavisson.scripts."+sname+"" ;
	   // log.info("content:"+content);

	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      try {
	    	  InputStream stream = Flow.class.getResourceAsStream("/NsFlow.javaa");
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
	    		   log.info("NsFlow file created successfully");
	    		   bw.close();}
	    	       
	       }
	
	}
	
	public void nsflow(String sname) throws IOException{
		this.sname = sname;
	    data();  
	}

}
