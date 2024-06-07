package best_buy_website;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class AddToCartPage extends BaseClass {

	List<String> itemList = new ArrayList();
		
	@FindBy (xpath = "//button[text() = 'Checkout']")
	WebElement checkOutButton;

	@FindBy (xpath = "//button[contains(@class,'c-modal-close-icon')]")
	WebElement popUpCloseButton;
	
	@FindBy (css = "ul.item-list div.item-title a")
	WebElement verifyTitle;
	
	@FindBy (css = "ul.item-list div.item-title a")
	List<WebElement> verifyMultipleTitle;
	
	public AddToCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//get product title
	public String getVerifyTitle() {
		String productNameinCart = verifyTitle.getText();
		System.out.println("product Name in Cart "+productNameinCart);
		return productNameinCart;
	}
	//click checkout button
	public CheckOutSignInPage getCheckOutButton() {
		checkOutButton.click();
		return new CheckOutSignInPage(driver);
	}
	//get all products title in cart and adding it to a list
	public List<String> getVerifyMultipleTitle() throws InterruptedException {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		Thread.sleep(3000);
		for (WebElement webElement : verifyMultipleTitle) {
			String items = webElement.getText();
			itemList.add(items);
		}
		for (String string : itemList) {
			System.out.println(string);
		}
		return itemList;
	}
}
