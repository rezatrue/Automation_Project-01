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
//	
//	faqs
//	
//	
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
	
	By customerServiceLinksBy = By.xpath("//footer//div[@class='link-section'][child::h4/button[contains(text(),'Customer Service')]]/ul/li/a");
	
	public List<WebElement> getCustomerServiceLinks() {
		return driver.findElements(customerServiceLinksBy);
	}
	
	By onlinePurchasesLinksBy = By.xpath("//footer//div[@class='link-section'][child::h4[contains(text(),'Online Purchases')]]/ul/li/a");
	
	public List<WebElement> getOnlinePurchasesLinks() {
		return driver.findElements(onlinePurchasesLinksBy);
	}

	By followUsLinksBy = By.xpath("//div[contains(@class,'link-section')][child::h4[contains(text(),'Follow Us')]]/ul/li/a");
	
	public List<WebElement> getFollowUsLinks() {
		return driver.findElements(followUsLinksBy);
	}
	
	By socialImageBy = By.xpath("./*[local-name()='svg']/*[local-name()='use']");
	//By socialImageBy = By.cssSelector("svg > use"); // also work fine
	
	public String getsocialImage(WebElement we) {
		return we.findElement(socialImageBy).getAttribute("xlink:href");
	}
	
	By footerUtilityLinksBy = By.xpath("//div[@class='footer-utility']//ul/li/a");
	
	public List<WebElement> getFooterUtilityLinks() {
		return driver.findElements(footerUtilityLinksBy);
	}
	
}
