package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Header {

	private WebDriver driver;
	
	public Header(WebDriver driver) {
		this.driver = driver;
	}
	
	By logoBy = By.xpath("//div[@class='primary-logo']");
	By logoImageBy = By.xpath("./a/*[local-name()='svg']");
	By logoLinkBy = By.xpath("./a[@href='https://www.neutrogena.com/']");
	By logoTooltipBy = By.xpath("./a[contains(@title,'Neutrogena')]");
	
	By searchInputBy = By.xpath("//form[@name='simpleSearch']//input[preceding-sibling::label[contains(text(),'Search')]][1]");
	By searchButtonBy = By.xpath("//form[@name='simpleSearch']//button[preceding-sibling::label[contains(text(),'Search')]]");

	By userInfoBy = By.xpath("//div[@class='user-info'][parent::div[contains(@class,'header-guts-wrapper')]]");
	
	By myAccountDropdownItemsBy = By.xpath("//div[@class='user-info active']/div[@class='user-panel']/div[@class='user-links']/a");
	By myAccountDropdownTitleBy = By.xpath("//div[@class='user-info active']/div[@class='user-panel']/h3");

	
	public WebElement getLogo(){
		return driver.findElement(logoBy);
	}
	
	public WebElement getLogoImage(){
		return getLogo().findElement(logoImageBy);
	}
	
	public WebElement getLogoLink(){
		return getLogo().findElement(logoLinkBy);
	}
	
	public WebElement getLogoTooltip(){
		return getLogo().findElement(logoTooltipBy);
	}
	
	public WebElement getSearchInput(){
		return driver.findElement(searchInputBy);
	}
	
	public WebElement getSearchButton(){
		return driver.findElement(searchButtonBy);
	}	
	
	public WebElement getUserInfo(){
		return driver.findElement(userInfoBy);
	}	
	
	public By getMyAccountDropdownTitleBy(){
		return myAccountDropdownTitleBy;
	}
	
	public WebElement getMyAccountDropdownTitle(){
		return driver.findElement(myAccountDropdownTitleBy);
	}
	
	public List<WebElement> getMyAccountDropdownItems(){
		return driver.findElements(myAccountDropdownItemsBy);
	}
}
