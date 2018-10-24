package demo;
import java.util.*;
import java.io.*;

public class fileparser {
public static void main(String args[])throws Exception
	{
	Find find=new Find();
	Scanner sc=new Scanner(new File("ranjeet.txt"));
	while(sc.hasNext())
	{
	find.run(sc.nextLine());
	}
	}
}
class Find
{
ArrayList array=new ArrayList();
public void run(String s)
	{
	String action=s.substring(0,s.indexOf(',')).substring(s.indexOf('=')+1);
	switch(action)
	{
	case "Scroll":scrollFunction(s);break;
	case "input":scrollFunction(s);break;
	case "Browse":scrollFunction(s);break;
	case "search":scrollFunction(s);break;
	case "Click":scrollFunction(s);break;
	default:System.out.println(action);break;
	}
}	
void scrollFunction(String s)
     	{
	      String[] val=s.split(",");
	      int i=0;
	      int j=2;
	      if(val.length<3)
	      j=1;
	     for(;i<val.length-j;i++)
	        {  
	    	   System.out.println(val[i]);
	    	   String action = val[i].substring(0,val[i].indexOf('='));
	    	   String action_name = val[i].substring(val[i].indexOf('=')+1);
	    	   
	    	   
	          System.out.println("action is: "+action);
	          System.out.println("action name :"+action_name);
	         }
	     String str = s.substring(s.indexOf(val[i]));
	     System.out.println(str.substring(0,str.indexOf('='))+" is "+str.substring(str.indexOf('=')+1));
	System.out.println("");
	}
}

