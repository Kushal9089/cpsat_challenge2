package cpsat_challenge2;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Exercise4 {
	WebDriver driver;
	
	
	@BeforeClass
	//Method to open browser and redirect to URL
	public void initialSetup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_w\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.nseindia.com/");
		Thread.sleep(5000);
	}
	@Test(priority=1)
	//
	public void searchCompany() throws InterruptedException, IOException {
		 File file =    new File(".\\Resources\\companyNames.xlsx");
		 FileInputStream inputStream = new FileInputStream(file);
		 Workbook searchWorkbook = new XSSFWorkbook(inputStream);
		 Sheet searchSheet = searchWorkbook.getSheet("Sheet1");
		 //Row count
		 int rowcount=searchSheet.getLastRowNum();
		
	     //Loop to read data from Excel sheet
		 for (int i=1;i<=rowcount;i++) {
			//getting search test
			String searchText=searchSheet.getRow(i).getCell(0).toString();
		//sending search text
		driver.findElement(By.xpath("//input[@id='keyword']")).sendKeys(searchText);
		Thread.sleep(5000);
		//Searching
		driver.findElement(By.xpath("//input[@id='keyword']")).sendKeys(Keys.RETURN);
		Thread.sleep(5000);
	
		 TakesScreenshot scrShot =((TakesScreenshot)driver);

	        //Call getScreenshotAs method to create image file
	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	        //Destination to save screenshots
	        FileCopyUtils.copy(SrcFile, new File(".\\Resources\\screenshots\\"+searchText  + timestamp() +".png"));
	        Thread.sleep(5000);
			String faceValue= driver.findElement(By.xpath("//span[@id='faceValue']")).getText();
			String weekHigh= driver.findElement(By.xpath("//span[@id='high52']")).getText();
			String weekLow= driver.findElement(By.xpath("//span[@id='low52']")).getText();
			//printing Face Value 
			System.out.println("FaceValue of" +searchText +"is: " +faceValue);
			//printing 52 WeekHigh
			System.out.println("52 WeekHigh of" +searchText +"is: " +weekHigh);
			//printing 52 WeekLow
			System.out.println("52 WeekLow of" +searchText +"is: " +weekLow);
		 }
		 
		 inputStream.close();
	}
	
	 public static String timestamp()  {
		 return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		
	  }
	 
	 
	 @Test(priority=2)
	 public void browserClose() {
		 driver.quit();
	  }
}

