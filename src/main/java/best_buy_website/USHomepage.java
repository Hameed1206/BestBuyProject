package best_buy_website;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class USHomepage extends BaseClass {
//Hameed@gm.co
	
	@FindBy (id = "survey_invite_no")
	WebElement survey;
	
	@FindBy(id = "account-menu-account-button")
	WebElement accountButton;
	
	@FindBy (xpath = "//div[@class='bottom-nav-left-wrapper']//li")
	List<WebElement> TopSecondLineLinks;
	
	@FindBy (css = "div.cart-icon")
	WebElement cartLink;
	
	@FindBy (xpath = "//div[contains(@class,'justify-content-start')]/a")
	List<WebElement> footerlinks;
	
	@FindBy (className= "search-input")
	WebElement searchBox;
	
	@FindBy (className= "header-search-button") 
	WebElement searchButton;
	
	@FindBy (id = "header-clear-search-icon")
	WebElement clearSearch;
	
	@FindBy (xpath = "//button[text() = 'Menu']")
	WebElement menuButtton;

	public USHomepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public AccountPage getAccountButton() {
		accountButton.click();
		return new AccountPage(driver);
	}

	// In menu navigate to all link and get title
	public USHomepage getTopSecondLineLinks() throws IOException {
		for (int i = 0; i < TopSecondLineLinks.size(); i++) {
			TopSecondLineLinks.get(i).click();
			String title = driver.getTitle();
			System.out.println(i+" "+title);
		}
		System.out.println("");
		return this;
	}

	public WebElement getCartLink() {
		return cartLink;
	}

	//In end navigate to all footer link 
	public List<WebElement> getFooterlinks() throws IOException {
		System.out.println("=====Footer Links=====");
		for (int i = 0; i < footerlinks.size(); i++) {
			footerlinks.get(i).click();
			String title = driver.getTitle();
			System.out.println(i+" "+title);
			String currentUrl = driver.getCurrentUrl();
			if (currentUrl.contains("landing")) {
				driver.navigate().back();
			}
		}
		return footerlinks;
	}

	public USHomepage getSearchBox(String product) {
		searchBox.sendKeys(product);
		return this;
	}
 
	public USHomepage getNewSearchBox(String product) {
		clearSearch.click();
		getSearchBox(product);
		return this;
	}
	public ProductListPage getSearchButton() {
		searchButton.click();
		return new ProductListPage(driver);
	}
	
	public MenuPage getMenuButtton() {
		menuButtton.click();
		return new MenuPage(driver);
	}

	
	
}
