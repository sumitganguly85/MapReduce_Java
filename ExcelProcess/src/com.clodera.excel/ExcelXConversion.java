package com.cloudera.excel;

//import java.io.FileInputStream;
//import java.io.IOException;
import java.util.Iterator;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelXConversion {

	private StringBuilder currentString = null;
	/*
	public static void main(String[] args) throws IOException
	{
		ExcelXConversion objExcelXConversion = new ExcelXConversion();
		objExcelXConversion.convertExcelXCellData();
		
	}
	*/
		
	@SuppressWarnings("deprecation")
	public StringBuilder convertExcelXCellData(InputStream is)
	{		
		//FileInputStream is;
		try
		{
			//is = new FileInputStream("D:\\Sumit\\Big_Data\\Java\\Sample1.xlsx");
			XSSFWorkbook myWorkBook = new XSSFWorkbook (is);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			Iterator<Row> rowIterator = mySheet.iterator();
			
			while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                
                Iterator<Cell> cellIterator = row.cellIterator(); 
                while (cellIterator.hasNext()) 
                { 
                	Cell cell = cellIterator.next();
                	
                	switch (cell.getCellType()) 
                	{ 
                	case Cell.CELL_TYPE_STRING:
                		currentString.append(cell.getStringCellValue() + "\t");                		
                		break; 
                	case Cell.CELL_TYPE_NUMERIC: 
                		currentString.append(cell.getNumericCellValue() + "\t"); 
                		break; 
                	case Cell.CELL_TYPE_BOOLEAN: 
                		currentString.append(cell.getBooleanCellValue() + "\t");
                		break;                	
                	//default : System.out.print(cell.getStringCellValue() + "\t");
                	default :
                		currentString.append(cell.getStringCellValue() + "\t");                		
                		break; 
                	} 
                	
                }
                currentString.append("\n");
                }
			//System.out.println("");
			is.close();	
			myWorkBook.close();
		}		
		catch (Exception ex)
		{
			ex.printStackTrace();
			
		}
		
		return currentString;
		
	}
}
