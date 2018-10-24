package demo;

import java.util.*;
import java.io.*;

public class fileHandle {
public static void main(String args[])throws Exception
	{
	fileHandle find=new fileHandle();
	Scanner sc=new Scanner(new File("ranjeet.txt"));
	while(sc.hasNext())
	{
	find.run(sc.nextLine());
	}
	}

public void run(String s)
	{
	String action=s.substring(0,s.indexOf(',')).substring(s.indexOf('=')+1);
	switch(action)
	{
	case "Scroll":scrollFunction(s);break;
	case "input":inputFunction(s);break;
	case "Browse":browseFunction(s);break;
	case "search":searchFunction(s);break;
	case "Click":clickFunction(s);break;
	default:System.out.println(action);break;
	}
}	
public void clickFunction(String s)
	{
	String[] val=s.split(",");
	int i=0;
	int j=2;
	if(val.length<3)
	j=1;
	for(;i<val.length-j;i++)
	{
		
	if(val[i].substring(0,val[i].indexOf('=')).equals("value")){
	System.out.println(" Xpath value is "+val[i].substring(val[i].indexOf('=')+1));
	return;	}
	}
	String str = s.substring(s.indexOf(val[i]));
	if(str.substring(0,str.indexOf('=')).equals("options")){
	System.out.println(" options value is "+str.substring(str.indexOf('=')+1)); 	}
	return;
	}
public void scrollFunction(String s)
	{
	String[] val=s.split(",");
	int i=0;
	int j=2;
	if(val.length<3)
	j=1;
	for(;i<val.length-j;i++)
	{
	//System.out.print(val[i].substring(0,val[i].indexOf('='))+" is "+val[i].substring(val[i].indexOf('=')+1)+" : ");
	if(val[i].substring(0,val[i].indexOf('=')).equals("value")){
	System.out.println(" Xpath value is "+val[i].substring(val[i].indexOf('=')+1));
	return;	}
	}
	String str = s.substring(s.indexOf(val[i]));
	//System.out.print(str.substring(0,str.indexOf('='))+" is "+str.substring(str.indexOf('=')+1));
	if(str.substring(0,str.indexOf('=')).equals("option")){
	System.out.println(" options value is "+str.substring(str.indexOf('=')+1)); 	}
	return;
	}

public void inputFunction(String s)
	{
	String[] val=s.split(",");
	int i=0;
	int j=2;
	if(val.length<3)
	j=1;
	for(;i<val.length;i++)
	{
	//System.out.print(val[i].substring(0,val[i].indexOf('='))+" is "+val[i].substring(val[i].indexOf('=')+1)+" : ");
	if(val[i].substring(0,val[i].indexOf('=')).equals("data")){
	System.out.println(" value is "+val[i].substring(val[i].indexOf('=')+1));
	return;	}
	}
	String str = s.substring(s.indexOf(val[i]));
	//System.out.print(str.substring(0,str.indexOf('='))+" is "+str.substring(str.indexOf('=')+1));
	if(str.substring(0,str.indexOf('=')).equals("data")){
	System.out.println(" options value is "+str.substring(str.indexOf('=')+1)); 	}
	return;
	}
public void browseFunction(String s)
	{
	String[] val=s.split(",");
	int i=0;
	int j=2;
	if(val.length<3)
	j=1;
	for(;i<val.length-j;i++)
	{
	//System.out.print(val[i].substring(0,val[i].indexOf('='))+" is "+val[i].substring(val[i].indexOf('=')+1)+" : ");
	if(val[i].substring(0,val[i].indexOf('=')).equals("value")){	
	System.out.println(" Xpath value is "+val[i].substring(val[i].indexOf('=')+1));
	return;	}
	}
	String str = s.substring(s.indexOf(val[i]));
	//System.out.print(str.substring(0,str.indexOf('='))+" is "+str.substring(str.indexOf('=')+1));
	if(str.substring(0,str.indexOf('=')).equals("value")){
	System.out.println(" url value is "+str.substring(str.indexOf('=')+1)); 	}
	return;
	}
public void searchFunction(String s)
	{
	String[] val=s.split(",");
	int i=0;
	int j=2;
	if(val.length<3)
	j=1;
	for(;i<val.length-j;i++)
	{
	//System.out.print(val[i].substring(0,val[i].indexOf('='))+" is "+val[i].substring(val[i].indexOf('=')+1)+" : ");
	if(val[i].substring(0,val[i].indexOf('=')).equals("input")){
	System.out.println(" input value is "+val[i].substring(val[i].indexOf('=')+1));
	return;	}
	}
	String str = s.substring(s.indexOf(val[i]));
	//System.out.print(str.substring(0,str.indexOf('='))+" is "+str.substring(str.indexOf('=')+1));
	if(str.substring(0,str.indexOf('=')).equals("input")){
	System.out.println(" input value is "+str.substring(str.indexOf('=')+1)); 	}
	return;
	}

}

