package best_buy_website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class CreateAccountPage extends BaseClass{

	@FindBy(id = "firstName")
	WebElement fName;
	
	@FindBy(id = "lastName")
	WebElement lName;
	
	@FindBy(id = "email")
	WebElement email;
	
	@FindBy(xpath = "(//input[@type='password'])[1]")
	WebElement pass;
	
	@FindBy(id = "reenterPassword")
	WebElement cPass;
	
	@FindBy(id = "phone")
	WebElement phone;
	
	@FindBy(id ="is-recovery-phone")
	WebElement actRecoveryCheckBox;
	
	@FindBy(xpath = "//button[text()='Create an Account']")
	WebElement createActButton;
	
	@FindBy (xpath = "//div[contains(@class,'c-alert-content')]//div")
	WebElement errorMsg;
	
	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// Create an account using required details
	public CreateAccountPage getfName(String name) {
		fName.sendKeys(name);
		return this;
	}

	public CreateAccountPage getlName(String name) {
		lName.sendKeys(name);
		return this;
	}

	public CreateAccountPage getEmail(String emailID) {
		email.sendKeys(emailID);
		return this;
	}

	public CreateAccountPage getPass(String passw) {
		pass.sendKeys(passw);
		return this;
	}

	public CreateAccountPage getcPass(String cPassw) {
		cPass.sendKeys(cPassw);
		return this;
	}

	public CreateAccountPage getPhone(String mobNo) {
		phone.sendKeys(mobNo);
		return this;
	}

	public CreateAccountPage getActRecoveryCheckBox() {
		actRecoveryCheckBox.click();
		return this;
	}

	public CreateAccountPage getCreateActButton() {
		createActButton.click();
		return this;
	}
	
	public String getErrorMsg() {
		//explicitWait(errorMsg);
		String currentUrl = driver.getCurrentUrl();
		String errText = errorMsg.getText();
		if (currentUrl.contains("recovery")) {
			String output = "Logged in successfully Current URL is "+currentUrl;
			System.out.println(output);
			return output;
		} else {
		System.out.println(errText);  
		return errText; }
		
		
	}

	
}
