package gyuri.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import gyuri.pageobjects.CartPage;
import gyuri.pageobjects.ConfirmOrderPage;
import gyuri.pageobjects.LandingPage;
import gyuri.pageobjects.PaymentPage;
import gyuri.pageobjects.ProductCatalogue;
import gyuri.tests.TestComponents.BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=gyuri.tests.TestComponents.Retry.class)
	public void loginErrorValidation() throws IOException {
		String userEmail = "villanostra@freemail.hu";
		String badUserPassword = "Negen1234zz";
		lp.login(userEmail, badUserPassword);
	//	Assert.assertEquals("Incorrect email  password.", lp.getErrorMessage()); //will fail
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	
	}
	
	
	@Test
	public void productErrorValidation() throws IOException {
		String userEmail = "villanostra@freemail.hu";
		String userPassword = "Negen1234";
		String productName = "ZARA COAT 3";
		String badProductName = "xyz";

		ProductCatalogue pc= lp.login(userEmail, userPassword);
		pc.addToCart(productName);
		CartPage cp = pc.navigateToCartPage();
		Boolean match=cp.checkElementInCart(badProductName);
		Assert.assertFalse(match);
	}

}
