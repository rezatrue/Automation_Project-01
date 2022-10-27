package com.technogearup.E2EProject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.Footer;
import pageObjects.Register;
import resources.Base;
import resources.Utilities;

public class RegistrationPage  extends Base{
	public WebDriver driver;
	private Register register;
	private Utilities utilities;
	
	@BeforeClass
	public void launcBrowser() throws IOException {
		driver = initializerDriver();
		utilities = new Utilities();
		log.info("Driver is Initialized");
		driver.get(prop.getProperty("registration"));
		log.info("Registration Page: "+"Successfully loaded");
		driver.manage().window().maximize();
		register = new Register(driver);
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
		log.info("Page (Registration): "+"Closed");
	}
	
	private void scrollToWebElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	private String contentNotFound(String name, WebElement we) {
		try {
			scrollToWebElement(we);
			driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
			if(!we.getText().equalsIgnoreCase(name))
				return name;
		} catch (Exception e) {
			return name;
		}
		return null;
	}
	
	@Test(priority=1)
	public void registerContent() {
		LinkedList<String> missingContents = new LinkedList<String>();
		
		String missing = contentNotFound("FIRST NAME", register.getFirstNameLabel());
		if(missing != null) missingContents.add(missing);
		
		missing = contentNotFound("LAST NAME", register.getLastNameLabel());
		if(missing != null) missingContents.add(missing);
		
		missing = contentNotFound("MOBILE", register.getMobileLabel());
		if(missing != null) missingContents.add(missing);
		
		missing = contentNotFound("DATE OF BIRTH", register.getDobLabel());
		if(missing != null) missingContents.add(missing);
		
		missing = contentNotFound("EMAIL", register.getEmailLabel());
		if(missing != null) missingContents.add(missing);
		
		missing = contentNotFound("PASSWORD", register.getPasswordLabel());
		if(missing != null) missingContents.add(missing);
		
		missing = contentNotFound("CONFIRM PASSWORD", register.getConfirmPasswordLabel());
		if(missing != null) missingContents.add(missing);
		
		missing = contentNotFound("PLEASE ADD ME TO NEUTROGENA'S EMAIL LIST.", register.getAddtoemaillistLabel());
		if(missing != null) missingContents.add(missing);
		
		int x = 0;
		if((x = missingContents.size()) == 0) {
			log.info("Register: "+ "All Input label present");
			Assert.assertEquals(x, 0, "Register: "+ "All Input label present");
		}else {
			log.info("Register: "+ x +" Input labels missing " + missingContents.toString());
			Assert.assertEquals(x, 0, "Register: "+ x +" Input labels missing " + missingContents.toString());
		}
		
	}
	
	private String notMarkedAsRequired(String name, WebElement we) {
		try {
			scrollToWebElement(we);
			driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
			if(!register.isRequired(we))
				return name;
		} catch (Exception e) {
			return name;
		}
		return null;
	}
	
	@Test(priority=2)
	public void requiredFieldLabel() {
		LinkedList<String> missingRequiredFields = new LinkedList<String>();
		
		String missing = notMarkedAsRequired("FIRST NAME", register.getFirstNameLabel());
		if(missing != null) missingRequiredFields.add(missing);
		
		missing = notMarkedAsRequired("LAST NAME", register.getLastNameLabel());
		if(missing != null) missingRequiredFields.add(missing);
		
		missing = notMarkedAsRequired("MOBILE", register.getMobileLabel());
		if(missing != null) missingRequiredFields.add(missing);
		
		missing = notMarkedAsRequired("DATE OF BIRTH", register.getDobLabel());
		if(missing != null) missingRequiredFields.add(missing);
		
		missing = notMarkedAsRequired("EMAIL", register.getEmailLabel());
		if(missing != null) missingRequiredFields.add(missing);
		
		missing = notMarkedAsRequired("PASSWORD", register.getPasswordLabel());
		if(missing != null) missingRequiredFields.add(missing);
		
		missing = notMarkedAsRequired("CONFIRM PASSWORD", register.getConfirmPasswordLabel());
		if(missing != null) missingRequiredFields.add(missing);
				
		int x = 0;
		if((x = missingRequiredFields.size()) == 0) {
			log.info("Register: "+ "All Required labels are marked properly");
			Assert.assertEquals(x, 0, "Register: "+ "All Required labels are marked properly");
		}else {
			log.info("Register: "+ x +" labels are marked as required " + missingRequiredFields.toString());
			Assert.assertEquals(x, 0, "Register: "+ x +" labels are marked as required " + missingRequiredFields.toString());
		}
		
	}
	
	
	
}
