package best_buy_website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class CheckOutSignInPage extends BaseClass {

	
	@FindBy (xpath = "//button[text() = 'Continue as Guest']")
	WebElement GuestSignIn;

	public CheckOutSignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// sign in as guest
	public CheckOutGettingOrderYourPage getGuestSignIn() {
		GuestSignIn.click();
		return new CheckOutGettingOrderYourPage(driver);
	}
	
	
}
