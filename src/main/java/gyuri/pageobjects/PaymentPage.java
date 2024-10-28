package gyuri.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gyuri.AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {
	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "action__submit")
	WebElement PaymentButton;

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement DynamicDropdown;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement DesiredCountryInDropdown;

	By CountryDropdown = By.cssSelector(".ta-results");

	public void selectCountry(String selectedCountry) {
		Actions a = new Actions(driver);
		a.sendKeys(DynamicDropdown, selectedCountry).build().perform();
		waitForElementToAppear(CountryDropdown);
		DesiredCountryInDropdown.click();
	}

	public ConfirmOrderPage clickPaymentButton() {
		PaymentButton.click();
		return new ConfirmOrderPage(driver);
	}
}