package nsu_gen_jscript;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
        
	public static Scanner sca;
	public static String path;
	public static String spath;
	public static String sname;
	public static void main(String arg[]) throws Exception{
		 new File("/tmp/nsu_gen_jscript").mkdirs();
	     new File("/tmp/nsu_gen_jscript").mkdirs();
     
	     final Logger log = Logger.getLogger(Main.class.getName());
	     log.setLevel(Level.INFO);
	
		
		System.out.println("##############################################################\n" +
			                "#                                                           #\n" +
			                "#       WELCOME TO AUTO JTS SCRIPT GENERATION               #\n" +
			                "#                                                           #\n" +
			                "#############################################################\n");
		sca = new Scanner(System.in);
		System.out.print("Enetr Script Project Dir :");
		String sproj = sca.nextLine();
		System.out.print("Enter Script SubProject Dir :");
		String ssproj = sca.nextLine();
		System.out.print("Enter Script Name :");
		sname = sca.nextLine();
		
		
		
		path = "/home/netstorm/work/scripts/"+sproj+"/"+ssproj+"/"+sname+"";
		
		//System.out.println(path);
		spath = "/home/netstorm/work/scenarios/"+sproj+"/"+ssproj+"/";
		
		if(new File(path).mkdirs() == false )
			log.info("Projetc and SubProject Already Created");
		else{
		    new File(path).mkdirs();
		    log.info("Project and Subproject Created");
		}
		

		if(new File(spath).mkdirs() == false )
			log.info("Scenarion Projetc and SubProject Already Created");
		else{
		    new File(spath).mkdirs();
		    log.info("Scenario Project and Subproject Created");
		}

       
        //Call to Device option selection
        Device_Selection dsele = new Device_Selection();
        dsele.selectDevice();
        
       
		
		
		
	}
}
