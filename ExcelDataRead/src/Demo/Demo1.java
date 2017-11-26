package Demo;

public class Demo1 {
	
	
	public static void main(String arg[]){
	
		String a="2,132";
		int d = 1034;
		StringBuffer b = new StringBuffer();
		
       String[] c = a.split(",");
       for (String w:c){
    	   b.append(w);
    	   
       }
       
       int f1 = Integer.parseInt(a);
     
       int f = Integer.parseInt(b.toString());
       int g = f-d;
       System.out.println("g:" +g);
	   
       System.out.println(b);
	}
	
}
