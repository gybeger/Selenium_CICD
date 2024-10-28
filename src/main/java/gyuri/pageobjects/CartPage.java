package gyuri.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import gyuri.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "cartWrap")
	List<WebElement> cartElements;

	@FindBy(css = ".totalRow button")
	WebElement CheckoutButton;

	public Boolean checkElementInCart(String productName) {
		Boolean match = cartElements.stream().anyMatch(product -> product.getText().contains(productName));
		System.out.println("benn van a kosárban: " + match);
		return match;
	}

	public PaymentPage clickCheckoutButton() {
		CheckoutButton.click();
		return new PaymentPage(driver);
	}
}