package jsonreadfile;

import java.util.ArrayList;
import java.util.HashMap;

public class ArrayListDemo {
	
	public static void main(String arg []){
		
		HashMap<ArrayList<String>, ArrayList<String>> hm = new HashMap<>();
		ArrayList<String> al1 = new ArrayList<>();
		al1.add(0,"browser");
		al1.add(1, "driver");
		
		ArrayList<String> al2 = new ArrayList<>();
		
		al2.add("chrome");
		al2.add("Desktop Chrome");
		
		hm.put(al1, al2);
		
		System.out.println(hm);
		
		
		
		
	}

}
