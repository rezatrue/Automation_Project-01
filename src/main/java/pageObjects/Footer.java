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
	
	
	By emailSignUpHeaderBy = By.xpath("//div[contains(@class,'footer-item')]//h4[contains(text(),'Email Sign Up')]");
	
	public WebElement getEmailSignUpHeader() {
		return driver.findElement(emailSignUpHeaderBy);
	}
	
	By companyInfoBy = By.xpath("//div[contains(@class,'footer-item')]//h4/button[contains(text(),'Company Info')]");
	
	public WebElement getCompanyInfo() {
		return driver.findElement(companyInfoBy);
	}
	
	By customerServiceBy = By.xpath("//div[contains(@class,'footer-item')]//h4/button[contains(text(),'Customer Service')]");
	
	public WebElement getCustomerService() {
		return driver.findElement(customerServiceBy);
	}
	
	By onlinePurchasesBy = By.xpath("//div[@class='footer-subcol']/div/h4[contains(text(),'Online Purchases')]");
	
	public WebElement getOnlinePurchases() {
		return driver.findElement(onlinePurchasesBy);
	}
	
	By followUsBy = By.xpath("//div[@class='footer-subcol']/div/h4[contains(text(),'Follow Us')]");
	
	public WebElement getFollowUs() {
		return driver.findElement(followUsBy);
	}
	
	By footerBadgesBy = By.xpath("//div[@class='klarna-footer']");
	
	public WebElement getFooterBadges() {
		return driver.findElement(footerBadgesBy);
	}
	
	By footerUtilityBy = By.xpath("//div[@class='footer-utility']");
	
	public WebElement getFooterUtility() {
		return driver.findElement(footerUtilityBy);
	}
	
	
	By companyInfosBy = By.xpath("//footer//div[@class='link-section'][child::h4/button[contains(text(),'Company Info')]]/ul/li/a");
	
	public List<WebElement> getCompanyInfos() {
		return driver.findElements(companyInfosBy);
	}
	
}
