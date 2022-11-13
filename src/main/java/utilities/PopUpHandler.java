package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopUpHandler {
 
	private WebDriver driver;
	
	public PopUpHandler(WebDriver driver) {
		this.driver = driver;
	}
	
	//By popupShadowHostBy = By.xpath("//div[@role='dialog'][3]/div[contains(@id,'yie-inner-overlay')]/div[contains(@id,'yie-overlay-wrapper')]/yld-tag-host-campaign");
	//By popupShadowHostBy = By.xpath("//div[@id='yie-overlay-wrapper-ad57273e-8fd0-5f0a-8b7d-8c013363ff68']/yld-tag-host-campaign");
	By popupShadowHostBy = By.xpath("(//div[contains(@id,'yie-overlay-wrapper')]/yld-tag-host-campaign)[1]");

	public WebElement getPopupShadowHost(){
		return driver.findElement(popupShadowHostBy);
	}
	
	public boolean isShadowHostVisible() {
		
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    boolean isElementVisible = true;
	    try {
			wait.until(ExpectedConditions.visibilityOf(getPopupShadowHost()));
			System.out.println("InterceptedElement visible!!");
	    } catch (Exception e) {
	    	isElementVisible = false;
	    	System.out.println("No InterceptedElement present");
		}	    
	    return isElementVisible;
	}
	
	//By interceptedElementBy = By.xpath("//div[@id='yie-backdrop-ad57273e-8fd0-5f0a-8b7d-8c013363ff68']/parent::div");
	By interceptedElementBy = By.xpath("(//div[contains(@id,'yie-backdrop')]/parent::div)[1]");
	public WebElement getInterceptedElement(){
		return driver.findElement(interceptedElementBy);
	}
	
	boolean isInercertedElementVisible() {
		
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    boolean isElementVisible = true;
	    try {
			wait.until(ExpectedConditions.visibilityOf(getInterceptedElement()));
			System.out.println("InterceptedElement visible!!");
	    } catch (Exception e) {
	    	isElementVisible = false;
	    	System.out.println("No InterceptedElement present");
		}	    
	    return isElementVisible;
	}

	public boolean closePopup() {

		// Shadow root closed
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(getPopupShadowHost()).build().perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.ENTER).perform();
		} catch (Exception e) {
			return false;
		}
	    driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	    return true;
	}
	//.......................................//
	
	public WebElement getCampaignPopup(String campaignId){
		return driver.findElement(By.xpath("//yld-tag-host-campaign[@componentuuid='"+campaignId+"']"));
	}
	
	public void closeCampaignPopupIfDisplayed(String campaignId) {
		try {
			getCampaignPopup(campaignId).isDisplayed();
			closeCampaignPopup(campaignId);
		} catch (Exception e) {System.out.println("No Campaign Popup present");	}
	}
	
	public boolean closeCampaignPopup(String campaignId) {
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(getCampaignPopup(campaignId)).build().perform();
			builder.sendKeys(Keys.TAB).perform();
			builder.sendKeys(Keys.ENTER).perform();
		} catch (Exception e) {
			return false;
		}
	    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
	    return true;
	}

	
	
}
