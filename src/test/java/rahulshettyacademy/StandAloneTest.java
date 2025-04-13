package rahulshettyacademy;
import java.util.List;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//sudiptachowdhury6597@gmail.com
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		//LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("sudiptachowdhury6597@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Dipto6597#");
		
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
			List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
	    WebElement prod =	products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
		String conformmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(conformmessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
		
	}

}
