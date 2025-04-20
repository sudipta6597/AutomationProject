package rahulshettyacademy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConformationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacedemy.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	
    @Test(dataProvider="getData", groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//String productName = "ZARA COAT 3";
		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		//sudiptachowdhury6597@gmail.com
//		driver.manage().window().maximize();
//		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.GoTo();
		ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"),input.get("password"));
		//ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		
		List<WebElement>products = productCatalogue.getProductList();
		
		
		productCatalogue.addProductToCart(input.get("productName"));
		
		CartPage cartPage = productCatalogue.goToCartPage();
		
		
		//CartPage cartPage = new CartPage(driver);
		Boolean match= cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		ConformationPage conformationPage = checkoutPage.SubmitOrder();

		String conformmessage = conformationPage.getConformationMessage();
		Assert.assertTrue(conformmessage.equalsIgnoreCase("Thankyou for the order."));		
		
	}
    
    @Test(dependsOnMethods= {"SubmitOrder"})
    public void OrderHistoryTest()
    {
    	//ZARA COAT 3
    	ProductCatalogue productCatalogue = landingPage.LoginApplication("sudiptachowdhury6597@gmail.com", "Dipto6597#");
    	OrderPage orderPage = productCatalogue.goToOrderPage();
    	Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    	
    	
    	
    }
    
//    @DataProvider
//	public Object[][] getData()
//	{	
//   		return new Object[][]  {{"sudiptachowdhury6597@gmail.com","Dipto6597#","ZARA COAT 3"}, {"dip6597.sc@gmail.com","Dipto6597#","ADIDAS ORIGINAL"} };				
//	}
//    
    
    @DataProvider
	public Object[][] getData() throws IOException
	{	
    	HashMap<String,String> map = new HashMap<String,String>();
    	map.put("email", "sudiptachowdhury6597@gmail.com");
    	map.put("password", "Dipto6597#");
    	map.put("productName", "ZARA COAT 3");
    	
    	HashMap<String,String> map1 = new HashMap<String,String>();  //System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json"
    	map1.put("email", "dip6597.sc@gmail.com");
    	map1.put("password", "Dipto6597#");
    	map1.put("productName", "ADIDAS ORIGINAL");  //D:\Study\AutomationProject\SeleniumFrameworkDesign\src\test\java\rahulshettyacademy\data\PurchaseOrder.json
    	return new Object[][]  {{map}, {map1} };
//    	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
//		return new Object[][]  {{data.get(0)}, {data.get(1) } };
    	
		
		
		
	}

}
