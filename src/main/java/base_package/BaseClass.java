package base_package;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import helper_package.UtilityClass;

public class BaseClass extends UtilityClass{

	@BeforeMethod
	public static void startUp() throws IOException {
		launch();
	}
	@AfterMethod
	public static void tearDown() throws IOException {
		closeBrowser();
	}
}
