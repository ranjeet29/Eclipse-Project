package Practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetExample{
	 public static void main(String[] args) {
	        Set<Integer> mySet = new HashSet<>();
	        mySet.add(5);
	        mySet.add(7);
	        mySet.add(-4);
	        mySet.add(2);
	        System.out.format("Set: %s%n", mySet);
	        
	        Iterator<Integer> it = mySet.iterator();
	        
	        ArrayList<Integer> data = new ArrayList<Integer>(mySet);
	       
           System.out.println(data);
           System.out.println(data.indexOf(5));
	 }
}
