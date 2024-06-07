package best_buy_website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class AccountPage extends BaseClass{

	@FindBy(xpath = "//a[text()='Create Account']")
	WebElement createAccount;
	
	@FindBy(xpath = "//a[text() = 'Sign In']")
	WebElement signIn;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    //click create account button
	public CreateAccountPage getAccountButton() {
		createAccount.click();
		return new CreateAccountPage(driver);
	}
	//click sign in button
	public SignInPage getSignInButton() {
		signIn.click();
		return new SignInPage(driver);
	}
}
