package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.reactivex.rxjava3.functions.Consumer;

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

	By userInfoBy = By.xpath("//div[contains(@class,'user-info')][parent::div[contains(@class,'header-guts-wrapper')]]");
	
	By myAccountDropdownItemsBy = By.xpath("//div[@class='user-info active']/div[@class='user-panel']/div[@class='user-links']/a");
	By myAccountDropdownTitleBy = By.xpath("//div[@class='user-info active']/div[@class='user-panel']/h3");

	String myAccountDropdownItem = "//div[@class='user-info active']/div[@class='user-panel']/div[@class='user-links']/a";

	
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
	
	public WebElement getMyAccountDropdownItem(String item){
		return driver.findElement(By.xpath(myAccountDropdownItem +"[contains(text(),'"+ item +"')]"));
	}
	
	By popupClosebuttonBy = By.xpath("//div[@role='dialog'][3]/div[contains(@id,'yie-inner-overlay')]/div/button");

	public WebElement getPopupClosebutton(){
		return driver.findElement(popupClosebuttonBy);
	}

	//By popupShadowHostBy = By.xpath("//div[@role='dialog'][3]/div[contains(@id,'yie-inner-overlay')]/div[contains(@id,'yie-overlay-wrapper')]/yld-tag-host-campaign");
	By popupShadowHostBy = By.xpath("//div[@id='yie-overlay-wrapper-ad57273e-8fd0-5f0a-8b7d-8c013363ff68']/yld-tag-host-campaign");
	//By popupShadowHostBy = By.xpath("//div[@id='yie-overlay-wrapper-2f8eb48f-7117-530f-8254-6b2c73b24378']/yld-tag-host-campaign");

	public WebElement getPopupShadowHost(){
		return driver.findElement(popupShadowHostBy);
	}
	
	By interceptedElementBy = By.xpath("//div[@id='yie-backdrop-ad57273e-8fd0-5f0a-8b7d-8c013363ff68']/parent::div");
	public WebElement getInterceptedElement(){
		return driver.findElement(interceptedElementBy);
	}
	
}
