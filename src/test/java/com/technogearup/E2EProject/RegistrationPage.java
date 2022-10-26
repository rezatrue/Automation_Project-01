package com.technogearup.E2EProject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	
	@Test
	public void registerContent() {
		WebElement fnwe = register.getFirstNameLabel();
		log.info(fnwe.getText());
		log.info(register.isRequired(fnwe));
		WebElement lnwe = register.getLastNameLabel();
		scrollToWebElement(lnwe);
		log.info(lnwe.getText());
		log.info(register.isRequired(lnwe));
		WebElement mwe = register.getMobileLabel();
		scrollToWebElement(mwe);
		log.info(mwe.getText());
		log.info(register.isRequired(mwe));
		WebElement dwe = register.getDobLabel();
		scrollToWebElement(dwe);
		log.info(dwe.getText());
		log.info(register.isRequired(dwe));
		WebElement ewe = register.getEmailLabel();
		scrollToWebElement(ewe);
		log.info(ewe.getText());
		log.info(register.isRequired(ewe));
		WebElement pwe = register.getPasswordLabel();
		scrollToWebElement(pwe);
		log.info(pwe.getText());
		log.info(register.isRequired(pwe));
		WebElement cpwe = register.getConfirmPasswordLabel();
		scrollToWebElement(cpwe);
		log.info(cpwe.getText());
		log.info(register.isRequired(cpwe));
		WebElement awe = register.getAddtoemaillistLabel();
		scrollToWebElement(awe);
		log.info(awe.getText());
		log.info(register.isRequired(awe));
	}
	
}
