package cpsat_challenge2;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Exercise3 {
	WebDriver driver;
	
	@BeforeClass
	//Method to open Browser and Launch WebSite
	public void initialSetup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".\\Resources\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.nseindia.com/");
		Thread.sleep(5000);
	}
	
	@Test(priority=1)
	//Method to Search Company
	public void searchCompany() throws InterruptedException {
		//Sending Search string
		driver.findElement(By.xpath("//input[@id='keyword']")).sendKeys("Eicher Motors Limited");
		//Searching
		driver.findElement(By.xpath("//input[@id='keyword']")).sendKeys(Keys.RETURN);
		Thread.sleep(3000);
	}
	
	@Test(priority=2)
	
	//Method to take screenshot
	public void equityScreenShot() throws IOException {
		 TakesScreenshot scrShot =((TakesScreenshot)driver);

	        //Call getScreenshotAs method to create image file
	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	        //path to save screenshot
	        FileCopyUtils.copy(SrcFile, new File(".\\Resources\\screenshots\\Equity"+ "__" + timestamp() +".png"));
		
	}
	
	 public static String timestamp()  {
		 return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		
	}
	 @Test(priority=3)
	 //Method to print values
	 public void printValues() throws InterruptedException {
		 Thread.sleep(4000);
		String faceValue= driver.findElement(By.xpath("//span[@id='faceValue']")).getText();
		String weekHigh= driver.findElement(By.xpath("//span[@id='high52']")).getText();
		String weekLow= driver.findElement(By.xpath("//span[@id='low52']")).getText();
		System.out.println("FaceValue is: " +faceValue);
		System.out.println("52 WeekHigh is: "+weekHigh);
		System.out.println("52 WeekLow is: "+weekLow);
		 
	 }
	 
	 @Test(priority=4)
	 //Method to close the browser
	 public void browserClose() {
		 driver.quit();
	 }
}

