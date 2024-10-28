package gyuri.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import gyuri.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		//WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-search-engine-choice-screen");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage lp = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("villanostra@freemail.hu");
		driver.findElement(By.id("userPassword")).sendKeys("Negen1234");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("card-body")));
		
		String productName = "ZARA COAT 3";

		//List <WebElement> productsList = driver.findElements(By.cssSelector(".mb-3"));
		List <WebElement> productsList = driver.findElements(By.className("card-body"));
		/*for (int i = 0; i < productsList.size(); i++) {
			System.out.println(productsList.get(i).findElement(By.cssSelector("b")).getText());
			System.out.println(productsList.get(i).findElement(By.xpath("//b")).getText());

		}*/

		//WebElement prod = productsList.stream().filter(product -> product.findElement(By.xpath("//b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);// NEM MŰKÖDIK
		WebElement prod = productsList.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("ngx-spinner")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("ngx-spinner"))));
 
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		List <WebElement> cartElements = driver.findElements(By.className("cartWrap"));
		//List <WebElement> cartElements = driver.findElements(By.cssSelector(".cartSection h3"));
		//List <WebElement> cartElements = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		
		Boolean match = cartElements.stream().anyMatch(product -> product.getText().contains(productName));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		/*
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("hu");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		List<WebElement> dropdownoptions = driver.findElements(By.cssSelector(".ta-item"));
		dropdownoptions.stream().filter(result -> result.getText().equalsIgnoreCase("Hungary")).findFirst().orElse(null).click();
		*/
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "hu").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

		driver.findElement(By.className("action__submit")).click();
		//String confirm= driver.findElement(By.cssSelector(".hero-primary")).getText();
		//System.out.println(confirm);
		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), "THANKYOU FOR THE ORDER.");
	

	}

}
