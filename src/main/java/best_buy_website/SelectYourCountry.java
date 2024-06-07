package best_buy_website;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class SelectYourCountry extends BaseClass {

	@FindBy(xpath = "//h4[text() = 'Canada']")
	List<WebElement> canada;
	
	@FindBy(xpath = "//h4[text() = 'United States']")
	List<WebElement> unitedStates;

	@FindBy(css = "div.language-menu li:nth-child(1)")
	WebElement english;
	
	@FindBy(css = "div.language-menu li:nth-child(2)")
	WebElement francais;
	
	@FindBy(css = "div.language-menu li:nth-child(3)")
	WebElement esponal;
	
	@FindBy(xpath = "//div[@class = 'heading']")
	List<WebElement> Head1;
	
	@FindBy(xpath = "//div[@class = 'heading']/following-sibling::h1")
	List<WebElement> Head2;
		
	@FindBy(xpath = "//h3[@class = 'international-header']")
	List<WebElement> head3;
	
	@FindBy(xpath = "//p[@class ='info']")
	List<WebElement> info;

	@FindBy(xpath = "//a[@class='more-details']")
	List<WebElement> SeeMoreDetails;
	
	public SelectYourCountry(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//select US
	public USHomepage getUnitedStates() {
		unitedStates.get(0).click();
		return new USHomepage(driver);
	}

	public WebElement getEnglish() {
		return english;
	}
	public WebElement getFrancais() {
		return francais;
	}
	public WebElement getEsponal() {
		return esponal;
	}
	public List<WebElement> getHead1() {
		return Head1;
	}
	public List<WebElement> getHead2() {
		return Head2;
	}
	public List<WebElement> getHead3() {
		return head3;
	}
	public List<WebElement> getInfo() {
		return info;
	}
	public List<WebElement> getSeeMoreDetails() {
		return SeeMoreDetails;
	}
	
	
	
	
	
	
	
	
}
