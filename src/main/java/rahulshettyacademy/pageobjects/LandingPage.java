package rahulshettyacademy.pageobjects;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//innilaization
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//WebDriver userEmail= driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	//ng-tns-c4-9 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
	@FindBy(css="[class*='flyInOut'")
	WebElement errormessage;
	public ProductCatalogue LoginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
		
	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errormessage);
		return errormessage.getText();
	}
	public void GoTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	

}
