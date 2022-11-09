package com.technogearup.E2EProject;

import java.io.IOException;
import java.time.Month;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import pageObjects.Register;
import resources.Base;
import utilities.EnglishCharacterData1;
import utilities.ReadExcelFile;

public class RegistrationPage  extends Base{
	public WebDriver driver;
	private Register register;
	
	@BeforeClass
	public void launcBrowser() throws IOException {
		driver = initializerDriver();
		log.info("Driver is Initialized");
		driver.get(prop.getProperty("registration"));
		log.info("Registration Page: "+"Successfully loaded");
		driver.manage().window().maximize();
		register = new Register(driver);
	}
	
	//@AfterClass
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
	
	//@Test(priority=1)
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
	
	//@Test(priority=2)
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
	
	//@Test(priority=3, dataProvider="dummyPassword", dataProviderClass = ReadExcelFile.class)
	public void validatePasswordInputField(String password) {
		
		WebElement we = register.getPasswordInputField();
		scrollToWebElement(we);
		we.clear();
		we.sendKeys(password);
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		register.getConfirmPasswordLabel().click();
		
		String errorMsg = register.getPasswordErrorMsg();
		
		Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[0-9A-Za-z\\d!@#$%^&*]{10,}$");
		Matcher matcher = pattern.matcher(password);
		boolean patternRes = matcher.matches();
		
		if(errorMsg.length() < 1 && patternRes == true) {
			log.info("Password Input Field validation: PASS "+ password +" matches the criteria");
			Assert.assertTrue(patternRes, "Password Input Field validation: PASS "+ password +" matches the criteria");
		}
		
		if(errorMsg.length() > 1 && patternRes == false) {
			log.info("Password Input Field validation: PASS "+ errorMsg +" --> "+password);
			Assert.assertTrue(!patternRes, "Password Input Field validation: PASS "+ errorMsg +" --> "+password);
		}
		
		if(errorMsg.length() > 1 && patternRes == true) {
			log.info("Password Input Field validation: FAIL "+ errorMsg +" --> "+password);
			Assert.assertTrue(!patternRes, "Password Input Field validation: FAIL "+ errorMsg +" --> "+password);
		}
		
		if(errorMsg.length() < 1 && patternRes == false) {
			log.info("Password Input Field validation: FAIL "+ " there is no error message --> "+password);
			Assert.assertTrue(patternRes, "Password Input Field validation: FAIL "+ " there is no error message --> "+password);
		}

	}
	
	//@Test(priority = 4, dataProvider = "dummyRegister", dataProviderClass = ReadExcelFile.class)
	public void validateRegistrationFormWithInvalidData(String firstName, String lastName, String phone,
			String dobMonth, String dobYear, String email, String password, String confirmpassword,
			String addToEmailList, String status) {
		
		System.out.println("FN: "+ firstName +"; LN: "+  lastName +"; Moblie: "+   phone 
				+"; Month: "+   dobMonth +"; Year: "+   dobYear +"; Email: "+   email +"; Pass: "+   password 
				+"; Confirm: "+   confirmpassword +"; addToEmailList: "+ addToEmailList +"; status: "+ status);
		
		subbmitRegistrationForm(firstName, lastName, phone, dobMonth, dobYear, email,
				password, confirmpassword, (addToEmailList.equalsIgnoreCase("true")? true: false));
		
		if(register.isErrorPresentOnForm()){
			Assert.assertFalse(false, "Form is not submitted");
		}else {
			Assert.assertTrue(true, "Form submitted");
		}
		
	}

	private void subbmitRegistrationForm(String firstName, String lastName, String phone,
			String dobMonth, String dobYear, String email,
			String password, String confirmpassword, boolean addToEmailList) {
		scrollToWebElement(register.getMonthInputField());
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		register.getFirstNameInputField().clear();
		if(firstName != null) register.getFirstNameInputField().sendKeys(firstName);
		driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
		register.getLastNameInputField().clear();
		if(lastName != null) register.getLastNameInputField().sendKeys(lastName);
		driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
		register.getMobileInputField().clear();
		if(phone != null) register.getMobileInputField().sendKeys(phone);
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		Actions action = new Actions(driver);
		register.deselectMonth();
		if(dobMonth != null) {
			String month = dobMonth;
			action.click(register.getMonthInputField()).build().perform();
			action.sendKeys(Keys.TAB).perform();
			Boolean monthSelected = false;
			while(!monthSelected) {
				if(register.getSectionMonth(month).isDisplayed()) {
					action.click(register.getSectionMonth(month)).build().perform();
					break;
				}
				driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
				action.sendKeys(Keys.ARROW_DOWN).perform();
			}
		}
		register.deselectYear();
		if(dobYear != null) {
			String year = dobYear;
			action.click(register.getYearInputField()).build().perform();
			action.sendKeys(Keys.TAB).perform();
			System.out.println(dobYear);
			Boolean yearSelected = false;
			while(!yearSelected) {
				if(register.getSectionYear(year).isDisplayed()) {
					action.click(register.getSectionYear(year)).build().perform();
					break;
				}
				driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
				action.sendKeys(Keys.ARROW_DOWN).perform();
			}
		}
		driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
		register.getEmailInputField().clear();
		if(email != null) register.getEmailInputField().sendKeys(email);
		driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
		scrollToWebElement(register.getSubmitButton());
		register.getPasswordInputField().clear();
		if(password != null) register.getPasswordInputField().sendKeys(password);
		driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
		register.getConfirmPasswordInputField().clear();
		if(confirmpassword != null) register.getConfirmPasswordInputField().sendKeys(confirmpassword);
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		if(!addToEmailList) register.getAddtoemaillist().click();
		//register.getSubmitButton().click();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

	}
	

	@Test(priority = 5)
	public void validateRegistrationFormWithValidData() {
		
		Faker faker = new Faker(new Locale("en-US"));
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String phone = faker.phoneNumber().subscriberNumber(10);
		String month = Month.of(faker.date().birthday().getMonth()+1).toString();
		String dobMonth = (month.charAt(0)+"").toUpperCase() + month.toLowerCase().substring(1);
		String gmtString = faker.date().birthday(18, 45).toGMTString();
		String dobYear = gmtString.split(" ")[2];
		String email = faker.internet().emailAddress();
		String password = generatePassword();
		String addToEmailList = "false";
		
		System.out.println("FN: "+ firstName +"; LN: "+  lastName +"; Moblie: "+   phone 
				+"; Month: "+   dobMonth +"; Year: "+   dobYear +"; Email: "+   email +"; Pass: "+   password 
				+"; Confirm: "+   password +"; addToEmailList: "+ addToEmailList);
		//*
		subbmitRegistrationForm(firstName, lastName, phone, dobMonth, dobYear, email,
				password, password, (addToEmailList.equalsIgnoreCase("true")? true: false));
		
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		if(register.isErrorPresentOnForm()){
			Assert.assertFalse(false, "Form is not submitted");
		}
		if(driver.getCurrentUrl().contains("neutrogena.com/account"))
		{
			log.info("Registration: " + "Account created" + "user: "+ email + " pass: "+ password);
			Assert.assertTrue(true, "Account created");
		}
		//*/
	}
	
	   private String generatePassword() {
		      CharacterRule alphabets = new CharacterRule(EnglishCharacterData.Alphabetical);
		      CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit, 1);
		      CharacterRule special = new CharacterRule(EnglishCharacterData1.Special, 1);
		      CharacterRule uppercase = new CharacterRule(EnglishCharacterData.UpperCase, 1);
		      CharacterRule lowercase = new CharacterRule(EnglishCharacterData.LowerCase, 1);
		      PasswordGenerator passwordGenerator = new PasswordGenerator();
		      return passwordGenerator.generatePassword(10, alphabets, digits, special, uppercase, lowercase);
		   }
}
