package gyuri.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import gyuri.pageobjects.CartPage;
import gyuri.pageobjects.ConfirmOrderPage;
import gyuri.pageobjects.LandingPage;
import gyuri.pageobjects.PaymentPage;
import gyuri.pageobjects.ProductCatalogue;
import gyuri.tests.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest {

	public LandingPage lp;
	public ProductCatalogue pc;
	public CartPage cp;
	public ConfirmOrderPage cop;
	public PaymentPage pp;
	public String selectedCountry = "hu";

	@Given("I landed on webshop")
	public void i_landed_on_webshop() throws IOException {
		lp = launchApplication();
	}

	@Given("^User logged in with email (.+) and password (.+)$")
	public void user_logged_in_with_email_and_password(String userEmail, String userPassword) {

		pc = lp.login(userEmail, userPassword);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		pc.addToCart(productName);

	}

	@When("^Checkout (.+) and submit order$")
	public void checkout_and_submit_order(String productName) {
		cp = pc.navigateToCartPage();
		Boolean match = cp.checkElementInCart(productName);
		Assert.assertTrue(match);
		pp = cp.clickCheckoutButton();
		pp.selectCountry(selectedCountry);
		cop = pp.clickPaymentButton();
	}

    @Then ("{string} message is displayed on confirmationPage")
    public void message_is_displayed_on_confirmation_page(String expectedConfirmOrderMessage) {
        Assert.assertEquals(cop.getMessage(), expectedConfirmOrderMessage);
		driver.quit();

    }

	
	@Then("{string} message is displayed")
	public void message_is_displayed(String expectedErrorMessage) {
		Assert.assertEquals(lp.getErrorMessage(),expectedErrorMessage);
		driver.quit();
	}

}
