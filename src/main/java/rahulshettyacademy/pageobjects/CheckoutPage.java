package rahulshettyacademy.pageobjects;
import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		//innilaization
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;

	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	//.ta-item:nth-of-type(2)
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	
	public void selectCountry(String CountryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, CountryName).build().perform();
		waitForElementToAppear(results);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		selectCountry.click();
	}
	public ConformationPage SubmitOrder()
	{
		submit.click();
		ConformationPage conformationPage = new ConformationPage(driver);
		return conformationPage;
	}

	
	

}
