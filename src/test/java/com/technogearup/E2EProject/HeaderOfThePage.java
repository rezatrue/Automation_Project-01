package com.technogearup.E2EProject;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.Header;

import resources.Base;

public class HeaderOfThePage extends Base{
	public WebDriver driver;
	private Header header;
	
	@BeforeClass
	public void launcBrowser() throws IOException {
		driver = initializerDriver();
		log.info("HomePage for Header: "+"Driver is Initialized");
		driver.get(prop.getProperty("url"));
		log.info("HomePage for Header: "+"Successfully open URL");
		driver.manage().window().fullscreen();
		header = new Header(driver);
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
		log.info("HomePage: "+"Closed");
	}
	
	@Test
	public void headerContent() {
		
		boolean isImagePresent = header.getLogo() != null ? true : false;
		log.info(isImagePresent ? "Header: "+"has a Logo" : "Header: "+"doesn't have any Logo");
		boolean isSearchOptionPresent = header.getSearchInput() != null ? true : false;
		log.info(isSearchOptionPresent ? "Header: "+"has search option" : "Header: "+"does not have search option");
		boolean isUserInconPresent = header.getUserInfo() != null ? true : false;
		log.info(isUserInconPresent ? "Header: "+"have user icon" : "Header: "+"does not have user icon");
		if(isImagePresent && isSearchOptionPresent && isUserInconPresent) Assert.assertTrue(true, "Header have all the components.");
		else Assert.assertTrue(false, "Header component missing");
		
	}
	
	@Test
	public void logo() {
		
		boolean isImagePresent = header.getLogoImage() != null ? true : false;
		log.info(isImagePresent ? "Logo: "+"image present" : "Logo: "+"image missing");
		boolean isImageLinked = header.getLogoLink() != null ? true : false;
		log.info(isImageLinked ? "Logo: "+"linked to Home page" : "Logo: "+"is not linked to Home page");
		boolean isImageTooltipPresent = header.getLogoTooltip() != null ? true : false;
		log.info(isImageTooltipPresent ? "Logo: "+"has tooltip" : "Logo: "+"doesn't have any tooltip");
		if(isImagePresent && isImageLinked && isImageTooltipPresent) Assert.assertTrue(true, "Logo : has all the Contents.");
		else Assert.assertTrue(false, "Logo content missing");
		
	}
	
	
	@Test
	public void searchOption() {
		boolean isFocusable = true;
		try {
			header.getSearchInput().click();
		} catch (Exception e) {
			isFocusable = false;
		}
		log.info(isFocusable ? "Search: "+"is focousable" : "Search: "+"is not focousable");
		
		// How to detect Hover mouse pointer change?
		
		String inputTxt = "skincare";
		header.getSearchInput().sendKeys(inputTxt);
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		String text = header.getSearchInput().getAttribute("value");
		boolean isTextPresent =inputTxt.equalsIgnoreCase(text);
		log.info(isTextPresent ? "Search: "+"input successful" : "Search: "+text+" input is not successful");
		
		boolean isSearchButtonPresent = header.getSearchButton() != null ? true : false;
		log.info(isSearchButtonPresent ? "Search: "+"Button is present" : "Search: "+"button not not present");
		
		boolean isCurrentSearchUrl = false;
		if(isSearchButtonPresent) {
			header.getSearchButton().click();
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
			String searchUrlStartWith = "https://www.neutrogena.com/search?q=" + inputTxt;
			isCurrentSearchUrl = driver.getCurrentUrl().startsWith(searchUrlStartWith);
			log.info(isCurrentSearchUrl ? "Search: "+" option is working" : "Search: "+"option is not working");
		}
		
		if(isTextPresent && isSearchButtonPresent && isCurrentSearchUrl) Assert.assertTrue(true, "Search : is good");
		else Assert.assertTrue(false, "Search : not proper");
	}
	
	int hoverOnUserIcon() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
	    WebElement menu = header.getUserInfo();
	    Actions builder = new Actions(driver);
	    builder.moveToElement(menu).build().perform();
	    try {
			wait.until(ExpectedConditions.presenceOfElementLocated(header.getMyAccountDropdownTitleBy()));
		} catch (Exception e) {
			return 1;
		}
	    return 0;
	}
	
	
	public void userInfo() {
		if(hoverOnUserIcon() != 0) {
			driver.navigate().refresh();
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
			driver.manage().window().fullscreen();
			hoverOnUserIcon();
		}
	    
	    String title = header.getMyAccountDropdownTitle().getText().trim();
		log.info("My Account Dropdown Title: "+ title);
		Assert.assertEquals(title, "My Account");
		
		LinkedList<String> missingList = new LinkedList<String>();
		String[]  menuItems = (String[]) header.getMyAccountDropdownItems().toArray();
		String[]  givenMenuItems = {"Login","Register","Care Club"};
		for(int i=0; i <= givenMenuItems.length; i++) {
			if(givenMenuItems[i] != menuItems[i]) {
				missingList.add(givenMenuItems[i]);
			}
		}
		
		if(missingList.size() == 0) {
			log.info("User Info: "+ "All menu items present");
			Assert.assertEquals(true, "User Info: "+ "All menu items present");
		}else {
			log.info("User Info: "+ missingList.toArray().toString() +" menu items are not in proper place");
			Assert.assertEquals(false, "User Info: "+ missingList.toArray().toString() + "menu items are not in proper place");
		}
		
		
		

	}
	
}
