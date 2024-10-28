package gyuri.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gyuri.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "card-body")
	List<WebElement> productsList;

	By productsBy = By.className("card-body");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toast = By.id("toast-container");
	By spinner = By.tagName("ngx-spinner");

	public List<WebElement> getProductsList() {
		waitForElementToAppear(productsBy);
		return productsList;
	}

	public WebElement getProductByName(String productName) {
		return getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
	}

	public void addToCart(String productName) {
		// TODO Auto-generated method stub
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toast);
		waitForElementToDisappear(spinner);
	}

}
