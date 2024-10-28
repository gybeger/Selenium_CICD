package gyuri.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gyuri.AbstractComponents.AbstractComponent;

public class ConfirmOrderPage extends AbstractComponent {
	WebDriver driver;

	public ConfirmOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement OrderConfirmationMessage;

	public String getMessage() {
		String message = OrderConfirmationMessage.getText();
		System.out.println("az üzenet: " + message);
		return message;
	}

}
