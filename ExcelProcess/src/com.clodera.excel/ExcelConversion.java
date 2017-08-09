package com.cloudera.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelConversion {

	//private StringBuilder strString =null;
	//private FileInputStream is;
	//private int count =0;
	
	
	public static void main(String[] args) throws IOException
	{
		ExcelConversion objExcelConversion = new ExcelConversion();
		objExcelConversion.convertExcelCellData();
		
	}
	
	//InputStream is
	//StringBuilder
		
	@SuppressWarnings("deprecation")
	public void convertExcelCellData()
	{
		try
		{			
			FileInputStream is = new FileInputStream("D:\\Sumit\\Big_Data\\Java\\Sample.xls");			
			HSSFWorkbook workbook = new HSSFWorkbook(is); // for xls
			//XSSFWorkbook workbook = new XSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0); // Assume there is only one sheet inside Excel for xls
			//XSSFSheet sheet = workbook.getSheetAt(0);
			//XSSFCell cell;
			
			Iterator<Row> rowIterator = sheet.iterator(); // Get Rows from Excel Sheet for xls
								
		
			//Iterator rowIterator = sheet.rowIterator(); // For xlsx
			
			while(rowIterator.hasNext())
			{
				Row row = rowIterator.next(); // For xls
				//XSSFRow row = (XSSFRow) rowIterator.next(); // For xlsx
				
				Iterator<Cell> cellIterator =row.cellIterator(); // For xls
				//Iterator cellIterator = row.cellIterator(); // For xlsx
				while(cellIterator.hasNext())
				{
					Cell cell = cellIterator.next(); // For xls
					//cell = (XSSFCell) cellIterator.next(); // For xlsx
					
					//For xls
					switch(cell.getCellType())
					{
					case Cell.CELL_TYPE_NUMERIC:
						System.out.println(cell.getNumericCellValue());
						break;
					
						
					case Cell.CELL_TYPE_STRING:
						System.out.println(cell.getStringCellValue());
						break;	
					}
														
				}
				
				//System.out.println("OK " + count);
			}
			is.close();	
			workbook.close();	
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		
		//return strString;
	}
}
