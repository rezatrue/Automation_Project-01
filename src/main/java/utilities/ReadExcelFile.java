package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadExcelFile {

	public static FileInputStream inputSteam;
	public static XSSFWorkbook workbook;

	@DataProvider(name = "dummyPassword")
	public Object[][] excelDP() throws IOException {

		String fileName = System.getProperty("user.dir") + "/TestData/data.xlsx";

		Object[][] arrObj = getDummyPassword(fileName, "passwords");
		return arrObj;
	}
	
	public String[][] getDummyPassword(String fileName, String sheetName) {

		String[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheetName);
			XSSFRow row = sh.getRow(0);
			int noOfRows = sh.getPhysicalNumberOfRows();
			int noOfCols = row.getLastCellNum();
			data = new String[noOfRows - 1][noOfCols-1];
			for (int i = 1; i < noOfRows; i++) {
				for (int j = 1; j < noOfCols; j++) {
					row = sh.getRow(i);
					XSSFCell cell = row.getCell(j);
					data[i - 1][j - 1] = cell.getStringCellValue();
				}
			}
		} catch (Exception e) {
			System.out.println("The exception is: " + e.getMessage());
		}
		return data;
	}

	@DataProvider(name = "dummyRegister")
	public Object[][] excelDR() throws IOException {

		String fileName = System.getProperty("user.dir") + "/TestData/data.xlsx";

		Object[][] arrObj = getDummyRegister(fileName, "register");
		return arrObj;
	}

	public String[][] getDummyRegister(String fileName, String sheetName) {

		String[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheetName);
			XSSFRow row = sh.getRow(0);
			int noOfRows = sh.getPhysicalNumberOfRows();
			System.out.println("noOfRows :"+noOfRows);
			int noOfCols = row.getLastCellNum();
			data = new String[noOfRows - 1][noOfCols-1];
			for (int i = 1; i < noOfRows; i++) {
				row = sh.getRow(i);
				try{data[i - 1][0] = row.getCell(1).getStringCellValue();}catch(NullPointerException ne) {} //first name
				try{data[i - 1][1] = row.getCell(2).getStringCellValue();}catch(NullPointerException ne) {}  //last name
				try{data[i - 1][2] = row.getCell(3).getNumericCellValue()+"";}catch(NullPointerException ne) {}  //mobile
				try{data[i - 1][3] = row.getCell(4).getStringCellValue();}catch(NullPointerException ne) {}  //month
				try{data[i - 1][4] = String.valueOf((int)row.getCell(5).getNumericCellValue());}catch(NullPointerException ne) {}  //year
				try{data[i - 1][5] = row.getCell(6).getStringCellValue();}catch(NullPointerException ne) {}  //email
				try{data[i - 1][6] = row.getCell(7).getStringCellValue();}catch(NullPointerException ne) {}  //pass
				try{data[i - 1][7] = row.getCell(8).getStringCellValue();}catch(NullPointerException ne) {}  //confirm
				try{data[i - 1][8] = row.getCell(9).getBooleanCellValue()+"";}catch(NullPointerException ne) {}  //add to list
				try{data[i - 1][9] = row.getCell(10).getBooleanCellValue()+"";}catch(NullPointerException ne) {}  //status
				
			}
		} catch (Exception e) {
			System.out.println("The exception is: " + e.getMessage());
		}
		System.out.println(data.length);
		return data;
	}
	
	
}
