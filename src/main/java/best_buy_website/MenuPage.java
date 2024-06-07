package best_buy_website;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class MenuPage extends BaseClass {

	@FindBy (xpath = "//ul[@class = 'hamburger-menu-flyout-list']//h2//following-sibling::li")
	List<WebElement> homeTheater;
	
	@FindBy (css = "li.liDropdownList button")
	List<WebElement> homeAudio;
	
	@FindBy (css = "li.liDropdownList a")
	List<WebElement> speakers;
	
	@FindBy (xpath = "//button[text() = 'Brands']")
	WebElement brandsButton;
	
	@FindBy (xpath = "//ul[contains(@class , 'hamburger-menu-flyout-sidecar-list')]//li")
	List<WebElement> brandName;

	public MenuPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//choose type of speaker
	public MenuPage getHometheater(String product) {
		int hTheater = findElementIndexByText(homeTheater, product );
		System.out.println("Index of TV & Home Theater "+hTheater);
		homeTheater.get(hTheater).click();
		return this;
	}
	
	public MenuPage getHomeAudio(String product) {
		int hAudio = findElementIndexByText(homeAudio, product);
		System.out.println("Index of Sound Bars & Home Audio "+hAudio);
		homeAudio.get(hAudio).click();
		return this;
	}
	
	public ChooseTypeOfSpeakerPage getSpeakers(String product) {
		int speaKers = findElementIndexByText(speakers, product );
		System.out.println("Index of Speakers is "+speaKers);
		speakers.get(speaKers).click();
		return new ChooseTypeOfSpeakerPage(driver);
	}
	
	//click samaung brand
	public ChooseTypeOfPhonePage getBrandsButton(String product) {
		brandsButton.click();
		int brand = findElementIndexByText(brandName, product);
		brandName.get(brand).click();
		return new ChooseTypeOfPhonePage(driver);
	}
}
