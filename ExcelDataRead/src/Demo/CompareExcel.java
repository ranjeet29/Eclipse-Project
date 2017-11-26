package Demo;

import java.io.File;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CompareExcel {

    public static void main(String[] args) {
        try {
        
            // Create Workbook instance holding reference to .xlsx file
        	System.out.println("Baseline File name with path :");
        	Scanner sca = new Scanner(System.in);
        	String f1 = sca.nextLine();
        	System.out.println("Current File Name with path :");
        	String f2 = sca.nextLine();
            XSSFWorkbook workbook1 = new XSSFWorkbook(new File(f1));
            XSSFWorkbook workbook2 = new XSSFWorkbook(new File(f2));

            // Get first/desired sheet from the workbook
            XSSFSheet sheet1 = workbook1.getSheetAt(0);
            XSSFSheet sheet2 = workbook2.getSheetAt(0);

            // Compare sheets
            if(compareTwoSheets(sheet1, sheet2)) {
                System.out.println("\n\nThe two excel sheets are Equal");
            } else {
                System.out.println("\n\nThe two excel sheets are Not Equal");
            }
            
          //close file 
            workbook1.close();
            workbook2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    // Compare Two Sheets
    public static boolean compareTwoSheets(XSSFSheet sheet1, XSSFSheet sheet2) {
        int firstRow1 = sheet1.getFirstRowNum();
        int lastRow1 = sheet1.getLastRowNum();
        boolean equalSheets = true;
        for(int i=7; i < lastRow1; i++) {
            
            System.out.println("\n\nComparing Row "+i);
            
            XSSFRow row1 = sheet1.getRow(i);
            XSSFRow row2 = sheet2.getRow(i);
         
            if(!compareTwoRows(row1, row2)) {
                equalSheets = false;
                System.out.println("Row "+i+" - Not Equal");
               
            } else {
                System.out.println("Row "+i+" - Equal");
            }
        }
        return equalSheets;
    }

    // Compare Two Rows
    public static boolean compareTwoRows(XSSFRow row1, XSSFRow row2) {
        if((row1 == null) && (row2 == null)) {
            return true;
        } else if((row1 == null) || (row2 == null)) {
            return false;
        }
        
        int firstCell1 = row1.getFirstCellNum();
        int lastCell1 = row1.getLastCellNum();
        boolean equalRows = true;
        
        // Compare all cells in a row
        for(int i=firstCell1; i <= lastCell1; i++) {
            XSSFCell cell1 = row1.getCell(i);
            XSSFCell cell2 = row2.getCell(i);
            System.out.println("cell in row1:"+cell1);
            System.out.println("cell in row2:"+cell2);
            if(!compareTwoCells(cell1, cell2)) {
                equalRows = false;
                int celltype = cell1.getCellType();
                int celltype2 =  cell2.getCellType();
                if( celltype == 0 && celltype2 == 0   ){
                float cell1value=(float) cell1.getNumericCellValue();
                float cell2value= (float) cell2.getNumericCellValue(); 
                System.err.println("       Diff. in both shell:"+(cell1value-cell2value));
                }else if ( celltype == 1 && celltype2 == 1){
                	StringBuffer b = new StringBuffer();
                	StringBuffer e = new StringBuffer();
                    String[] c = cell1.getStringCellValue().toString().split(",");
                    String[] d = cell2.getStringCellValue().toString().split(",");
                    for (String w:c){
                 	   b.append(w);
                 	   
                    }
                    for(String w1 :d){
                    	e.append(w1);
                    }
                //  System.out.println(b); 
                //  System.out.println(e);
                    float f = Integer.parseInt(b.toString());
                   float g = Integer.parseInt(e.toString());
                   System.err.println("       Diff. in both shell:"+(f-g));
                }
                System.err.println("       Cell "+i+" - NOt Equal");
            } else {
                System.out.println("       Cell "+i+ "- equal");
            }
        }
        return equalRows;
    }

    // Compare Two Cells
    public static boolean compareTwoCells(XSSFCell cell1, XSSFCell cell2) {
        if((cell1 == null) && (cell2 == null)) {
            return true;
        } else if((cell1 == null) || (cell2 == null)) {
            return false;
        }
        
        boolean equalCells = false;
        int type1 = cell1.getCellType();
        int type2 = cell2.getCellType();
        if (type1 == type2) {
            if (cell1.getCellStyle().equals(cell2.getCellStyle())) {
                // Compare cells based on its type
                switch (cell1.getCellType()) {
                case HSSFCell.CELL_TYPE_FORMULA:
                    if (cell1.getCellFormula().equals(cell2.getCellFormula())) {
                        equalCells = true;
                    }
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if (cell1.getNumericCellValue() == cell2
                            .getNumericCellValue()) {
                        equalCells = true;
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    if (cell1.getStringCellValue().equals(cell2
                            .getStringCellValue())) {
                        equalCells = true;
                    }
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    if (cell2.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                        equalCells = true;
                    }
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    if (cell1.getBooleanCellValue() == cell2
                            .getBooleanCellValue()) {
                        equalCells = true;
                    }
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    if (cell1.getErrorCellValue() == cell2.getErrorCellValue()) {
                        equalCells = true;
                    }
                    break;
                default:
                    if (cell1.getStringCellValue().equals(
                            cell2.getStringCellValue())) {
                        equalCells = true;
                    }
                    break;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return equalCells;
    }
}