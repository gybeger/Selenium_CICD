package gyuri.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gyuri.pageobjects.CartPage;
import gyuri.pageobjects.ConfirmOrderPage;
import gyuri.pageobjects.LandingPage;
import gyuri.pageobjects.OrderPage;
import gyuri.pageobjects.PaymentPage;
import gyuri.pageobjects.ProductCatalogue;
import gyuri.tests.TestComponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider ="getData", groups= {"PurchaseOrder"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
		String selectedCountry = "hu";
		String expectedConfirmOrderMessage = "THANKYOU FOR THE ORDER.";
		ProductCatalogue pc = lp.login(input.get("userEmail"), input.get("userPassword"));
		pc.addToCart(input.get("productName"));
		CartPage cp = pc.navigateToCartPage();
		Boolean match = cp.checkElementInCart(input.get("productName"));
		Assert.assertTrue(match);
		PaymentPage pp = cp.clickCheckoutButton();
		pp.selectCountry(selectedCountry);
		ConfirmOrderPage cop = pp.clickPaymentButton();
		String realConfirmOrderMessage = cop.getMessage();
		Assert.assertEquals(realConfirmOrderMessage, expectedConfirmOrderMessage);
	}

	@Test(dataProvider ="getData", dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest(HashMap<String,String> input) throws Exception {
		ProductCatalogue pc = lp.login(input.get("userEmail"), input.get("userPassword"));
		OrderPage op = pc.navigateToOrderPage();
		Assert.assertTrue(op.verifyOrderDisplay(input.get("productName")));
	}
	

	
	@DataProvider
	/*public Object[][] getData() {
		return new Object[][] {{"villanostra@freemail.hu", "Negen1234", "ZARA COAT 3"}, {"gybeger@freemail.hu","Negen1234", "ADIDAS ORIGINAL"}};
	}*/
	/*
	public Object[][] getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userEmail", "villanostra@freemail.hu");
		map.put("userPassword", "Negen1234");
		map.put("productName", "ZARA COAT 3");
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("userEmail", "gybeger@freemail.hu");
		map2.put("userPassword", "Negen1234");
		map2.put("productName", "ADIDAS ORIGINAL");
        return new Object[][] {{map}, {map2}};
    }
    */
	
	public Object[][] getData() throws IOException {
		List <HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\gyuri\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
    
}
