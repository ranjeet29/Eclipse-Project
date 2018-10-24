import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Demo {
	public static void main(String arg []) throws IOException{
		String sCurrentLine;
		StringBuffer bf = null;
		String add;
		 bf = new StringBuffer();
		 File is1=new File("/home/netstorm/Desktop/harp1.harp");
		
		  
	   //   long numb  = numbGen();
		  long number = (long) Math.floor(Math.random() * 90000000000000000L) + 1000000000000L;
	   
		  System.out.println("numb:="+number);

		  
		 BufferedReader reader = new BufferedReader(new FileReader(is1)); 
		 while((add = reader.readLine())!= null){
		  bf.append(add);
		  if(add.contains("_error")){
			  System.out.println("error contails");
			  bf.append(add.replaceAll(",\"_error\":\"Unable to *\"", " "));
		  }
		  bf.append("\n");
		  
			
		 }
		 reader.close();
		 
	     String str = bf.toString();
	     System.out.println(str);
	     
	     
		
		
		
	}
}
