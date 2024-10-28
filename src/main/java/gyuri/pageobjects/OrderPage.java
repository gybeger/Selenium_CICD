package gyuri.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gyuri.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderElements;

	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = orderElements.stream().anyMatch(product -> product.getText().contains(productName));
		System.out.println("benn van a historyban: " + match);
		return match;
	}
}