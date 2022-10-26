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
	
	@Test(priority=1)
	public void registerContent() {
		LinkedList<String> missingContents = new LinkedList<String>();
		String fn = "FIRST NAME";
		try {
			WebElement fnwe = register.getFirstNameLabel();
			if(!fnwe.getText().equalsIgnoreCase(fn))
				missingContents.add(fn);
		} catch (Exception e) {
			missingContents.add(fn);
		}
		String ln = "LAST NAME";
		try {
			WebElement lnwe = register.getLastNameLabel();
			scrollToWebElement(lnwe);
			if(!lnwe.getText().equalsIgnoreCase(ln))
				missingContents.add(ln);
		} catch (Exception e) {
			missingContents.add(ln);
		}
		String mob = "MOBILE";
		try {
			WebElement mwe = register.getMobileLabel();
			scrollToWebElement(mwe);
			if(!mwe.getText().equalsIgnoreCase(mob))
				missingContents.add(mob);
		} catch (Exception e) {
			missingContents.add(mob);
		}
		String dob = "DATE OF BIRTH";
		try {
			WebElement dwe = register.getDobLabel();
			scrollToWebElement(dwe);
			if(!dwe.getText().equalsIgnoreCase(dob))
				missingContents.add(dob);
		} catch (Exception e) {
			missingContents.add(dob);
		}
		String em = "EMAIL";
		try {
			WebElement ewe = register.getEmailLabel();
			scrollToWebElement(ewe);
			if(!ewe.getText().equalsIgnoreCase(em))
				missingContents.add(em);
		} catch (Exception e) {
			missingContents.add(em);
		}
		String pass = "PASSWORD";
		try {
			WebElement pwe = register.getPasswordLabel();
			scrollToWebElement(pwe);
			if(!pwe.getText().equalsIgnoreCase(pass))
				missingContents.add(pass);
		} catch (Exception e) {
			missingContents.add(pass);
		}
		String cpass = "CONFIRM PASSWORD";
		try {
			WebElement cpwe = register.getConfirmPasswordLabel();
			scrollToWebElement(cpwe);
			if(!cpwe.getText().equalsIgnoreCase(cpass))
				missingContents.add(cpass);
		} catch (Exception e) {
			missingContents.add(cpass);
		}
		String addtol = "PLEASE ADD ME TO NEUTROGENA'S EMAIL LIST.";
		try {
			WebElement awe = register.getAddtoemaillistLabel();
			scrollToWebElement(awe);
			if(!awe.getText().equalsIgnoreCase(addtol))
				missingContents.add(addtol);
		} catch (Exception e) {
			missingContents.add(addtol);
		}
		
		int x = 0;
		if((x = missingContents.size()) == 0) {
			log.info("Register: "+ "All Input label present");
			Assert.assertEquals(x, 0, "Register: "+ "All Input label present");
		}else {
			log.info("Register: "+ x +" Input labels missing " + missingContents.toString());
			Assert.assertEquals(x, 0, "Register: "+ x +" Input labels missing " + missingContents.toString());
		}
		
	}
	
	@Test(priority=2)
	public void requiredField() {
		LinkedList<String> missingRequiredFields = new LinkedList<String>();
		String fn = "FIRST NAME";
		try {
			WebElement fnwe = register.getFirstNameLabel();
			scrollToWebElement(fnwe);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
			if(!register.isRequired(fnwe))
				missingRequiredFields.add(fn);
		} catch (Exception e) {
			missingRequiredFields.add(fn);
		}
		String ln = "LAST NAME";
		try {
			WebElement lnwe = register.getLastNameLabel();
			scrollToWebElement(lnwe);
			if(!register.isRequired(lnwe))
				missingRequiredFields.add(ln);
		} catch (Exception e) {
			missingRequiredFields.add(ln);
		}
		String mob = "MOBILE";
		try {
			WebElement mwe = register.getMobileLabel();
			scrollToWebElement(mwe);
			if(!register.isRequired(mwe))
				missingRequiredFields.add(mob);
		} catch (Exception e) {
			missingRequiredFields.add(mob);
		}
		String dob = "DATE OF BIRTH";
		try {
			WebElement dwe = register.getDobLabel();
			scrollToWebElement(dwe);
			if(!register.isRequired(dwe))
				missingRequiredFields.add(dob);
		} catch (Exception e) {
			missingRequiredFields.add(dob);
		}
		String em = "EMAIL";
		try {
			WebElement ewe = register.getEmailLabel();
			scrollToWebElement(ewe);
			if(!register.isRequired(ewe))
				missingRequiredFields.add(em);
		} catch (Exception e) {
			missingRequiredFields.add(em);
		}
		String pass = "PASSWORD";
		try {
			WebElement pwe = register.getPasswordLabel();
			scrollToWebElement(pwe);
			if(!register.isRequired(pwe))
				missingRequiredFields.add(pass);
		} catch (Exception e) {
			missingRequiredFields.add(pass);
		}
		String cpass = "CONFIRM PASSWORD";
		try {
			WebElement cpwe = register.getConfirmPasswordLabel();
			scrollToWebElement(cpwe);
			if(!register.isRequired(cpwe))
				missingRequiredFields.add(cpass);
		} catch (Exception e) {
			missingRequiredFields.add(cpass);
		}
		
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
