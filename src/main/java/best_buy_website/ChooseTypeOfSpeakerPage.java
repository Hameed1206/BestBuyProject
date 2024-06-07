package best_buy_website;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseClass;

public class ChooseTypeOfSpeakerPage extends BaseClass{

	@FindBy (xpath = "//div//h2[text() = 'Shop speakers by type']//parent::div//following-sibling::div//div//a")
	List<WebElement> speakerType;
	
	public ChooseTypeOfSpeakerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// choose speaker type
	public ProductListPage getSpeakerType(String speaker) {
		int type = findElementIndexByText(speakerType, speaker);
		System.out.println("Index of Speaker type Floor speakers is "+type);
		speakerType.get(type).click();
		return new ProductListPage(driver);
	}
	
	
	
}
