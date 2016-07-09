package ec.eac.sitac.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
	private static InputStream file = null;
	private static Workbook workbook = null;
	private static Sheet sheet = null;
	
	public static void initialize(String filePath) throws IOException {

		file = new FileInputStream(new File(filePath));
        
		//Create Workbook instance holding reference to .xlsx file
		try 
		{
			workbook = WorkbookFactory.create(file);
		} 
		catch (Exception e) 
		{
			file = new FileInputStream(new File(filePath));

			if (filePath.contains(".xlsx"))
				workbook = new XSSFWorkbook(file); 
			else if (filePath.contains(".xls"))
				workbook = new HSSFWorkbook(file);
		}
         
        sheet = workbook.getSheetAt(0);
	}
	
    public static void readFile(String filePath) throws IOException
    {
    	//Iterate through each rows one by one
    	Iterator<Row> rowIterator = sheet.iterator();
    	while (rowIterator.hasNext())
    	{
    		Row row = rowIterator.next();
    		//For each row, iterate through all the columns
    		Iterator<Cell> cellIterator = row.cellIterator();

    		while (cellIterator.hasNext())
    		{
    			Cell cell = cellIterator.next();
    			//Check the cell type and format accordingly
    			switch (cell.getCellType())
    			{
    			case Cell.CELL_TYPE_NUMERIC:
    				System.out.print(cell.getNumericCellValue() + "t");
    				break;
    			case Cell.CELL_TYPE_STRING:
    				System.out.print(cell.getStringCellValue() + "t");
    				break;
    			}
    		}
    		System.out.println("");
    	}
    }
    
    /**
     * Lee un archivo Excel y devuelve una lista de Objects(filas)
     * 
     * @param filePath
     * @param readFrom
     * @param countToRead
     * @return
     * @throws IOException
     * 
     * since 1.0
     */
    public static List<Map<Integer, String>> readFile(String filePath, int readFrom, int countToRead) throws IOException
    {
    	//Iterate through each rows one by one
    	Iterator<Row> rowIterator = sheet.iterator();
    	int count = 0;
    	List<Map<Integer, String>> resultado = new ArrayList<Map<Integer, String>>();
    	
    	while (rowIterator.hasNext())
    	{
    		Map<Integer, String> map = new HashMap<Integer, String>();  
    		Row row = rowIterator.next();
    		count++;

    		if (count < readFrom) // this row is not taken into account
    			continue;

    		if (count > readFrom + countToRead - 1) // remaining rows will be not taken into account
    			break;

    		//For each row, iterate through all the columns
    		Iterator<Cell> cellIterator = row.cellIterator();



    		while (cellIterator.hasNext())
    		{
    			Cell cell = cellIterator.next();
    			//Check the cell type and format accordingly

    			switch (cell.getCellType())
    			{
    			case Cell.CELL_TYPE_NUMERIC:
    				if (DateUtil.isCellDateFormatted(cell)) 
    					map.put(cell.getColumnIndex(), String.valueOf(cell.getDateCellValue()));
    				else
    					map.put(cell.getColumnIndex(), String.valueOf(cell.getNumericCellValue()));
    				break;
    				
    			case Cell.CELL_TYPE_STRING:
    				map.put(cell.getColumnIndex(), cell.getStringCellValue());
    				break;
    			}
    		}
    		resultado.add(map);
    	}
    	
    	file.close();
    	return resultado;
    }
    
    public static String getCellValue(String cellName) {
    	Cell cell = null;

    	CellReference ref = new CellReference(cellName);
    	Row r = sheet.getRow(ref.getRow());
    	if (r != null) {
    		cell = r.getCell(ref.getCol());
    		return cell.getStringCellValue();
    	}
    	
    	return null; // empty cell
    }
    
    public static String getCellValue(String col, Integer row) {
    	String cellName = col + row.toString();
    	
    	return getCellValue(cellName);
    }
    
    public static void closeFile() throws IOException {
    	file.close();
    	
    	file = null;
    	workbook = null;
    	sheet = null;
    }
}
