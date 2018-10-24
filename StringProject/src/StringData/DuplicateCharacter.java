package StringData;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DuplicateCharacter {
     
   public static void main(String[] args) {
	
	   duplicateCharacter("hello");
	   duplicateCharacter("successfully");
}
	
	public static void duplicateCharacter(String word){
	   char[] charc = word.toCharArray();
	   Map<Character, Integer> charMap = new HashMap<Character, Integer>();
	   for(Character ch : charc){
		   
		   if (charMap.containsKey(ch)) {
               charMap.put(ch, charMap.get(ch) + 1);
           } else {
               charMap.put(ch, 1);
           }
	   }
	   
	   System.out.println("charMap : "+charMap);

	// Iterate through HashMap to print all duplicate characters of String
       Set<Map.Entry<Character, Integer>> entrySet = charMap.entrySet();
       System.out.println("entrySet : "+entrySet);
       System.out.printf("List of duplicate characters in String '%s' %n", word);
       for (Map.Entry<Character, Integer> entry : entrySet) {
           if (entry.getValue() >= 2) {
               System.out.printf("%s : %d %n", entry.getKey(), entry.getValue());
           }
       }
	}
}

