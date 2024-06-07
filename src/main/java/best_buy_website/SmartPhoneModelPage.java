package best_buy_website;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class SmartPhoneModelPage extends BaseClass {

	@FindBy (css = "div.flex-grid a")
	List<WebElement> selectPhone;
	
	public SmartPhoneModelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//select mobile model and navigate to product list page
	public ProductListPage getSelectPhone() {
		selectPhone.get(7).click();
		return new ProductListPage(driver);
	}
}

