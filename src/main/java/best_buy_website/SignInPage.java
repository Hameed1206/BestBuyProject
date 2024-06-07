package best_buy_website;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class SignInPage extends BaseClass {

	@FindBy (id = "fld-e")
	WebElement email;
	
	@FindBy (id = "fld-p1")
	WebElement passWord;
	
	@FindBy (css = "button[type='submit']")
	WebElement signInButton;
	
	@FindBy (xpath = "//div[contains(@class , 'c-alert-content')]")
	WebElement invalidError;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//try to signin if not signed in , get error
	//Hameed@gm.co
	public SignInPage getEmail(String user) {
		email.sendKeys(user);
		return this;
	}

	public SignInPage getPass(String pass) {
		passWord.sendKeys(pass);
		return this;
	}

	public SignInPage getSignInButton() {
		signInButton.click();
		return this;
	}

	public String getInvalidError() {
		String text = invalidError.getText();
		System.out.println(text);
		return text;
	}
}
