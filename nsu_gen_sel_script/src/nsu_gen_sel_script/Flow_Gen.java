package nsu_gen_sel_script;


import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Flow_Gen {

    capabilitySet cpa = new capabilitySet();            // capabilitySet file
    HarBuilder harbuilder = new HarBuilder();           // Harbuilder FIle 
    NsFlow nsflow = new NsFlow();                       // NsFlow file 
    registrations reg = new registrations();            // Registraction Spec file
    init_script init = new init_script();               //  Init_Script file 
    exit_script exit = new exit_script();               //exit_script  
    runlogic runlog = new runlogic();                   // runlogic file 
    SnapShotThread sshotthreed = new SnapShotThread();  //SnapShotThrea file 
    WaitTime waitTime = new WaitTime();                 //waitTime file
    Flow flow = new Flow();                             // Home Page file
    Scenarios scenario = new Scenarios();               // Scenarios file 
    CommandFile command = new CommandFile();            // Command File
    
    
    public static Scanner sca1;
    Logger log = Logger.getLogger(Flow_Gen.class.getName());
	
	public Flow_Gen(){
		log.setLevel(Level.INFO);
		}
	
	
	
	public Flow_Gen genScript(String actionfile , String b_type,String host,String s_port,String p_port) throws Exception{
		    
		int sel_port = Integer.parseInt(s_port);
		int pro_port = Integer.parseInt(p_port);
		
	    scenario.scen(Main.sname);    // For scenario file
	    exit.exitscript(Main.sname);  // For exit script
	    reg.spec(Main.sname);         // For registration file
	    nsflow.nsflow(Main.sname);    // For NSFlow file
	//    waitTime.wtime(Main.sname);   // For waitTime file
	    sshotthreed.snapshot(Main.sname); // Snapshot Thread file
	    runlog.rlogic(Main.sname);    // For runlogic file
	    harbuilder.buildercall(Main.sname); // For HarBuilder file
	    init.initScriptWhar(Main.sname ,b_type, host, sel_port, pro_port); // For init_script file 
	    cpa.capaSet(Main.sname);      // For capability file 
	    flow.flowfile(Main.sname , actionfile);    // For Flow file
	    command.commandfile(Main.sname); // Command File
	    
	    
		
		return this;
	}
	
	
}
