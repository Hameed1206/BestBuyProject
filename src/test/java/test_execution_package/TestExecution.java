package test_execution_package;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base_package.BaseClass;
import best_buy_website.AddToCartPage;
import best_buy_website.CheckOutGettingOrderYourPage;
import best_buy_website.CheckOutSignInPage;
import best_buy_website.CreateAccountPage;
import best_buy_website.ProductListPage;
import best_buy_website.ProductPage;
import best_buy_website.SelectYourCountry;
import best_buy_website.SignInPage;

public class TestExecution extends BaseClass{
	
	TestData datas;
	//All reporting variables made Instance
	ExtentSparkReporter crtreport;
	ExtentReports extent;
	ExtentTest snap;
	
	//DataProvider method having information about test datas in excel
	@DataProvider (name="SignUp function")
	public Object[][] signUpTestData() throws IOException {
		Object[][] datas = TestData.signUpTestDatas();
		return datas;
		
	}
	@DataProvider (name="SignIn function")
	public Object[][] signInTestData() throws IOException {
		Object[][] datas = TestData.testdatas();
		return datas;
	}
	@DataProvider (name="Delivery Details")
	public Object[][] deliveryDeatilsTestData() throws IOException {
		Object[][] datas = TestData.paymentPageDetails();
		return datas;
	}
	
	//Constructor to invoke reporting activities
	public TestExecution(String name) {
		long timeMillis = System.currentTimeMillis();
		String reportPath = "C:\\Users\\91936\\eclipse-workspace\\Best_Buy\\target\\"+timeMillis+".html";
		crtreport = new ExtentSparkReporter(reportPath);
		crtreport.config().setReportName(name);
		crtreport.config().setDocumentTitle("Web Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(crtreport);
	}
    //Test  Validating sign in function using different credentials
	// Data provider is used to access multiple values in Excel
	@Test(dataProvider = "SignUp function")
	public void ValidateCreateNewAccountFunction(String s1, String s2, String s3, String s4, String s5) throws IOException {
		SelectYourCountry createAccountPage = new SelectYourCountry(driver);
		TestExecution exe = new TestExecution("SignUp function Validation");
		CreateAccountPage accountPage = new CreateAccountPage(driver);
		snap = extent.createTest("ValidateCreateNewAccountFunction");
		try {
			createAccountPage.getUnitedStates().getAccountButton().getAccountButton().getfName(s1).getlName(s2).getEmail(s3).getPass(s4)
			.getcPass(s4).getPhone(s5).getActRecoveryCheckBox().getCreateActButton();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage() +" "+ e.getClass());
		}
		
		snap.addScreenCaptureFromPath(captureScreenshot(), "ValidateCreateNewAccountFunction");
		snap.log(Status.PASS, accountPage.getErrorMsg());
		extent.flush();
	}
	
     // Validating sign in function using different credentials based on Decision table technique
	// Data provider is used to access multiple values in Excel
	@Test(dataProvider = "SignIn function")
	public void ValidateSignInFunction(String s1, String s2, String s3) throws IOException {
		SelectYourCountry createAccountPage = new SelectYourCountry(driver);
		TestExecution exe = new TestExecution("SignIn function Validation");
		SignInPage in = new SignInPage(driver);
		snap = extent.createTest("ValidateSignInFunction");
		System.out.println(s1);
		try {
			createAccountPage.getUnitedStates().getAccountButton().getSignInButton()
			.getEmail(s2).getPass(s3).getSignInButton().getInvalidError();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage() +" "+ e.getClass());
		}
		snap.addScreenCaptureFromPath(captureScreenshot(), "ValidateSignInFunction");
		snap.log(Status.PASS, s1);
		extent.flush();
	}
	
	//Validation of menu items and footer links
	@Test(dependsOnMethods = "ValidateSignInFunction")
	public void ValidateAllMenuAndFooterLinks() throws IOException {
		SelectYourCountry createAccountPage = new SelectYourCountry(driver);
		TestExecution exe = new TestExecution("Menu and footer link validation");
		try {
			createAccountPage.getUnitedStates().getTopSecondLineLinks().getFooterlinks();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage() +" "+ e.getClass());
		}
		
	}
	
	//Validating the function of searching a item through search box and adding that into cart and verifying whether 
	//selected Item and Item added in cart is same and checking out to place order
	// Also we are asking report to track activities and prepare a report with screenshots
	@Parameters({"email","phoneNo"})
	@Test(dependsOnMethods = "ValidateAllMenuAndFooterLinks")
	public void ValidateSearchAndAddItemToCart(String mail, String mob) throws IOException, InterruptedException {
		SelectYourCountry createAccountPage = new SelectYourCountry(driver);
		datas = new TestData();
		ProductPage proPage = new ProductPage(driver);
		AddToCartPage atoCPage = new AddToCartPage(driver);
		CheckOutGettingOrderYourPage page = new CheckOutGettingOrderYourPage(driver);
		TestExecution exe = new TestExecution("Validate Search and add item to cart");
		
		snap = extent.createTest("ValidateSearchAndAddItemToCart");
		try {
			createAccountPage.getUnitedStates().getSearchBox(datas.getProduct1()).getSearchButton().getProductList();
			String productTitle = proPage.getProductTitle();
			proPage.getAddToCartButton().getGoToCartButton();
			String verifyTitle = atoCPage.getVerifyTitle();
			Assert.assertEquals(verifyTitle, productTitle);
			System.out.println("Expected product added in cart successfully");
			atoCPage.getCheckOutButton().getGuestSignIn().getEmailBox(mail).getPhoneBox(mob).getContinuePaymentButton();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage() +" "+ e.getClass());
		}
		// At this point we are getting error in application, so can't continue further
		// I have written the code logics till end.
	    //.getCreditCardBox().getExpMonth().getExpYear().getCvvBox().getfName().getlName().getAddress()
		//.getCityName().getStateName().getPostCode().getPlaceOrderButton().getErrorMsg();
		
		snap.addScreenCaptureFromPath(captureScreenshot(), "ValidateSearchAndAddItemToCart");
		snap.log(Status.PASS, page.getContinuePaymentButtonError());
		extent.flush();
	}
	
	// Validating function of adding a Item through menu and options there
	@Test(dependsOnMethods = "ValidateSearchAndAddItemToCart")
	public void validateAddingItemfromMenu() throws IOException {
		SelectYourCountry createAccountPage = new SelectYourCountry(driver);
		datas = new TestData();
		ProductPage itemPage = new ProductPage(driver);
		AddToCartPage atoCPage = new AddToCartPage(driver);
		TestExecution exe = new TestExecution("Validate adding item from menu");
		snap = extent.createTest("validateAddingItemfromMenu");
		try {
			createAccountPage.getUnitedStates().getMenuButtton().getHometheater(datas.getHomeTheater()).getHomeAudio(datas.getHomeAudio())
			.getSpeakers(datas.getSpeaker()).getSpeakerType(datas.getSpeakerType()).clickProductWithAddToCartOption();
			String productTitle = itemPage.getProductTitle();
			itemPage.getAddToCartButton().getGoToCartButton();
			String verifyTitle = atoCPage.getVerifyTitle();
			Assert.assertEquals(verifyTitle, productTitle);
			System.out.println("Expected product added in cart successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage() +" "+ e.getClass());
		}
		
		snap.addScreenCaptureFromPath(captureScreenshot(), "validateAddingItemfromMenu");
		snap.log(Status.PASS, "Item added in cart");
		extent.flush();
	}
	
	//Validating function of adding item through Brand option from menu
	@Test(dependsOnMethods = "validateAddingItemfromMenu")
	public void validateAddingItemfromMenuViaBrand() throws IOException {
		SelectYourCountry createAccountPage = new SelectYourCountry(driver);
		datas = new TestData();
		ProductPage itemPage = new ProductPage(driver);
		AddToCartPage atoCPage = new AddToCartPage(driver);
		TestExecution exe = new TestExecution("Validate adding item from menu via brand");
		snap = extent.createTest("validateAddingItemfromMenuViaBrand");
		createAccountPage.getUnitedStates().getMenuButtton().getBrandsButton(datas.getBrandName()).getSmartPhones().getSelectPhone().clickProductWithAddToCartOption();
		String productTitle = itemPage.getProductTitle();
		itemPage.getAddToCartButton().getGoToCartButton();
		String verifyTitle = atoCPage.getVerifyTitle();
		Assert.assertEquals(verifyTitle, productTitle);
		System.out.println("Expected product added in cart successfully");
		snap.addScreenCaptureFromPath(captureScreenshot(), "validateAddingItemfromMenuViaBrand");
		snap.log(Status.PASS, "Item added in cart");
		extent.flush();
	}
	//Validating the function of searching a item through search box and adding that into cart. here we're adding more than one product
	//verifying whether all selected Items and Items added in cart are same and checking out to place order
	@Test(dataProvider = "Delivery Details", dependsOnMethods = "validateAddingItemfromMenu")
	public void validateAddingMultipleItemsTocartAndCheckout
	(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10) throws InterruptedException, IOException {
		SelectYourCountry createAccountPage = new SelectYourCountry(driver);
		datas = new TestData();
		ProductListPage listPage = new ProductListPage(driver);
		AddToCartPage atoCPage = new AddToCartPage(driver);
		CheckOutGettingOrderYourPage page = new CheckOutGettingOrderYourPage(driver);
		TestExecution exe = new TestExecution("Validate adding multiple Items To cart and Checkout");
		snap = extent.createTest("validateAddingMultipleItemsTocartAndCheckout");
		//Apple airpods  mini fridge  microwave
		createAccountPage.getUnitedStates().getSearchBox(datas.getProduct1()).getSearchButton();
		String productOne = listPage.getProductTitle();
		listPage.getAddProductTocart().getContinueShopping().getNewSearchBox(datas.getProduct2()).getSearchButton();
		String productTwo = listPage.getProductTitle();
		listPage.getAddProductTocart().getContinueShopping().getNewSearchBox(datas.getProduct3()).getSearchButton();
		String productThree = listPage.getProductTitle();
		listPage.getAddProductTocart().getGoToCartButton();
		List<String> multipleTitle = atoCPage.getVerifyMultipleTitle();
		if (multipleTitle.contains(productOne) && multipleTitle.contains(productTwo) && multipleTitle.contains(productThree)) {
			System.out.println("All Items added to cart successfully");
		}
		else {
			for (String string : multipleTitle) {
				System.out.println("Items in cart "+string);
			}
			System.out.println("");
			System.out.println(productOne);
			System.out.println(productTwo);
			System.out.println(productThree);
		}
		atoCPage.getCheckOutButton().getGuestSignIn().getFillDetails(s1, s2, s3, s4, s5, s6).getContinuePaymentButtonError(); 
		//getEmailBox().getPhoneBox().getContinuePaymentButton();
		snap.addScreenCaptureFromPath(captureScreenshot(), "validateAddingMultipleItemsTocartAndCheckout");
		snap.log(Status.PASS, page.getContinuePaymentButtonError());
		extent.flush();
	}
}
