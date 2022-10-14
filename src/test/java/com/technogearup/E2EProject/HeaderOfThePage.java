package com.technogearup.E2EProject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
	
	//@AfterClass
	public void closeBrowser() {
		driver.close();
		log.info("HomePage: "+"Closed");
	}
	
	@Test(priority = 1)
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
	
	@Test(priority = 2)
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
	
	
	@Test(priority = 3)
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
	
	@Test(priority = 4)
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
	
	@Test(priority = 5)
	public void navigation() {
		
		LinkedList<String> missingNavItems = new LinkedList<String>();
		LinkedList<String> missingNavLinks = new LinkedList<String>();
		List<WebElement>  navItems = header.getNavAnchors();
		String[]  givenNavItemTxts = {"Holiday","What's New","Skin Care","Sun","Makeup", "Hair Care", "SKIN360", "Skin Advice"};
		String[]  givenNavItemLinks = {"https://www.neutrogena.com/holiday-shop","https://www.neutrogena.com/#",
				"https://www.neutrogena.com/skin", "https://www.neutrogena.com/sun", "https://www.neutrogena.com/makeup",
				"https://www.neutrogena.com/haircare", "https://www.neutrogena.com/skin360/try-now.html", "https://www.neutrogena.com/skin-advice"};
		for(int i = 0; i < navItems.size(); i++) {
			WebElement we = navItems.get(i);
			if(!we.getText().trim().equalsIgnoreCase(givenNavItemTxts[i])) {
				missingNavItems.add(we.getText().trim());
			}
			if(!we.getAttribute("href").equalsIgnoreCase(givenNavItemLinks[i])) {
				missingNavLinks.add(we.getAttribute("href"));
			}
		}
		int x = 0;
		if((x = missingNavItems.size()) == 0) {
			log.info("Nav: "+ "All nav items present");
			Assert.assertEquals(x, 0, "Nav: "+ "All nav items present");
		}else {
			log.info("Nav: "+ missingNavItems.toString() +" nav items are not in proper place");
			Assert.assertEquals(x, 0, "Nav: "+ missingNavItems.toString() + "nav items are not in proper place");
		}
		int y = 0;
		if((y = missingNavLinks.size()) == 0) {
			log.info("Nav: "+ "All nav item links are present");
			Assert.assertEquals(y, 0, "Nav: "+ "All nav item links are present");
		}else {
			log.info("Nav: "+ missingNavLinks.toString() +" nav item links are not in proper place");
			Assert.assertEquals(y, 0, "Nav: "+ missingNavLinks.toString() + "nav item links are not in proper place");
		}
		
		// ... Click on the Nav items
		LinkedList<String> wrongRedirectionList = new LinkedList<String>();
		for(int i = 0; i < givenNavItemTxts.length; i++) {
			WebElement we = header.getNav(givenNavItemTxts[i]);
			try {
				we.click();
			} catch (ElementClickInterceptedException e) {
				closePopup();
				we.click();
			}
			String url = driver.getCurrentUrl();
			if(!givenNavItemLinks[i].equalsIgnoreCase(url)) {
				wrongRedirectionList.add(givenNavItemTxts[i]);
			}
			if(!url.startsWith("https://www.neutrogena.com")) driver.get("https://www.neutrogena.com");
		}
		int wr = 0;
		if(( wr = wrongRedirectionList.size()) == 0) {
			log.info("Nav: "+ " items properly redircted");
			Assert.assertEquals(wr, 0, "Nav: "+ " items properly redirected");
		}else {
			log.info("Nav: "+ wrongRedirectionList.toString() +" items don't redirect properly");
			Assert.assertEquals(wr, 0, "Nav: "+ wrongRedirectionList.toString() + "items don't redirect properly");
		}
		
		//................ hover on nav ..........................
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 3);
		
		LinkedList<String> missingSubMenuContainers = new LinkedList<String>();
		HashMap<String, String> badUrl = new HashMap<String, String>();
		for(int i = 0; i < givenNavItemTxts.length; i++) {
			if(givenNavItemTxts[i].equalsIgnoreCase("SKIN360")) continue; // SKIN360 has no sub menu
			
			WebElement we = header.getNav(givenNavItemTxts[i]);
			try {
				action.moveToElement(we).build().perform();
				wait.until(ExpectedConditions.visibilityOf(header.getNavSubMenuContainer(givenNavItemTxts[i])));
			} catch (ElementClickInterceptedException e1) {
				closePopup();
			} catch (Exception e2) {
				missingSubMenuContainers.add(givenNavItemTxts[i]);
			}

			Iterator<WebElement> it = header.getNavSubMenu(givenNavItemTxts[i]).iterator();
			while(it.hasNext()) {
				 String url = it.next().getAttribute("href");
			      try {
					HttpURLConnection cn = (HttpURLConnection)new URL(url).openConnection();
					  cn.setRequestMethod("HEAD");
					  cn.connect();
					  int res = cn.getResponseCode();
					  if(res > 399) {
						  badUrl.put(url,String.valueOf(res));
					  }
				} catch (MalformedURLException e) {
					badUrl.put(url ,e.getMessage());
				} catch (ProtocolException e) {
					badUrl.put(url ,e.getMessage());
				} catch (IOException e) {
					badUrl.put(url ,e.getMessage());
				}
			      
			}
			if(!driver.getCurrentUrl().startsWith("https://www.neutrogena.com")) driver.get("https://www.neutrogena.com");
		}
		
		
		int smc = 0;
		if(( smc = missingSubMenuContainers.size()) == 0) {
			log.info("Nav: "+ "All sub menu containers are present");
			Assert.assertEquals(smc, 0, "Nav: "+ "All sub menu containers are present");
		}else {
			log.info("Nav: "+ missingSubMenuContainers.toString() + " sub menu containers are not visible");
			Assert.assertEquals(smc, 0, "Nav: "+ missingSubMenuContainers.toString() + " sub menu containers are not visible");
		}
		
		wr = 0;
		if(( wr = badUrl.size()) == 0) {
			log.info("Nav: "+ "All sub menu links are good");
			Assert.assertEquals(wr, 0, "Nav: "+ "All sub menu links are good");
		}else {
			log.info("Nav: "+ badUrl.toString() + " links have some problem");
			Assert.assertEquals(wr, 0, "Nav: "+ badUrl.toString() + " links have some problem");
		}
		
	}
	
	@Test(priority = 6)
	public void utilityBanner(){
		//.............. contents ..........................
		
		String ac = "We are experiencing shipments delays due to changes in our fulfillment center. We apologize for the inconvenience.";
		String txt = header.getAdvisoryContents().getText();
		if(ac.equalsIgnoreCase(txt)) {
			log.info("Utility Banner: "+ "Advisory contents are present");
			Assert.assertTrue(true);
		}else {
			log.info("Utility Banner: "+ "Advisory contents are not proper");
			Assert.assertTrue(false);
		}
		
		header.getSkipContent().click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

		if(header.getUtilityBannerWrapper().isDisplayed()) {
			log.info("Utility Banner: "+ "Skip Content CTA is working");
			Assert.assertTrue(true);
		}else {
			log.info("Utility Banner: "+ "Skip Content CTA is not working");
			Assert.assertTrue(false);
		}
		/* incomplete
		driver.get("https://www.neutrogena.com");
		driver.manage().window().maximize();
		
		LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
		contents.put("Fall Semi Annual Sale - 25% Off Sitewide", "https://www.neutrogena.com/search?q=neutrogena");
		contents.put("Free Shipping on all Orders", "https://www.neutrogena.com/offers.html");
		contents.put("15% off First Purchase", "//www.neutrogena.com/register");
		
		LinkedList<String> missingContent = new LinkedList<String>();
		LinkedList<String> missingUrl = new LinkedList<String>();
		
		if(header.getPromotionalContents().size() != contents.size()){
			log.info("Utility Banner: "+ "Content numbers are same");
		}
		
		Iterator<WebElement> it = header.getPromotionalContents().iterator();
		Iterator<Entry<String, String>> entrySets = contents.entrySet().iterator();
		
		while(it.hasNext() && entrySets.hasNext()) {
						
			WebElement we = it.next();
			Entry<String, String> entries = entrySets.next();
			
			String ctaTxt = we.getText();
			String ctaUrl = we.getAttribute("href");
			String givenTxt = entries.getKey();
			String givenUrl = entries.getValue();
			
			System.out.println(ctaTxt);
			System.out.println(givenTxt);
			
			if(!givenTxt.equalsIgnoreCase(ctaTxt)) {
				missingContent.add(ctaTxt);
			}
			if(!ctaUrl.equalsIgnoreCase(givenUrl)) {
				missingUrl.add(ctaUrl);
			}
		}

		
		if(missingContent.size() == 0) {
			log.info("Utility Banner: "+ "All promotional content header present in proper order");
			Assert.assertTrue(true);
		}else {
			log.info("Utility Banner: "+ missingContent.toString() + "  promotional contents headers are not proper");
			Assert.assertTrue(false);
		}
		
		if(missingUrl.size() == 0) {
			log.info("Utility Banner: "+ "All promotional content links are present");
			Assert.assertTrue(true);
		}else {
			log.info("Utility Banner: "+ missingUrl.toString()+ " promotional content links are not proper");
			Assert.assertTrue(false);
		}
		*/
		
		//...............home.....................
		header.getEmailSignup().click();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		header.getSignupEmail().sendKeys("alicin85025@tester.com");
		
		String month = "December";
		Actions action = new Actions(driver);
		action.click(header.getSignupBirthMonth()).build().perform();
		action.sendKeys(Keys.TAB).perform();
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Boolean monthSelected = false;
		while(!monthSelected) {
			if(header.getSignupMonth(month).isDisplayed()) {
				action.click(header.getSignupMonth(month)).build().perform();
				monthSelected = true;
			}
			action.sendKeys(Keys.ARROW_DOWN).perform();
		}
		
		
		String year = "1980";
		action.click(header.getSignupBirthYear()).build().perform();
		action.sendKeys(Keys.TAB).perform();
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Boolean yearSelected = false;
		while(!yearSelected) {
			if(header.getSignupYear(year).isDisplayed()) {
				action.click(header.getSignupYear(year)).build().perform();
				yearSelected = true;
			}
			action.sendKeys(Keys.ARROW_DOWN).perform();
		}
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		if(header.getSignupAgeConsentYes().isDisplayed()) {
			header.getSignupAgeConsentYes().click();
		}
		
		
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		driver.switchTo().frame(header.getSignupreCAPTCHAframe());
		header.getSignupreCAPTCHAlabel().click();
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		driver.switchTo().defaultContent();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", header.getSignupSubmit());
		header.getSignupSubmit().click();
		
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		if(header.getSignupSuccessMessage().isDisplayed()) {
			log.info("Utility Banner: "+ " Signup form successfully submitted");
		}else {
			log.info("Utility Banner: "+ " Signup Failed");
		}
		header.getEmailSignup().click();
		
		
		// ------------ language change ----------------------
		String givenLang1 = "Español";
		String givenLang1Url = "https://es.neutrogena.com/";
		String language1 = header.getChangeLanguage().getText();
		if(language1.equalsIgnoreCase(givenLang1)) {
			log.info("Utility Banner: "+ language1 + " is the First language. All fine");
			Assert.assertTrue(true);
		}else {
			log.info("Utility Banner: "+ language1 + " wrong as First language");
			Assert.assertTrue(false);
		}
		String lang1Url = header.getChangeLanguage().getAttribute("href");
		if(lang1Url.equalsIgnoreCase(givenLang1Url)) {
			log.info("Utility Banner: "+ lang1Url + "is the First language. URL is fine");
			Assert.assertTrue(true);
		}else {
			log.info("Utility Banner: "+ lang1Url + "wrong as the First language URL.");
			Assert.assertTrue(false);
		}
		
		try {
			header.getChangeLanguage().click();
		} catch (ElementClickInterceptedException e) {
			closePopup();
		}
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		
		String givenLang2 = "English";
		String givenLang2Url = "https://neutrogena.com/";
		String language2 = header.getChangeLanguage().getText();
		if(language2.equalsIgnoreCase(givenLang2)) {
			log.info("Utility Banner: "+ language2 + " is the Second language. All fine.");
			Assert.assertTrue(true);
		}else {
			log.info("Utility Banner: "+ language2 + " wrong as Second language.");
			Assert.assertTrue(false);
		}
		String lang2Url = header.getChangeLanguage().getAttribute("href");
		if(lang2Url.equalsIgnoreCase(givenLang2Url)) {
			log.info("Utility Banner: "+ lang2Url + "is the Second language. URL is fine.");
			Assert.assertTrue(true);
		}else {
			log.info("Utility Banner: "+ lang2Url + "wrong as Second language URL.");
			Assert.assertTrue(false);
		}

		

	}
	
	
}
