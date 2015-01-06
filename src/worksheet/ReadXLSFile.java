package worksheet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ReadXLSFile {
	public static void main(String[] args) {
		try {
				FileInputStream fileInputStream = new FileInputStream("testing.xls");
			     
	            //Get the workbook instance for XLS file 
	            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
	 
	            //Get first sheet from the workbook
	            HSSFSheet sheet = workbook.getSheetAt(0);
	             
	            //Iterate through each rows from first sheet
	            Iterator rowIterator = sheet.rowIterator();
	            while(rowIterator.hasNext()) {
	            	//HSSFRow myRow = 
	            	HSSFRow row = (HSSFRow) rowIterator.next();
	                //For each row, iterate through each columns
	                Iterator cellIterator = row.cellIterator();
	                while(cellIterator.hasNext()) {
	                     
	                	HSSFCell cell = (HSSFCell)cellIterator.next();
	                     
	                    switch(cell.getCellType()) {
	                        case Cell.CELL_TYPE_BOOLEAN:
	                            System.out.print(cell.getBooleanCellValue() + "\t\t");
	                            break;
	                        case Cell.CELL_TYPE_NUMERIC:
	                        	
	                        	if(HSSFDateUtil.isCellDateFormatted( cell))
	                        	   {
	                        		System.out.print("Date:"+cell.getDateCellValue());
	                        	   }else{
	                            System.out.print("Nmeric:"+cell.getNumericCellValue() + "\t\t");
	                        	   }
	                            break;
	                        case Cell.CELL_TYPE_STRING:
	                            System.out.print(cell.getStringCellValue() + "\t\t");
	                            break;
	                    }
	                }
	                System.out.println("");
	            }
	             
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
