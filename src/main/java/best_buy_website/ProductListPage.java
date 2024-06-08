package best_buy_website;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class ProductListPage extends BaseClass {

	@FindBy (xpath = "//ol[@class='sku-item-list']//div[@class='shop-sku-list-item']//div[@class='column-right']//button[text() = 'Add to Cart']/ancestor::div[@class='column-right']/preceding-sibling::div[@class='column-middle']//h4//a")
	List<WebElement> productTitle;
	
	@FindBy (xpath = "(//div[@class = 'column-middle']/h4/a)")
	List<WebElement> productList;
	
	@FindBy (xpath = "//ol[@class='sku-item-list']//div[@class='shop-sku-list-item']//div[@class='column-right']//button[text() = 'Add to Cart']")
	List<WebElement> addProduct;
	
	@FindBy (xpath = "//button[contains(@class,'continue-shopping')]")
	WebElement ContinueShopping;
	
	@FindBy (className = "go-to-cart-button")
	WebElement goToCartButton;
	
	@FindBy (css = "span.item-count")
	WebElement itemCount;
	
	public ProductListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//apply filter for products
	public ProductListPage getFilter(int a , int b) throws InterruptedException {
		System.out.println("Item Count before filter "+itemCount.getText());
		WebElement filterName = driver.findElement(By.xpath("//div[@class='bordered-section']/div[2]//section["+a+"]//strong"));
		String filterN = filterName.getText();
		System.out.println("Applied filter is "+filterN);
		WebElement element = driver.findElement(By.xpath("//div[@class='bordered-section']/div[2]//section["+a+"]//li["+b+"]/a"));
		element.click();
		Thread.sleep(1000);
		System.out.println("Item Count After filter "+itemCount.getText());
		return this;
	}
	
	//get first product title
	public String getProductTitle() {
		String productName = productTitle.get(0).getText();
		System.out.println("Selected "+productName);
		return productName;
	}
	//select first product having add to cart option
	public ProductPage clickProductWithAddToCartOption() {
		productTitle.get(0).click();
		return new ProductPage(driver);
	}
	//select first product
	public ProductPage getProductList() {
		productList.get(0).click();
		return new ProductPage(driver);
	}
	//add product to cart
	public ProductListPage getAddProductTocart() {
		addProduct.get(0).click();
		return this;
	}
	public USHomepage getContinueShopping() {
		ContinueShopping.click();
		return new USHomepage(driver);
	}
	public AddToCartPage getGoToCartButton() {
		explicitWait(goToCartButton);
		goToCartButton.click();
		return new AddToCartPage(driver);
	}
}
