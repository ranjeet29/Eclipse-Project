package StringData;

public class WordContains {
 
	
	public static void main(String[] args) {
		wordContains("cattt", "atc");
		
	}
	public static void wordContains(String word1 , String word2){
		char [] char1 = word1.toCharArray();
		char [] char2 = word2.toCharArray();
	    int counter =0;
		int wordlen = char1.length;
		for (int i =0; i< wordlen ; i++){
			
			System.out.println("char :"+char1[i]);
			
			for(Character ch : char2){
				if (ch.equals(char1[i])){
					System.out.println("char matched");
			        counter ++;	
					break;
				}
			}
		}
		
		if ( counter == wordlen){
			System.out.println("Word Matched");
		}
	}
}

