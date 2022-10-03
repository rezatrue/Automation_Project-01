package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	
	public Properties prop;
	public static Logger log = LogManager.getLogger(Base.class.getName());
	public WebDriver initializerDriver() throws IOException {
		WebDriver driver = null;
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/java/resources/data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "res/drivers/chromedriver.exe");
			ChromeOptions ChOptions = new ChromeOptions();
			ChOptions.addArguments("--disable-notifications");
			driver = new ChromeDriver(ChOptions);
		}
		else if(browserName.equals("firefox")){
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("EI")){
			
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public String takeScreenShot(WebDriver driver, String fileName) throws IOException {
		TakesScreenshot tss = (TakesScreenshot)driver;
		
		String destinationPath = System.getProperty("user.dir")+"\\reports\\"+fileName+".png";
		File destinationFile = new File(destinationPath);
		File source = tss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, destinationFile);
		return destinationPath;
	}

}
