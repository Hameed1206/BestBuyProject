package best_buy_website;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class CheckOutGettingOrderYourPage extends BaseClass{

	@FindBy (name ="firstName")
	WebElement fName;

	@FindBy (id ="lastName")
	WebElement lName;
	
	@FindBy (id ="street")
	WebElement address;
	
	@FindBy (id ="city")
	WebElement cityName;
	
	@FindBy (id ="state")
	WebElement stateName;
	
	@FindBy (id ="zipcode")
	WebElement postCode;
	
	@FindBy (xpath ="//span[text()='Apply']")
	WebElement applyButton;
	
	@FindBy (id = "user.emailAddress")
	WebElement emailBox;
	
	@FindBy (id = "user.phone")
	WebElement phoneBox;

	@FindBy (xpath = "//span[contains(text() , 'Continue to')]")
	WebElement continuePaymentButton;
	
	@FindBy (css = "div.error-spacing")
	WebElement ErrorMsg;
	
	public CheckOutGettingOrderYourPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Give email and phone number to continue
	public CheckOutGettingOrderYourPage getEmailBox(String email) {
		emailBox.sendKeys(email);
		return this;
	}

	public CheckOutGettingOrderYourPage getPhoneBox(String phNo) {
		phoneBox.sendKeys(phNo);
		return this;
	}
 
	public String getContinuePaymentButtonError() {
		String errText = ErrorMsg.getText();
		return errText;
	}
	//Fill out details
	public CheckOutGettingOrderYourPage getFillDetails(String fiName, String laName, String addres, String city, String state, String pincode) {
		fName.sendKeys(fiName);
		lName.sendKeys(laName);
		address.sendKeys(addres);
		cityName.sendKeys(city);
		selectFromDropDown(stateName , state);
		postCode.sendKeys(pincode);
		applyButton.click();
		return this;
	}
	//verify URL and click continue
	public PlaceYourOrderPage getContinuePaymentButton() throws IOException, InterruptedException {
		continuePaymentButton.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL is "+currentUrl);
		if (currentUrl.contains("fulfillment")) {
			moveToParticularElement(ErrorMsg);
			System.out.println(ErrorMsg.getText());
			
		}
		
		return new PlaceYourOrderPage(driver);
	}
	
	
	
	
}
