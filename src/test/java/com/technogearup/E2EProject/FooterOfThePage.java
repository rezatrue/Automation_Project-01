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
import resources.Utilities;

public class FooterOfThePage extends Base{
	public WebDriver driver;
	private Footer footer;
	private Utilities utilities;
	
	@BeforeClass
	public void launcBrowser() throws IOException {
		driver = initializerDriver();
		utilities = new Utilities();
		log.info("Page for Footer: "+"Driver is Initialized");
		driver.get(prop.getProperty("url"));
		log.info("Page for Footer: "+"Successfully open URL");
		driver.manage().window().maximize();
		footer = new Footer(driver);
		scrollToTheBottomOfThePage();
	}
	
	private void scrollToTheBottomOfThePage() {
		//Thread sleep for loading page component
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
	
	@Test(priority = 1)
	public void footerContent() {
		if(footer.getEmailSignUpHeader().isDisplayed()) {
			log.info("Footer->Content: "+" Email SignUp section ispresent.");
		}else {
			log.info("Footer->Content: "+" Email SignUp is not visible.");
			Assert.assertTrue(false, "Footer->companyInfo: "+" Email SignUp is not visible.");
		}
		
		if(footer.getCompanyInfo().isDisplayed()) {
			log.info("Footer->Content: "+" Company Info section is present.");
		}else {
			log.info("Footer->Content: "+" Company Info is not visible.");
			Assert.assertTrue(false, "Footer->companyInfo: "+" Company Info is not visible.");
		}
		
		if(footer.getCustomerService().isDisplayed()) {
			log.info("Footer->Content: "+" Customer Service section is present.");
		}else {
			log.info("Footer->Content: "+" Customer Service is not visible.");
			Assert.assertTrue(false, "Footer->companyInfo: "+" Customer Service is not visible.");
		}
		
		if(footer.getOnlinePurchases().isDisplayed()) {
			log.info("Footer->Content: "+" Online Purchases section is present.");
		}else {
			log.info("Footer->Content: "+" Online Purchases is not visible.");
			Assert.assertTrue(false, "Footer->companyInfo: "+" Online Purchases is not visible.");
		}
		
		if(footer.getFollowUs().isDisplayed()) {
			log.info("Footer->Content: "+" Follow Us section is present.");
		}else {
			log.info("Footer->Content: "+" Follow Us is not visible.");
			Assert.assertTrue(false, "Footer->companyInfo: "+" Follow Us is not visible.");
		}
		
		if(footer.getFooterBadges().isDisplayed()) {
			log.info("Footer->Content: "+" Footer Badges section is present.");
		}else {
			log.info("Footer->Content: "+" Footer Badges is not visible.");
			Assert.assertTrue(false, "Footer->companyInfo: "+" Footer Badges is not visible.");
		}
		
		if(footer.getFooterUtility().isDisplayed()) {
			log.info("Footer->Content: "+" Footer Utility section is present.");
		}else {
			log.info("Footer->Content: "+" Footer Utility is not visible.");
			Assert.assertTrue(false, "Footer->companyInfo: "+" Footer Utility is not visible.");
		}
		
	}

	@Test(priority = 2)
	public void companyInfo() {
		LinkedList<String> missingLinks = new LinkedList<String>();
		LinkedList<String> extraLinks = new LinkedList<String>();
		LinkedHashMap<String,String> linkMap = new LinkedHashMap<String, String>();
		try {
			linkMap = utilities.ReadFooterFile("Company Info");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<WebElement> list = footer.getCompanyInfos();
		
		if(linkMap.size() > 0 && list.size() > 0) {
			Iterator<Entry<String, String>> entries = linkMap.entrySet().iterator();
			Iterator<WebElement> it = list.iterator();
			
			while(entries.hasNext() && it.hasNext()) {
				Entry<String, String> entry = entries.next();
				WebElement we = it.next();
				if(!we.getText().equalsIgnoreCase(entry.getKey()))
					missingLinks.add(entry.getKey());
				if(!we.getAttribute("href").startsWith(entry.getValue())) // there might have some utm parameters
					missingLinks.add(entry.getValue());
			}
			if(entries.hasNext()) {
				while(entries.hasNext()) {
					Entry<String, String> entry = entries.next();
					missingLinks.add(entry.getKey() +" : "+ entry.getValue());}
				log.info("Footer->companyInfo: "+missingLinks.toString()+" Doc have some extra component");
			}
			else log.info("Footer->companyInfo: Doc does not have any extra component");
			
			if(it.hasNext()) {
				while(it.hasNext()) {
					WebElement we = it.next();
					missingLinks.add(we.getText()+" : "+ we.getAttribute("href"));}
				log.info("Footer->companyInfo: "+extraLinks.toString()+" Extra link present in the page");
			}
			else log.info("Footer->companyInfo: there is no extra link in the page");
		}
		
		LinkedList<String> notRedirectedProperly = new LinkedList<String>();
		Iterator<WebElement> it = list.iterator();
		while(it.hasNext()) {
			String url = it.next().getAttribute("href");
			if(!utilities.isRedirectUrlOf(url)) {
				notRedirectedProperly.add(url);
				log.info("Footer->companyInfo: "+url+" doesn't redirect");
			}
		}
		
		if(missingLinks.size() > 0) Assert.assertTrue(false,"Footer->companyInfo: "+missingLinks.toString()+" Doc have some extra component");
		if(extraLinks.size() > 0) Assert.assertTrue(false, "Footer->companyInfo: "+extraLinks.toString()+" Extra link present in the page"); 
		else Assert.assertTrue(true, "Footer->companyInfo: All component present in the page");
		
		if(notRedirectedProperly.size() > 0) Assert.assertTrue(false, "Footer->companyInfo: " 
		+ notRedirectedProperly + " didn't redirected properly.");
		else if(notRedirectedProperly.size() == 0) Assert.assertTrue(true, "Footer->companyInfo: "
				+ "All link rediredted properly");
	}
	
}