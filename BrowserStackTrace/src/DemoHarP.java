import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;


public class DemoHarP {
 public static void main(String[] args) {
	String HarFileName = "/home/netstorm/Desktop/a";
	 String harStr = "";
     BufferedReader br = null;
     

     try {
         br = new BufferedReader(new FileReader(HarFileName));
         StringBuilder sb = new StringBuilder();

         while (true) {
             String line = br.readLine();
             if(line == null)
                 break;
             sb.append(line);
         }
         harStr = sb.toString();
         br.close();

     } catch(Exception e){
         try{
             br.close();
         }catch(Exception ex){
         }
         harStr = "";
     }
 
     harStr = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(harStr));
     harStr = "\n" +harStr+ "\n;";

     //name of harp file
     String harpFileName = HarFileName +"p";

     //write harp file
     BufferedWriter writer = null;
     try{
         writer = new BufferedWriter(new FileWriter(harpFileName));
         writer.write(harStr);
         writer.close();
     }catch(Exception e){
          e.printStackTrace(); 
      } 
      
     
 }
}

