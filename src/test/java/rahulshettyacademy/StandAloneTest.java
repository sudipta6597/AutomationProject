package rahulshettyacademy;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//sudiptachowdhury6597@gmail.com
		
		driver.findElement(By.id("userEmail")).sendKeys("sudiptachowdhury6597@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Dipto6597#");
		
		driver.findElement(By.id("login")).click();
	}

}
