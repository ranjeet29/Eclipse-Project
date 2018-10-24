package StringData;

import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicatechar {
	
	public static void main(String[] args) {
		String data = "hellooooooooooo";
		char [] data1 = data.toCharArray();
	
		String datanew = " "; 
		for(Character ch : data1){
			if(! datanew.contains(ch.toString()))
		    datanew = datanew + ch; 
		}
		
		System.out.println(datanew);
		newWay("sfergthjkldsfgbfdfsfdfdg");
		removeDuplicates("aaadsasfsfaaaaa");
		otherWay();
	}
	
	
	public static void removeDuplicates(String input){
	    String result = "";
	    for (int i = 0; i < input.length(); i++) {
	        if(!result.contains(String.valueOf(input.charAt(i)))) {
	            result += String.valueOf(input.charAt(i));
	        }
	    }
	    System.out.println("result : "+result);
	}
	
	public static void newWay(String data){
		String string = data;

		char[] chars = string.toCharArray();
		Set<Character> charSet = new LinkedHashSet<Character>();
		
		for (char c : chars) {
		    charSet.add(c);
		}
		System.out.println("charSet :"+charSet);
		StringBuilder sb = new StringBuilder();
		for (Character character : charSet) {
		    sb.append(character);
		}
		System.out.println(sb.toString());
	}

	public static void otherWay(){
		String s="aabbccdef";
		Set<Character> set=new LinkedHashSet<Character>();
		for(char c:s.toCharArray())
		{
		    set.add(Character.valueOf(c));
		}
		
		System.out.println("set : "+set);
		String data = "";
		for(Character ch : set){
			data += ch;
		}
		System.out.println(data);
	}
}
