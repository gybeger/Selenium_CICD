package gyuri.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gyuri.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	


	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
    WebElement userPassword;
	
	@FindBy(id="login")
    WebElement loginButton;
	
	@FindBy(css="[class*=flyInOut]")
	WebElement errorMessage;
	
	public ProductCatalogue login(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
		ProductCatalogue pc = new ProductCatalogue(driver);
		return pc;

    }
	
	public void navigate() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
