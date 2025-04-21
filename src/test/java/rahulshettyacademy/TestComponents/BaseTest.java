package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		//properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\Study\\AutomationProject\\SeleniumFrameworkDesign\\src\\main\\java\\rahulshettyacademy\\resourcese\\GlobalDate.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			//Firefox
			
		}
		else if (browserName.equalsIgnoreCase("Edge"))
		{
			//edge
			
		}
		
        		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//sudiptachowdhury6597@gmail.com
		driver.manage().window().maximize();
		return driver;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	
	//{map, map}

	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
    {
    	TakesScreenshot ts = (TakesScreenshot)driver;
    	File source = ts.getScreenshotAs(OutputType.FILE);
    	File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
    	FileUtils.copyFile(source, file);
    	return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
    }
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunceApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.GoTo();
		return landingPage;
		
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}

}
