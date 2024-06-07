package best_buy_website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;
//6011000991300009
public class PlaceYourOrderPage extends BaseClass{

	@FindBy (id = "number")
	WebElement creditCardBox;
	
	@FindBy (id = "expMonth")
	WebElement expMonth;
	
	@FindBy (id = "expYear")
	WebElement expYear;
	
	@FindBy (id = "cvv")
	WebElement cvvBox;
	
	@FindBy (id ="first-name")
	WebElement fName;

	@FindBy (id ="last-name")
	WebElement lName;
	
	@FindBy (id ="address-input")
	WebElement address;
	
	@FindBy (id ="city")
	WebElement cityName;
	
	@FindBy (id ="state")
	WebElement stateName;
	
	@FindBy (id ="postalCode")
	WebElement postCode;
	
	@FindBy (xpath ="//span[text() = 'Place Your Order']")
	WebElement placeOrderButton;
	
	@FindBy (css = "div.error-spacing span")
	WebElement errorMsg;
	
	public PlaceYourOrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// fill credit card details and personal details
	public PlaceYourOrderPage getCreditCardBox(String cardNo) {
		creditCardBox.sendKeys(cardNo);
		return this;
	}
	public PlaceYourOrderPage getExpMonth(String mnth) {
		selectFromDropDown(expMonth, mnth);
		return this;
	}
	public PlaceYourOrderPage getExpYear(String year) {
		selectFromDropDown(expYear, year);
		return this;
	}
	public PlaceYourOrderPage getCvvBox(String cvv) {
		cvvBox.sendKeys(cvv);
		return this;
	}
	public PlaceYourOrderPage getfName(String fNam) {
		fName.sendKeys(fNam);
		return this;
	}
	public PlaceYourOrderPage getlName(String lNam) {
		lName.sendKeys(lNam);
		return this;
	}
	public PlaceYourOrderPage getAddress(String addres) {
		address.sendKeys(addres);
		return this;
	}
	public PlaceYourOrderPage getCityName(String city) {
		cityName.sendKeys(city);
		return this;
	}
	public PlaceYourOrderPage getStateName(String state) {
		selectFromDropDown(stateName, state);
		return this;
	}
	public PlaceYourOrderPage getPostCode(String pincode) {
		postCode.sendKeys(pincode);
		return this;
	}
	public PlaceYourOrderPage getPlaceOrderButton() {
		placeOrderButton.click();
		return this;
	}
	public WebElement getErrorMsg() {
		String error = errorMsg.getText();
		System.out.println("Error message displayed is "+error);
		return errorMsg;
	}
}
