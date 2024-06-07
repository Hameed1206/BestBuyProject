package best_buy_website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class ProductPage extends BaseClass {

	@FindBy (xpath = "//div[@class='sku-title']")
	WebElement productTitle;
	
	@FindBy(xpath = "//div[@class='sku-title']//following-sibling::div//div[contains(@class,'sku')]//span[contains(@class,'product-data-value')]")
	WebElement skuValue;
	
	@FindBy(xpath = "//span[text()='Features']")
	WebElement moveToLocation;
	
	@FindBy (className = "go-to-cart-button")
	WebElement goToCartButton;
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//get product title
	public String getProductTitle() {
		String productTit = productTitle.getText();
		System.out.println("Product Title is "+productTit);
		return productTit;
	}
	//using SKU value get add to cart button
	public ProductPage getAddToCartButton() {
		String skuVal = skuValue.getText();
		System.out.println("Product sku value is "+skuVal);
		//javscriptDown(moveToLocation);
		moveToParticularElement(moveToLocation);
		
		WebElement addTocart = driver.findElement(By.xpath(("(//button[@data-sku-id='"+skuVal+"'])[1]")));
		addTocart.click();
		return this;
	}
	public AddToCartPage getGoToCartButton() {
		goToCartButton.click();
		return new AddToCartPage(driver);
	}
	

}
