package cpsat_challenge2;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Exercise2 {
	WebDriver driver;
	
	@Test
	
	public void initialSetup() throws InterruptedException {
		
		//Setting the system property
		System.setProperty("webdriver.chrome.driver", ".\\Resources\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.nseindia.com/");
		Thread.sleep(5000);
		
		String advances=driver.findElement(By.xpath("//li[@id='advances']//span")).getText();
		System.out.println(advances);
		//List of webelemets of advanceTab
		List<WebElement> allElements=driver.findElements(By.xpath("//ul[@class='advanceTab']//span"));
		System.out.println("value: "+allElements.get(1).getText());
		List<String> values=new ArrayList();
		
		for(int i=0;i<allElements.size();i++)
		{values.add(allElements.get(i).getText());
		System.out.println("values: " +values);
			
		}
		Thread.sleep(4000);
		driver.quit();
		Collections.sort(values);
		System.out.println("values: " +values);
		System.out.println("Minimum value: " +values.get(0));
	}

}
