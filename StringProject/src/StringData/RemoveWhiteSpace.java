package StringData;

public class RemoveWhiteSpace {
   public static void main(String[] args) {
	
	   String data = "             data with white space      ";
	   String dat2 = data.trim();
	   
	   System.out.println("data : "+data );
	   System.out.println("data2 :"+dat2);
	   
	   String data3 = data.replaceAll(" ", "");
	   System.out.println("Data3 :"+data3);
}
}
