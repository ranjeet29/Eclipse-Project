package nsu_gen_sel_script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		
	    String newFileName = ""+Main.path+"/registrations.spec";
	    log.info("registraction spec :"+newFileName);
	    
	
	   // log.info("content:"+content);

	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      
	      try {
	    	  InputStream stream = registrations.class.getResourceAsStream("/registrations.spec");
	          System.out.println(stream != null);
			  br = new BufferedReader(new InputStreamReader(stream));
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
