package nsu_gen_jscript;


import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Device_Selection {

    capabilitySet cpa = new capabilitySet();            // capabilitySet file
    HarBuilder harbuilder = new HarBuilder();           // Harbuilder FIle 
    NsFlow nsflow = new NsFlow();                       // NsFlow file 
    registrations reg = new registrations();            // Registraction Spec file
    init_script init = new init_script();               //  Init_Script file 
    exit_script exit = new exit_script();               //exit_script  
    runlogic runlog = new runlogic();                   // runlogic file 
    SnapShotThread sshotthreed = new SnapShotThread();  //SnapShotThrea file 
    WaitTime waitTime = new WaitTime();                 //waitTime file
    Home home = new Home();                             // Home Page file
    Scenarios scenario = new Scenarios();                    // Scenarios file 
    
    
    
    public int deviceoption;
    public static String udid;
    public String dipw;
    Logger log = Logger.getLogger(Device_Selection.class.getName());
	
	public Device_Selection(){
		log.setLevel(Level.INFO);
		}
	
	public Device_Selection selectDevice() throws Exception{
		   // Select Device Option Name
		    System.out.println("");
			System.err.println("************** Select Device Option (1 or 2)********");
			System.out.println("");
			System.out.println("1)Android");
			System.out.println("2)iOS");
	        deviceoption = Main.sca.nextInt();
	    if ( deviceoption == 1 || deviceoption == 2){
	    	proxyoption();
	  
	    }else{
	    	System.err.println("Please Select Valid option (1 or 2)");
	    	selectDevice();
	    }
	    
		return this;
	}
	
	public Device_Selection proxyoption() throws Exception{
	    System.out.println(" ");
		System.err.println("************** Select Device Option (1 or 2)*******");
		System.out.println(" ");
		System.out.println("1)With Har");
		System.out.println("2)WithOut Har");
		int whar = Main.sca.nextInt();
	    Scanner sca1 = new Scanner(System.in);
		if(whar == 1 ){
			System.out.print("Device Name :");
			String dname = sca1.nextLine();
			System.out.print("Device Version :");
			String dversion = sca1.nextLine();
			System.out.print("Platform Name :");
			String pname = sca1.nextLine();
			if(deviceoption == 1){
				System.out.print("Device Ip with Port :");
				 dipw = sca1.nextLine();
			}else{
				
				 dipw = null;
			}
			System.out.print("Driver IP with Port :");
			String dripw = sca1.nextLine();
			System.out.print("Proxy Port :");
			int pport = sca1.nextInt();
			System.out.print("App Activity :");
			Scanner sca2 = new Scanner(System.in);
			String apactiy = sca2.nextLine();
			System.out.print("App Path :");
			String appath = sca2.nextLine();
			if(deviceoption == 2 ){
				System.out.print("Phone UDID :");
				udid = sca2.nextLine();
			}else{
				udid = null;
			}
			
			
			//#########Call NS Flow file #############
			nsflow.nsflow(Main.sname);
			reg.spec(Main.sname);
			runlog.rlogic(Main.sname);
			init.initScriptWhar(Main.sname ,dname , dversion , pname , dipw , dripw , pport , apactiy , appath);
			exit.exitscript(Main.sname);
			waitTime.wtime(Main.sname);
			sshotthreed.snapshot(Main.sname);
			cpa.capaSet(Main.sname);
			harbuilder.buildercall(Main.sname);
			home.home(Main.sname);
			scenario.scen(Main.sname);
			
		
			
			

		}else if (whar == 2){
			System.out.print("Device Name :");
			String dname = sca1.nextLine();
			System.out.print("Device Version :");
			String dversion = sca1.nextLine();
			System.out.print("Platform Name :");
			String pname = sca1.nextLine();
			if(deviceoption == 1){
				System.out.print("Device Ip with Port :");
				 dipw = sca1.nextLine();
			}else{
				
				 dipw = null;
			}
			System.out.print("Driver IP with Port :");
			String dripw = sca1.nextLine();	
			System.out.print("App Activity :");
			String apactiy = sca1.nextLine();
			System.out.print("App Path :");
			String appath = sca1.nextLine();
			if(deviceoption == 2 ){
				System.out.print("Phone UDID :");
			    udid = sca1.nextLine();
			}else{
				udid = null;
			}
			
			//########## Call NS Flow file ###############
			nsflow.nsflow(Main.sname);
			reg.spec(Main.sname);
			runlog.rlogic(Main.sname);
			init.initScriptWOhar(Main.sname , dname , dversion , pname , dipw , dripw , apactiy , appath );
			exit.exitscript(Main.sname);
			waitTime.wtime(Main.sname);
			sshotthreed.snapshot(Main.sname);
			cpa.capaSetwohar(Main.sname);
			home.homewohar(Main.sname);
			scenario.scen(Main.sname);
			
			
			
		}else{
			System.err.println("Please Select Valid option (1 or 2)");
			proxyoption();
		}
		
		return this;
	}
	
	
}
