package com.technogearup.E2EProject;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
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
		driver.manage().window().maximize();
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
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 5);
	    WebElement menu = header.getUserInfo();
	    Actions builder = new Actions(driver);
	    builder.moveToElement(menu).build().perform();
	    boolean requirePageRefresh = true;
	    try {
			wait.until(ExpectedConditions.visibilityOf(header.getInterceptedElement()));
			System.out.println("InterceptedElement visible");
	    } catch (Exception e) {
	    	requirePageRefresh = false;
	    	System.out.println("No InterceptedElement present");
		}
	    if(requirePageRefresh) {
	    	if(ExpectedConditions.visibilityOf(header.getInterceptedElement()) != null) {
		    	closePopup();
		    }
		    driver.navigate().refresh();
		    driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		    menu = header.getUserInfo();
		    builder = new Actions(driver);
	    	wait.until(ExpectedConditions.elementToBeClickable(menu));
	    	builder.moveToElement(menu).build().perform();
		    
	    }
	    
	    return 0;
	}
	
	private void closePopup() {

			// Shadow root closed
			Actions builder = new Actions(driver);
			
			builder.moveToElement(header.getPopupShadowHost()).build().perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			
			builder.sendKeys(Keys.ENTER).perform();

	}
	
	@Test
	public void userInfo() {
		hoverOnUserIcon();		
		
	    String title = header.getMyAccountDropdownTitle().getText().trim();
		log.info("My Account Dropdown Title: "+ title);
		Assert.assertEquals(title, "My Account");
		
		LinkedList<String> missingListItems = new LinkedList<String>();
		LinkedList<String> missingListLinks = new LinkedList<String>();
		List<WebElement>  menuItems = header.getMyAccountDropdownItems();
		String[]  givenMenuItemTxts = {"Login","Register","Care Club"};
		String[]  givenMenuItemLinks = {"https://www.neutrogena.com/account","https://www.neutrogena.com/register","https://www.neutrogena.com/care-club.html"};
		for(int i = 0; i < menuItems.size(); i++) {
			WebElement we = menuItems.get(i);
			if(!we.getText().trim().equalsIgnoreCase(givenMenuItemTxts[i])) {
				missingListItems.add(we.getText());
			}
			if(!we.getAttribute("href").equalsIgnoreCase(givenMenuItemLinks[i])) {
				missingListLinks.add(we.getAttribute("href"));
			}
		}
		int x = 0;
		if((x = missingListItems.size()) == 0) {
			log.info("User Info: "+ "All menu items present");
			Assert.assertEquals(x, 0, "User Info: "+ "All menu items present");
		}else {
			log.info("User Info: "+ missingListItems.toString() +" menu items are not in proper place");
			Assert.assertEquals(x, 0, "User Info: "+ missingListItems.toString() + "menu items are not in proper place");
		}
		int y = 0;
		if((y = missingListLinks.size()) == 0) {
			log.info("User Info: "+ "All menu item links are present");
			Assert.assertEquals(y, 0, "User Info: "+ "All menu item links are present");
		}else {
			log.info("User Info: "+ missingListLinks.toString() +" menu item links are not in proper place");
			Assert.assertEquals(y, 0, "User Info: "+ missingListLinks.toString() + "menu item links are not in proper place");
		}
		
		
		// ... click on menu items..
		boolean linkError = false;
		for(int i = 0; i < menuItems.size(); i++) {
			hoverOnUserIcon();
			header.getMyAccountDropdownItem(givenMenuItemTxts[i]).click();
			driver.getCurrentUrl().contentEquals(givenMenuItemLinks[i]);

			if(driver.getCurrentUrl().contentEquals(givenMenuItemLinks[i])) {
				log.info("User Info: "+ givenMenuItemLinks[i] + " link wroks fine");
			}else {
				linkError = true;
				log.info("User Info: "+ givenMenuItemLinks[i] + " link does not wrok properly");
			}
		}
		
		Assert.assertEquals(false, linkError, "User Info: menu item links are not redirected to proper page");

		
	}
	
}
