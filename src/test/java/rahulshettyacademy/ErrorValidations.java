package rahulshettyacademy;

import java.io.IOException;
import java.util.List;

//import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConformationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacedemy.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest {
	
	
    @Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";

		landingPage.LoginApplication("sudiptachowdhury6597@gmail.com", "Dipto659#");
		
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
    @Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.LoginApplication("sudiptachowdhury6597@gmail.com", "Dipto6597#");		
		List<WebElement>products= productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match= cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
			
		
	}

}
