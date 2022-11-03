package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
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
	
	By submitButtonBy = By.xpath("//form[@id='RegistrationForm']//button[@type='submit']");
	
	public WebElement getSubmitButton(){
		return driver.findElement(submitButtonBy);
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
	
}
