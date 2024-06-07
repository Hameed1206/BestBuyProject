package helper_package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UtilityClass {

	public static WebDriver driver;
	
	public static void launch() {
		/*ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920,1080");*/
		driver = new ChromeDriver();
		driver.get("https://www.bestbuy.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));	
	}
	
	public static void launchEdge() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("https://www.bestbuy.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));	
	}
	public static void launchFireFox() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.get("https://www.bestbuy.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));	
	}
	
	public static void validateLink() throws IOException {
		String currentUrl = driver.getCurrentUrl();
		URL url = new URL(currentUrl);
		URLConnection openConnection = url.openConnection();
		HttpsURLConnection httpsConnection = (HttpsURLConnection)openConnection;
		int responseCode = httpsConnection.getResponseCode();
		System.out.println(responseCode);
		if (responseCode==200) {
			System.out.println(currentUrl+" is valid link");
		}else { System.out.println(currentUrl+" is broken link"); }
	}
	public static void explicitWait(WebElement element) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
         wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static void selectFromDropDown(WebElement element, String text) {
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}
	public static void moveToParticularElement(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).build().perform();
	}
	public static String captureScreenshot() throws IOException {
		TakesScreenshot tk = (TakesScreenshot)driver;
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		File file = tk.getScreenshotAs(OutputType.FILE);
		long timeMillis = System.currentTimeMillis();
		String title = driver.getTitle();
		String snapPath = "C:\\Users\\91936\\eclipse-workspace\\Best_Buy\\target\\"+timeMillis+".jpg";
		File path = new File(snapPath);
		Files.copy(file, path);
		return snapPath;
	}
	public static int findElementIndexByText(List<WebElement> elements, String text) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return i;
            }
        }   return -1;  // Return -1 if not found
    }
	public static XSSFSheet readValueFromExcel(String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\91936\\eclipse-workspace\\Best_Buy\\ExcelFile\\Best_Buy_App_Testing.xlsx");
		XSSFWorkbook wBook = new XSSFWorkbook(fis);
		XSSFSheet sheet = wBook.getSheet(sheetName);
		return sheet;
	}
	public static void closeBrowser() {
		driver.close();	
	}
	
	
	
}
