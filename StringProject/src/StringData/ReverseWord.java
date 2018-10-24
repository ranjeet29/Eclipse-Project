package StringData;

public class ReverseWord {
	public static void main(String[] args) {
		String data = "helloworld this is java";
		String reverse;
		reverse = reverse(data);
		System.out.println("reverse : " + reverse);
		rev("dacaaca");
		method("hello world");
	}

	public static String reverse(String word) {
		String data = "";

		for (int i = 1; i <= word.length(); i++) {
			data = data + word.charAt(word.length() - i);
		}

		return data;
	}

	public static void rev(String original) {

		String reverse = "";
		int length = original.length();

		for (int i = length - 1; i >= 0; i--)
			reverse = reverse + original.charAt(i);

		System.out.println("Reverse of entered string is: " + reverse);
	}

	public static void method(String word) {
		String data = "";
		StringBuffer br = new StringBuffer(word);
		data = br.reverse().toString();
		System.out.println("reverse data is : " + data);
	}

}
