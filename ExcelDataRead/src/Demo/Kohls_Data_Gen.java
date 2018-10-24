package Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Kohls_Data_Gen {

	static int tdc;
	static int flag_stat;
	static XSSFWorkbook yourworkbook;
	

	static ArrayList<String> al = new ArrayList<String>();
    
	public static void main(String[] args) throws IOException {
		Options options = new Options();

        Option input = new Option("i", "input", true, "input data file path");
        input.setRequired(true);
        options.addOption(input);
        
        Option temp = new Option("t", "templet", true, "Template file ");
        temp.setRequired(true);
        options.addOption(temp);

        Option output = new Option("o", "output", true, "output file path name");
        output.setRequired(true);
        options.addOption(output);
        
        Option number = new Option("n", "number", true, "Number of entries");
        number.setRequired(false);
        options.addOption(number);

        
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            return;
        }

        String inputFilePath = cmd.getOptionValue("input");
        String outputFilePath = cmd.getOptionValue("output");
        String template = cmd.getOptionValue("templet");
        String  num_record= cmd.getOptionValue("number");
        
        if (num_record.equals(null)){
        	num_record = "20";
        }
        if(outputFilePath.equals(null)){
        	outputFilePath = "Gendata";
        }
		
		parseData(inputFilePath , outputFilePath , template, num_record);
	
	
	}
	
	
	
	
	public static void parseData(String inputfile, String outputFilePath , String templte , String num_record) throws IOException {
		int num_records = Integer.parseInt(num_record);
		try {
		//	File fin_1 = new File("/home/netstorm/kohls_data_read/Pick_Data.xlsx");
			File fin_1 = new File(inputfile);

			XSSFWorkbook workbook = new XSSFWorkbook(fin_1);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator rowIterator = sheet.rowIterator();
			XSSFRow row_old;
			XSSFCell cell;
			
			while (rowIterator.hasNext()) {
				row_old = (XSSFRow) rowIterator.next();

				cell = row_old.getCell(0);
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					String str = NumberToTextConverter.toText(cell
							.getNumericCellValue());
					System.out.println("data: " + str);
					al.add(str);
				} else {
					al.add(cell.toString());
				}

			}
			// System.out.println("ARRRRRR\n"+al);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String arr[] = new String[al.size()];
		al.toArray(arr);
		System.out.println(arr.length);

		tdc = arr.length;
		int file_qty = tdc / num_records;

		//FileInputStream file = new FileInputStream(new File("/home/netstorm/kohls_data_read/Auto_Data.xlsx"));
		FileInputStream file = new FileInputStream(new File(templte));
		yourworkbook = new XSSFWorkbook(file);
		XSSFSheet sheet1 = yourworkbook.getSheetAt(0);
		Row row = sheet1.getRow(12);
		Cell column = row.getCell(3);
		Cell column2 = row.getCell(2);

		int j = 2;
		for (flag_stat = 0; flag_stat < file_qty; flag_stat++) {
			int count = 0;
			System.out.println("FILE: " + (flag_stat + 1));
			String data = randomAlphaNumeric(10);
			System.out.println("Random number :" + data);
			for (int i = j; count < num_records; i++) {
				count++;
				row.setRowNum(10 + count);
				try {
					System.out.println(i + " Cell RR:" + row.getRowNum()
							+ " CC: " + column.getColumnIndex() + " VAL: "
							+ arr[i]);
				} catch (Exception e) {
					filewriter(outputFilePath);
					e.printStackTrace();
				}
				Row rr = sheet1.getRow(row.getRowNum());
				Cell cc = rr.getCell(column.getColumnIndex());
				Cell cc2 = rr.getCell(column2.getColumnIndex());
				try {
					cc.setCellValue(Integer.parseInt(arr[i]));
				//	cc2.setCellValue(data);
				} catch (Exception e) {
					System.out.println("Pick Data Empty");
					cc.setCellValue(" ");
				}
			}
			file.close();
			filewriter(outputFilePath);

			j += num_records;
		}

	}

	static String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING
					.length());

			builder.append(ALPHA_NUMERIC_STRING.charAt(character));

		}
		//System.out.println("data : " + builder.toString());

		return builder.toString();
	}

	public static void filewriter(String outputfile) throws IOException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(outputfile + (flag_stat + 1)
							+ ".xlsm"));
			yourworkbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		out.close();
	}
}



