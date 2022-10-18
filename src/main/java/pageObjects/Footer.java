package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Footer {

	private WebDriver driver;
	
	public Footer(WebDriver driver) {
		this.driver = driver;
	}
	
	By footerDisclaimerBy = By.xpath("//div[@class='footer-disclaimer']/div/p");
	
	public WebElement getFooterDisclaimer(){
		return driver.findElement(footerDisclaimerBy);
	}
	
//	emailSignUp
//	
//	customerService
//	faqs
//	onlinePurchases
//	followUs
//	footerBadges
//	footerUtility
	
	
	By emailSignUpHeaderBy = By.xpath("//footer/div/div//h4[contains(text(),'Email Sign Up')]");
	
	public WebElement getEmailSignUpHeader() {
		return driver.findElement(emailSignUpHeaderBy);
	}
	
	By companyInfosBy = By.xpath("//footer//div[@class='link-section'][child::h4/button[contains(text(),'Company Info')]]/ul/li/a");
	
	public List<WebElement> getCompanyInfos() {
		return driver.findElements(companyInfosBy);
	}
	
}
