package pageObjects;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Register {
	private WebDriver driver;
	
	public Register(WebDriver driver) {
		this.driver = driver;
	}

	
	By firstNameLabelBy = By.xpath("//form[@id='RegistrationForm']//label[contains(@for,'firstname')]");
	

	public WebElement getFirstNameLabel(){
		return driver.findElement(firstNameLabelBy);
	}
	
	By lastNameLabelBy = By.xpath("//form[@id='RegistrationForm']//label[contains(@for,'lastname')]");
	
	public WebElement getLastNameLabel(){
		return driver.findElement(lastNameLabelBy);
	}
	
	By mobileLabelBy = By.xpath("//form[@id='RegistrationForm']//label[contains(@for,'phone')]");
	
	public WebElement getMobileLabel(){
		return driver.findElement(mobileLabelBy);
	}
	
	By dobLabelBy = By.xpath("//form[@id='RegistrationForm']//label[contains(@for,'dob_month')]");
	
	public WebElement getDobLabel(){
		return driver.findElement(dobLabelBy);
	}
	
	By emailLabelBy = By.xpath("//form[@id='RegistrationForm']//label[contains(@for,'customer_email')]");
	
	public WebElement getEmailLabel(){
		return driver.findElement(emailLabelBy);
	}

	By passwordLabelBy = By.xpath("//form[@id='RegistrationForm']//label[contains(@for,'login_password')]");
	
	public WebElement getPasswordLabel(){
		return driver.findElement(passwordLabelBy);
	}
	
	By confirmPasswordLabelBy = By.xpath("//form[@id='RegistrationForm']//label[contains(@for,'passwordconfirm')]");
	
	public WebElement getConfirmPasswordLabel(){
		return driver.findElement(confirmPasswordLabelBy);
	}

	By addtoemaillistLabelBy = By.xpath("//form[@id='RegistrationForm']//label[contains(@for,'addtoemaillist')]");
	
	public WebElement getAddtoemaillistLabel(){
		return driver.findElement(addtoemaillistLabelBy);
	}

	By isRequiredBy = By.xpath("./span[@class='required-indicator']");
	
	public boolean isRequired(WebElement element){
		
		try {
			return element.findElement(isRequiredBy).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}
	
	By legalInfoNoteBy = By.xpath("//form[@id='RegistrationForm']//p[@class='legal-info']");
	
	public WebElement getLegalInfoNote(){
		return driver.findElement(legalInfoNoteBy);
	}
	
	By passwordInputFieldBy = By.xpath("//input[@id='dwfrm_profile_login_password']");
	
	public WebElement getPasswordInputField(){
		return driver.findElement(passwordInputFieldBy);
	}

	By passwordErrorMsgBy = By.xpath("//input[@id='dwfrm_profile_login_password']/following-sibling::span[@class='error']");
	
	public String getPasswordErrorMsg(){
		try {
			return driver.findElement(passwordErrorMsgBy).getText();
		}catch(Exception e) {
			return "";
		}
	}
	
	By errorBy = By.xpath("//*[@class='error']");
	By errorMYBy = By.xpath("//*[@class='form-caption error']");
	
	public boolean isErrorPresentOnForm(){
		boolean error = false;
		try {
			error = driver.findElement(errorBy).isDisplayed();
		} catch (Exception e) {}
		if(!error){
			try {
				error = driver.findElement(errorMYBy).isDisplayed();
			} catch (Exception e) {}
		}
		return error;
	}
	
	By firstNameInputFieldBy = By.xpath("//input[@id='dwfrm_profile_customer_firstname']");
	
	public WebElement getFirstNameInputField(){
		return driver.findElement(firstNameInputFieldBy);
	}
	
	By lastNameInputFieldBy = By.xpath("//input[@id='dwfrm_profile_customer_lastname']");
	
	public WebElement getLastNameInputField(){
		return driver.findElement(lastNameInputFieldBy);
	}
	
	By mobileInputFieldBy = By.xpath("//input[@id='dwfrm_customeraddress_phone']");
	
	public WebElement getMobileInputField(){
		return driver.findElement(mobileInputFieldBy);
	}
	
	By monthInputFieldBy = By.xpath("//input[@id='dwfrm_profile_customer_dob_month']");
	
	public WebElement getMonthInputField(){
		return driver.findElement(monthInputFieldBy);
	}
	
	By yearInputFieldBy = By.xpath("//input[@id='dwfrm_profile_customer_dob_year']");
	
	public WebElement getYearInputField(){
		return driver.findElement(yearInputFieldBy);
	}
	
	String sectionMonth = "//section[contains(@id,'mat-data-list-dwfrm_profile_customer_dob_month')]";
	
	public WebElement getSectionMonth(String month){
		return driver.findElement(By.xpath(sectionMonth+"/div[contains(.,'"+ month +"')]")); 
	}
	
	String sectionYear = "//section[contains(@id,'mat-data-list-dwfrm_profile_customer_dob_year')]";
	
	public WebElement getSectionYear(String year){
		return driver.findElement(By.xpath(sectionYear+"/div[contains(.,'"+ year +"')]")); 
	}
	
	By emailInputFieldBy = By.xpath("//input[@id='dwfrm_profile_customer_email']");
	
	public WebElement getEmailInputField(){
		return driver.findElement(emailInputFieldBy);
	}
	
	By confirmPasswordInputFieldBy = By.xpath("//input[@id='dwfrm_profile_login_passwordconfirm']");
	
	public WebElement getConfirmPasswordInputField(){
		return driver.findElement(confirmPasswordInputFieldBy);
	}
	
	By addtoemaillistBy = By.xpath("//label[@for='dwfrm_profile_customer_addtoemaillist']");
	
	public WebElement getAddtoemaillist(){
		return driver.findElement(addtoemaillistBy);
	}

	//By submitButtonBy = By.xpath("//form[@id='RegistrationForm']//button[@type='submit']");	
	By submitButtonBy = By.xpath("//button[@name='dwfrm_profile_confirm']");
	
	public WebElement getSubmitButton(){
		return driver.findElement(submitButtonBy);
	}
	
	public void deselectMonth(){
		try {
		WebElement element = driver.findElement(By.cssSelector("#mat-data-list-dwfrm_profile_customer_dob_month > div:nth-child(1)"));
		((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('disabled')", element);
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		getMonthInputField().click();
		getSectionMonth("Month").click();
		}catch(Exception e) {System.out.println(e.getMessage());}
	}
	
	public void deselectYear(){
		try {
		WebElement element = driver.findElement(By.cssSelector("#mat-data-list-dwfrm_profile_customer_dob_year > div:nth-child(1)"));
		((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('disabled')", element);
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		getYearInputField().click();
		getSectionYear("Year").click();
		}catch(Exception e) {System.out.println(e.getMessage());}
	}
	
}
