package Demo;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtils {
    public static void main(String[] args) throws InvalidFormatException, IOException {
		
    	File f1 = new File("/home/netstorm/Desktop/D_4111_215_216.xlsx");
    	 XSSFWorkbook workbook1 = new XSSFWorkbook(f1);
    	 XSSFSheet sheet = workbook1.getSheetAt(0);
    	 
    	 XSSFRow row1 ;
    	 
    	 row1 = sheet.getRow(2);
    	 
    	 XSSFCell cell2 = null ;
    	 
    	 cell2 = row1.getCell(0);
    	 
    	 System.out.println("cell2 : "+cell2);
    	 
    	 for(Row row11 : sheet ){
    		 Cell cell = row11.getCell(0);
    		 System.out.println("cell "+cell);
    	 }
    	 
    	 workbook1.close();
    	 
	}   
}
