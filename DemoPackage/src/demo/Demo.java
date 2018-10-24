package demo;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Demo {

	

	public static void main(String[] args) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
		
            InputStream stream = Demo.class.getResourceAsStream("/demo.txt");
            System.out.println(stream != null);
          
            System.out.println(stream.toString());
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String st;
			while((st = br.readLine()) != null){
				System.out.println(st);
			}
			
			boolean a = false;
			boolean b = true;
			if (a && b){
				System.out.println("true and true ");
			}else if ( a == true  && b == false){
				System.out.println("true and false");
			}else if (a == false && b == true){
				System.out.println(" false and true ");
 				
			}else{
				System.out.println(" false and false ");
			}
					

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}
