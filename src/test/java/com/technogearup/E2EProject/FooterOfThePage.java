package com.technogearup.E2EProject;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.Footer;
import pageObjects.Header;
import resources.Base;
import resources.XlsxFileReader;

public class FooterOfThePage extends Base{
	//public WebDriver driver;
	//private Footer footer;
	private XlsxFileReader xlsxReader;
	
	@BeforeClass
	public void launcBrowser() throws IOException {
//		driver = initializerDriver();
		xlsxReader = new XlsxFileReader();
//		log.info("Page for Footer: "+"Driver is Initialized");
//		driver.get(prop.getProperty("url"));
//		log.info("Page for Footer: "+"Successfully open URL");
//		driver.manage().window().maximize();
//		footer = new Footer(driver);
//		scrollToTheBottomOfThePage();
	}
	
	private void scrollToTheBottomOfThePage() {
		//driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		try{Thread.sleep(10000);}catch(Exception e) {;}
		WebElement fd = footer.getFooterDisclaimer();
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		   js.executeScript("arguments[0].scrollIntoView();",fd);
		while(true) {
			if(!footer.getFooterDisclaimer().isDisplayed()) {
				js = (JavascriptExecutor) driver;  
				   js.executeScript("arguments[0].scrollIntoView();",fd);
			}else {
				break;
			}
		}
		log.info("Page for Footer: "+"Scroll to the bottom of the page");
	}
	
	//@AfterClass
	public void closeBrowser() {
		driver.close();
		log.info("Page (footer): "+"Closed");
	}
	
	//@Test(priority = 1)
	public void footerContent() {
		Assert.assertTrue(footer.getEmailSignUpHeader().isDisplayed());
	}

	@Test(priority = 2)
	public void companyInfo() {
		LinkedHashMap<String,String> linkMap = new LinkedHashMap<String, String>();
		try {
			linkMap = xlsxReader.ReadFooterFile("Company Info");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(linkMap.size() > 0) {
			Iterator<Entry<String, String>> it = linkMap.entrySet().iterator();
			while(it.hasNext()) {
				Entry<String, String> entry = it.next();
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
		}
		
//		List<WebElement> list = footer.getCompanyInfos();
//		Iterator<WebElement> it = list.iterator();
//		while(it.hasNext()) {
//			WebElement we = it.next();
//			System.out.println(we.getText());
//			System.out.println(we.getAttribute("href"));
//		}
		
	}
	
}
