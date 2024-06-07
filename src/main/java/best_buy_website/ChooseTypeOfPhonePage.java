package best_buy_website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class ChooseTypeOfPhonePage extends BaseClass {

	@FindBy (xpath = "(//a[text() = 'Smartphones'])[1]" )
	WebElement smartPhones;
	
	public ChooseTypeOfPhonePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//choosing smartphones options
	public SmartPhoneModelPage getSmartPhones() {
		smartPhones.click();
		return new SmartPhoneModelPage(driver);
	}
}
