package Demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	
	 static ArrayList<String> al = new ArrayList<String>();
     public static void main(String[] args) {
    	 try {
    			//	File fin_1 = new File("/home/netstorm/kohls_data_read/Pick_Data.xlsx");
    				File fin_1 = new File("/home/netstorm/kohls_data_read/template.xlsm");
    				XSSFWorkbook workbook = new XSSFWorkbook(fin_1);
    				XSSFSheet sheet = workbook.getSheetAt(0);
    				
    				   Iterator<Row> rowIterator = sheet.rowIterator();
    			        while (rowIterator.hasNext()) {
    			            Row row = rowIterator.next();

    			            // Now let's iterate over the columns of the current row
    			            Iterator<Cell> cellIterator = row.cellIterator();

    			            while (cellIterator.hasNext()) {
    			                Cell cell1 = cellIterator.next();
    			                switch (cell1.getCellType()) {
    			                case HSSFCell.CELL_TYPE_FORMULA:
    			                    System.out.println("Formula : "+cell1.getCellFormula());
    			                    break;
    			                case HSSFCell.CELL_TYPE_NUMERIC:
    			                    
    			                    System.out.println("Number : "+cell1.getNumericCellValue());
    			                    break;
    			                case HSSFCell.CELL_TYPE_STRING:
    			                    System.out.println("String :"+cell1.getStringCellValue());
    			                    break;
    			                case HSSFCell.CELL_TYPE_BLANK:
    			                    System.out.println(" ");
    			                    break;
    			                case HSSFCell.CELL_TYPE_ERROR:
    			                    System.out.println("Eror : "+cell1.getErrorCellValue());
    			                    break;
    
    			                }
    			             
    			            }
    			            System.out.println();
    			        }

    	 }catch(Exception e){ e.printStackTrace();}
    	 
     }
     
}


