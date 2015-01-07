package worksheet;


import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class WriteXlsxFile  {
//Processes requests for both HTTP
public static void main(String []args){

	try{
		 String excelFileName = "westt.xlsx";//name of excel file    
         String sheetName = "Sheet1";//name of sheet    
         SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk  
         Sheet sheet = wb.createSheet(sheetName);    
     
         //iterating r number of rows    
         for (int r = 0; r < 55555; r++) {    
             Row row = sheet.createRow(r);    
     
             //iterating c number of columns    
             for (int c = 0; c < 5; c++) {    
                 Cell cell = row.createCell(c);    
                 cell.setCellValue("Cell " + r + " " + c); 
                 
             }    
             if ( r % 1000 == 0) {  
                 System.out.println(r);  
             }  
         }    
     
         FileOutputStream out = new FileOutputStream(excelFileName);  
         wb.write(out);  
         out.close();  
	}
	catch(Exception e){
	System.out.println("excpetion"+e);
	}

}
}