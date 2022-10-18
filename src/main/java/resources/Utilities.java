package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	public static Logger log = LogManager.getLogger(Utilities.class.getName());
	
	public Utilities() {}
	
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

	public boolean isRedirectUrlOf(String url) {
		boolean isRedirected = false;
		HttpURLConnection cn;
	      try {
			cn = (HttpURLConnection)new URL(url).openConnection();
			  cn.setRequestMethod("HEAD");
			  cn.connect();
			  int res = cn.getResponseCode();
			  if(res > 199 && res < 399) {
				  isRedirected = true;
			  }
			  cn.disconnect();
		} catch (MalformedURLException e) {
			log.info(url +"-- "+ e.getMessage());
		} catch (ProtocolException e) {
			log.info(url +"-- "+ e.getMessage());
		} catch (IOException e) {
			log.info(url +"-- "+ e.getMessage());
		}
		return isRedirected;
	}
	
}
