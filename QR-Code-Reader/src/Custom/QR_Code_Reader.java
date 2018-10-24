package Custom;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QR_Code_Reader {
	
	public static void main(String[] args) throws IOException, NotFoundException {
		
		BufferedImage bufferimage = ImageIO.read(new File("/home/netstorm/JAR_File/BarCodeReader/palvika.png"));
		LuminanceSource luminance = new BufferedImageLuminanceSource(bufferimage);
		
		BinaryBitmap binarybitmap = new BinaryBitmap(new HybridBinarizer(luminance));
		
		Result result = new MultiFormatReader().decode(binarybitmap);
		
		String qrcoderesult = result.getText();
		Boolean qrcoderesult1 = result.getText().equals("shruti");
		System.out.println("Text inside QR Code is : "+qrcoderesult);
		
		
	}

}
