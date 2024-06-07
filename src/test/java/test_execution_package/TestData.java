package test_execution_package;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base_package.BaseClass;
import best_buy_website.SelectYourCountry;

public class TestData extends BaseClass{

	private String homeTheater = "TV & Home Theater";
	private String homeAudio = "Sound Bars & Home Audio";
	private String speaker = "Speakers";
	private String speakerType = "Floor speakers";
	private String brandName = "Samsung";
	private String product1 = "apple airpods";
	private String product2 = "mini fridge";
	private String product3 = "microwave";
	
	public String getProduct1() {
		return product1;
	}
	public String getProduct2() {
		return product2;
	}
	public String getProduct3() {
		return product3;
	}
	public String getHomeTheater() {
		return homeTheater;
	}
	public String getHomeAudio() {
		return homeAudio;
	}
	public String getSpeaker() {
		return speaker;
	}
	public String getSpeakerType() {
		return speakerType;
	}
	public String getBrandName() {
		return brandName;
	}

	public static Object[][] testdatas() throws IOException {
		
			Object[][] data = new Object[readValueFromExcel("SignIn").getPhysicalNumberOfRows()-1][readValueFromExcel("SignIn").getRow(1).getPhysicalNumberOfCells()];
			for (int i = 1; i < readValueFromExcel("SignIn").getPhysicalNumberOfRows(); i++) {	
				XSSFRow row = readValueFromExcel("SignIn").getRow(i);
				for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
					XSSFCell cell = row.getCell(j);
					data[i-1][j] = cell.toString();
				} }

			return data;
	}
	
	public static Object[][] signUpTestDatas() throws IOException {
		
		Object[][] data = new Object[readValueFromExcel("SignUp").getPhysicalNumberOfRows()-1][readValueFromExcel("SignUp").getRow(0).getPhysicalNumberOfCells()];
		for (int i = 1; i < readValueFromExcel("SignUp").getPhysicalNumberOfRows(); i++) {	
			XSSFRow row = readValueFromExcel("SignUp").getRow(i);
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				XSSFCell cell = row.getCell(j);
				data[i-1][j] = cell.toString();
			} }

		return data;
}

public static Object[][] paymentPageDetails() throws IOException {
		
		Object[][] data = new Object[readValueFromExcel("Details").getPhysicalNumberOfRows()-1][readValueFromExcel("Details").getRow(0).getPhysicalNumberOfCells()];
		for (int i = 1; i < readValueFromExcel("Details").getPhysicalNumberOfRows(); i++) {	
			XSSFRow row = readValueFromExcel("Details").getRow(i);
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				XSSFCell cell = row.getCell(j);
				data[i-1][j] = cell.toString();
			} 	}

		return data;
}
}
