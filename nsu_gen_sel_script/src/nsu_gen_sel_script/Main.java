package nsu_gen_sel_script;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
        
	public static Scanner sca;
	public static String path;
	public static String spath;
	public static String sname;
	
	
	private static void copyFileUsingChannel(InputStream source_old, String jarpath) throws IOException {
                 
		    InputStream inputStream = null;
		    OutputStream outputStream = null;
	    	try {
	
	    		inputStream = source_old;

	    		// write the inputStream to a FileOutputStream
	    		outputStream = new FileOutputStream(new File(jarpath));

	    		int read = 0;
	    		byte[] bytes = new byte[1024];

	    		while ((read = inputStream.read(bytes)) != -1) {
	    			outputStream.write(bytes, 0, read);
	    		}


	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}finally{
	    		inputStream.close();
	    		outputStream.close();
	    	}
	       
	   }

	
	public static void main(String arg[]) throws Exception{
		 new File("/tmp/nsu_gen_jscript").mkdirs();
	     new File("/tmp/nsu_gen_jscript").mkdirs();
     
	     final Logger log = Logger.getLogger(Main.class.getName());
	     log.setLevel(Level.INFO);
	
		
		System.out.println("##############################################################\n" +
			                "#                                                           #\n" +
			                "#       WELCOME TO AUTO SELENUM JTS SCRIPT GENERATION       #\n" +
			                "#                                                           #\n" +
			                "#############################################################\n");
		/*sca = new Scanner(System.in);
		System.out.print("Enetr Script Project Dir :");
		String sproj = sca.nextLine();
		System.out.print("Enter Script SubProject Dir :");
		String ssproj = sca.nextLine();
		System.out.print("Enter Script Name :");
		sname = sca.nextLine();*/
		
		   String sproj = null ;
		   String ssproj = null;;
		   String b_type = null ;
		   String host = null;
		   String s_port = null;
		   String p_port = null;
		   String actionfile = null;
		
		   try {
			   
			   
			   Options options = new Options();

			    Option option_p = new Option("p", "pr_dir", true, "Project Direcotry");
			    option_p.setRequired(false);
			    options.addOption(option_p);

			    Option option_sp = new Option("sp", "spr_dir",true, "SubProject Directory");
			    option_sp.setRequired(false);
			    options.addOption(option_sp);
			    
			    Option option_n = new Option("n", "sname",true, "Script Name");
			    option_n.setRequired(false);
			    options.addOption(option_n);


			    Option option_b = new Option("b", "type", true, "Browser Type (chrome or firefox or IE or andriod or ios)");
			    option_b.setRequired(true);
			    options.addOption(option_b);
			    
			    Option option_h = new Option("h", "host", true, "Machine Host Name ( IP Address )");
			    option_h.setRequired(false);
			    options.addOption(option_h);
			    
			    Option option_selport = new Option("selPort", "sel_port", true, "Selenium Server Remote Port (defaul 5555)");
			    option_selport.setRequired(false);
			    options.addOption(option_selport);
			    
			    Option option_proPort = new Option("proPort", "pro_port", true, "Proxy Server Running Port( default 7991)");
			    option_proPort.setRequired(false);
			    options.addOption(option_proPort);
			    
			    Option option_f = new Option("f", "act_file", true, "Action Input File (default www.Cavisson.com)");
			    option_f.setRequired(false);
			    options.addOption(option_f);

			    CommandLineParser parser = new DefaultParser();
			    HelpFormatter formatter = new HelpFormatter();
			    CommandLine cmd;

			    try {
			        cmd = parser.parse(options, arg);
			    } catch (ParseException e) {
			        System.out.println(e.getMessage());
			        formatter.printHelp("utility-name", options);

			        System.exit(1);
			        return;
			    }

			    sproj = cmd.getOptionValue("pr_dir");
			    ssproj  = cmd.getOptionValue("spr_dir");
			    sname =  cmd.getOptionValue("sname");
			    b_type = cmd.getOptionValue("type");
			    host = cmd.getOptionValue("host");
			    s_port  = cmd.getOptionValue("selPort");
			    p_port = cmd.getOptionValue("proPort");
			    actionfile = cmd.getOptionValue("act_file");
			    
			    //Check project and subproject is passed or not
			    if ( sproj == null ){
					sproj = "default";
				}
			    if (ssproj == null){
					ssproj = "default";
				}
               
			    if ( sname == null){
			    	sname = "RDTScript";
			    }
			    
			    if (host == null  ){
			    	host = "10.10.30.37";
			    }
			    if ( s_port == null){
			    	s_port = "5555";
			    }
			    if ( p_port == null ){
			    	p_port = "7991";
			    }
		   }catch(Exception e){e.getStackTrace();}
	
		   
		
		path = "/home/netstorm/work/scripts/"+sproj+"/"+ssproj+"/"+sname+"";
		
		//System.out.println(path);
		spath = "/home/netstorm/work/scenarios/"+sproj+"/"+ssproj+"/";
		
		File jarpath = new File("/home/netstorm/work/automation/");
		
		
		
		try{
		if (jarpath.exists() == false){
			jarpath.mkdirs();
		    log.info("Jar file copyed in automation dir");
		
			InputStream b_jar = Main.class.getResourceAsStream("/browsermob-dist-2.1.2-SNAPSHOT.jar");
			InputStream j_jar = Main.class.getResourceAsStream("/gson-2.2.2.jar");
			InputStream s_jar = Main.class.getResourceAsStream("/selenium-java-2.52.0.jar");
			
			copyFileUsingChannel(b_jar , "/home/netstorm/work/automation/browsermob-dist-2.1.2-SNAPSHOT.jar");
			copyFileUsingChannel(j_jar , "/home/netstorm/work/automation/gson-2.2.2.jar");
			copyFileUsingChannel(s_jar , "/home/netstorm/work/automation/selenium-java-2.52.0.jar");
			
			
		}else{
			log.info("Automaton directry already created");			
			InputStream b_jar = Main.class.getResourceAsStream("/browsermob-dist-2.1.2-SNAPSHOT.jar");
			InputStream j_jar = Main.class.getResourceAsStream("/gson-2.2.2.jar");
			InputStream s_jar = Main.class.getResourceAsStream("/selenium-java-2.52.0.jar");
			copyFileUsingChannel(b_jar , "/home/netstorm/work/automation/browsermob-dist-2.1.2-SNAPSHOT.jar");
			copyFileUsingChannel(j_jar , "/home/netstorm/work/automation/gson-2.2.2.jar");
			copyFileUsingChannel(s_jar , "/home/netstorm/work/automation/selenium-java-2.52.0.jar");
			
			
		}
		}catch(Exception e){
			System.out.println("Jar Already copy");
		}
		
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
        Flow_Gen flow = new Flow_Gen();
        flow.genScript(actionfile,b_type,host,s_port,p_port);
        		
		
	}
}
