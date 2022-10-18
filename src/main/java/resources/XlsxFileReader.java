package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxFileReader {

	public XlsxFileReader() {
	}
	
	public LinkedHashMap<String,String> ReadFooterFile(String section) throws IOException {
		LinkedHashMap<String,String> data = new LinkedHashMap<String, String>();
		FileInputStream fs = new FileInputStream("./src/main/java/resources/Footer.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) 
        {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
             
            cellIterator.next(); // skipping as first cell is Sl.No.
            if(cellIterator.next().getStringCellValue().equalsIgnoreCase(section)){
            	Cell cell1 = cellIterator.next();
            	Cell cell2 = cellIterator.next();
            	data.put(cell1.getStringCellValue(), cell2.getStringCellValue());
            }
        }
        return data;
	}

	
}
