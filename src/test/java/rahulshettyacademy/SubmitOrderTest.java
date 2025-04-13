package rahulshettyacademy;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConformationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//sudiptachowdhury6597@gmail.com
		driver.manage().window().maximize();
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		LandingPage landingPage = new LandingPage(driver);
		landingPage.GoTo();
		ProductCatalogue productCatalogue = landingPage.LoginApplication("sudiptachowdhury6597@gmail.com", "Dipto6597#");
		//ProductCatalogue productCatalogue = new ProductCatalogue(drivrr);
		
		//List<WebElement>products= productCatalogue.getProductList();
		
		
		productCatalogue.addProductToCart(productName);
		
		CartPage cartPage = productCatalogue.goToCartPage();
		
		
		//CartPage cartPage = new CartPage(driver);
		Boolean match= cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		ConformationPage conformationPage = checkoutPage.SubmitOrder();

		String conformmessage = conformationPage.getConformationMessage();
		Assert.assertTrue(conformmessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
		
	}

}
