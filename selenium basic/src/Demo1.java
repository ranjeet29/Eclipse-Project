import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Demo1 {
     
	//staging
		  static String ivKey = "DB7hnoY7bO8f38DmT6+1OA==";
		  static String secretKey = "HoQWIa578GsHryyoDMEx5A==";
		
		//prod
		/*static String ivKey="nH27aJUl2xeNFXjn7sel5Q==";
		static String secretKey="SFNZlv48IYCrlJCWVdVylw==";*/

		public static void main(String[] args) {
			String text = "NP3npNdu0eGwN6PXcEqrBRd7UdIFOyubeLnFXtuf0sig%2F45sa1Sj51sKYtauIQ63LRamFa4%2FWkgTPwNHVplVidE73qhtzRjZnOYncE7AP8240%2FcS1eyfDNlagz3013dGjCCxn0Ozzt6rRnv874UTvJHcpRvhf%2BakK4WRDX8yqTGKcenyCvWqdQoerAxKeqXWg2%2FbcLNa6d4VVGfE8kJYnzZR4lFUXr92g8wASInjvWhaTplNedJf%2FreJLyhUteXwJ3U9NvkIs8VdwUl1lBo2RlNOMd3ELbZ5jA%3D%3D";
			//String text = "iwxI2VUxrUep%2BHcuVpxrIuUX4RlM1tUKHIEnrBN5dQH16VxsVDYf7q%2BX7itVZ58s5JjNJakbqtfUYCLxbietWmC3%2B%2FxUgisBRXW1oawp3GhK6DclYBhhY%2FM5he%2F8Mz6PVYa3HFXKnFW";
			String encryptedText = null;
			String decryptedText = null;
			
			try {

				//System.out.println("Input value-->" + text);
				//encryptedText = encryptData(text, secretKey);
				System.out.println("Encrypted data is :: " + text);
				decryptedText = decryptData(text, secretKey);
				System.out.println("Decrypted data is :: " + decryptedText);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public static String encryptData(String content, String key) throws Exception {
			String encryptedData = null;
			String urlEncodedValue = null;
			try {
				byte[] byteCipherText;
				SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key.getBytes()), "AES");

				Cipher aesCipher = Cipher.getInstance("AES/CTR/NoPadding");
				IvParameterSpec ips = new IvParameterSpec(Base64.getDecoder().decode(ivKey.getBytes()));
				aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, ips);
				byteCipherText = aesCipher.doFinal(content.getBytes());
				encryptedData = Base64.getEncoder().encodeToString(byteCipherText);
				urlEncodedValue = URLEncoder.encode(encryptedData, "UTF-8");

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return urlEncodedValue;
		}

		public static String decryptData(String encryptedValue, String key) throws Exception {
			String decryptedData = null;
			String urlDecodedValue = null;
			try {
				byte[] byteCipherText;

				SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key.getBytes()), "AES");
				Cipher aesCipher = Cipher.getInstance("AES/CTR/NoPadding");
				IvParameterSpec ips = new IvParameterSpec(Base64.getDecoder().decode(ivKey.getBytes()));
				aesCipher.init(Cipher.DECRYPT_MODE, secretKey, ips);
				urlDecodedValue = URLDecoder.decode(encryptedValue, "UTF-8");
				byteCipherText = aesCipher.doFinal(Base64.getDecoder().decode((urlDecodedValue.getBytes("UTF-8"))));
				decryptedData = new String(byteCipherText, "UTF-8");

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return decryptedData;
		}
}
