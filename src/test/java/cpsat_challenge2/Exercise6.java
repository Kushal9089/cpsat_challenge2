package cpsat_challenge2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise6 {
	WebDriver driver;
	Workbook writeWorkbook = new XSSFWorkbook();
	File file =    new File(".\\Resources\\dataWrite.xlsx");
	
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
	 public void liveMarket() throws InterruptedException {
		driver.findElement(By.linkText("Live Market")).click();
		Thread.sleep(4000);
		Actions actions= new Actions(driver);
		WebElement hover= driver.findElement(By.xpath("//a[contains(text(),'Top Ten Gainers / Losers')]"));
		//hovering over to Top Gainer/Losers
		actions.moveToElement(hover).perform();
		driver.findElement(By.xpath("//a[contains(text(),'Top Ten Gainers / Losers')]")).click();
	}
	@Test(priority=2)
	//Method to write Top Gainers data into excel sheet
	public void tableDataTopGainers() throws IOException,FileNotFoundException {
		
		 Sheet writeSheet = writeWorkbook.createSheet("Gainers");
		 Row createRow;
		 //Row Count
		int tableRows=driver.findElements(By.xpath("//table[@id='topGainers']/tbody/tr")).size();
		//Column Count
		int tableColumns=driver.findElements(By.xpath("//table[@id='topGainers']/tbody/tr/th")).size();
	
		//Loop to write data into Excel file
		for(int i=1;i<=tableRows;i++)
        {
			createRow = writeSheet.createRow(i-1);
			
            for(int j=1;j<=tableColumns;j++) {
               
            	
                if(i==1) {
                    //if i==1, header row will be written into the excel file
                	createRow.createCell(j-1).setCellValue(driver.findElement(By.xpath("//table[@id='topGainers']/tbody/tr["+i+"]/th["+j+"]")).getText());
                }
                    else {
                    	createRow.createCell(j-1).setCellValue(driver.findElement(By.xpath("//table[@id='topGainers']/tbody/tr["+i+"]/td["+j+"]")).getText());
                    	
                }
                
            }
                
        }
		    //output stream for writing in the excel
	           FileOutputStream outputStream = new FileOutputStream(file);
	           writeWorkbook.write(outputStream);
	           outputStream.close();
	           
	 }
		
	@Test(priority=3)
	//Method to write Top Losers data into excel sheet
	public void tableDataTopLosers() throws IOException, InterruptedException,FileNotFoundException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@id='tab8']")).click();
		Thread.sleep(2000);
		File file =    new File("C:\\Users\\Admin\\Downloads\\groww-reviewupdate\\groww-reviewupdate\\Resources\\dataWrite.xlsx");
		
		 Sheet writeSheet = writeWorkbook.createSheet("Losers");
		 Row createRow;
		 //Row Count
		int tableRows=driver.findElements(By.xpath("//table[@id='topLosers']/tbody/tr")).size();
		//Column count
		int tableColumns=driver.findElements(By.xpath("//table[@id='topLosers']/tbody/tr/th")).size();
		
		
		
		//Loop to write data into Excel file
		for(int i=1;i<=tableRows;i++)
        {
			createRow = writeSheet.createRow(i-1);
           
            for(int j=1;j<=tableColumns;j++) {
               
      
                if(i==1) {
                	//if i==1, header row will be written into the excel file
                	createRow.createCell(j-1).setCellValue(driver.findElement(By.xpath("//table[@id='topLosers']/tbody/tr["+i+"]/th["+j+"]")).getText());
                	
                }
                    else {
                    	createRow.createCell(j-1).setCellValue(driver.findElement(By.xpath("//table[@id='topLosers']/tbody/tr["+i+"]/td["+j+"]")).getText());
                    	
                }
                
            }
                
        }
		//output stream for writing in the excel
	           FileOutputStream outputStream = new FileOutputStream(file);
	           writeWorkbook.write(outputStream);
	           outputStream.close();
	         
	   
		
		}
	@Test(priority=4)
  //method for Comparing % change of losers
	public  void compareChangeLosers() throws IOException,FileNotFoundException {
		File file =    new File("C:\\Users\\Admin\\Downloads\\groww-reviewupdate\\groww-reviewupdate\\Resources\\dataWrite.xlsx");
		 FileInputStream inputStream = new FileInputStream(file);
		 Sheet writeSheet = writeWorkbook.getSheet("Losers");
		 int rowCount =writeSheet.getLastRowNum();
		 
		 List<String> changeLosers=new ArrayList();
		 //For loop to add values from excel to List
		 for(int k=1;k<=rowCount;k++) {
			 
			changeLosers.add(writeSheet.getRow(k).getCell(2).getStringCellValue());
			
			 
		 }
		//For loop to compare whether the values are high to low
		 for (int l=0;l<changeLosers.size()-1;l++) {
			 System.out.println(Float.parseFloat(changeLosers.get(l))+" < " +Float.parseFloat(changeLosers.get(l+1)));
			 Assert.assertTrue(Float.parseFloat(changeLosers.get(l))<= Float.parseFloat(changeLosers.get(l+1)));
		 }
		 
		inputStream.close();
	}
	@Test(priority=5)
	//method for Comparing % change of Gainers
	public  void compareChangeGainers() throws IOException,FileNotFoundException {
		File file =    new File("C:\\Users\\Admin\\Downloads\\groww-reviewupdate\\groww-reviewupdate\\Resources\\dataWrite.xlsx");
		 FileInputStream inputStream = new FileInputStream(file);
		 Sheet writeSheet = writeWorkbook.getSheet("Gainers");
		 int rowCount =writeSheet.getLastRowNum();
		 
		 List<String> changeGainers=new ArrayList();
		//For loop to add values from excel to List
		 for(int k=1;k<=rowCount;k++) {
			 
			 changeGainers.add(writeSheet.getRow(k).getCell(2).getStringCellValue());
			
			 
		 }
		//For loop to compare whether the values are high to low
		 for (int l=0;l<changeGainers.size()-1;l++) {
			 System.out.println(Float.parseFloat(changeGainers.get(l))+" > " +Float.parseFloat(changeGainers.get(l+1)));
			 Assert.assertTrue(Float.parseFloat(changeGainers.get(l))>= Float.parseFloat(changeGainers.get(l+1)));
		 }
		 
		inputStream.close();
		
	}
	@Test(priority=6)
	//Method to compare Site date with System date
	public void dateCompare() {
		//extracting the site date
	   String siteDate=	driver.findElement(By.xpath("//span[@id='dataTime']")).getText();
	   //Extracting the sub string from extracted date string
	   String temp=siteDate.substring(6);
	   //Defining the format for date
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss"); 
	   //Getting the Local date
	   LocalDateTime now = LocalDateTime.now();  
	   //Appending the IST to the system date
	   String sDate=dtf.format(now)+" IST";
	   //Comparing both dates
	   System.out.println("Comparing :" +"WebsiteDate " +temp +"With "+ "System Date " +sDate+" :" +sDate.equals(siteDate));
       	}
	@Test(priority=7)
	//Method to close the driver
	public void closeTest() {
		driver.close();
		driver.quit();
    	}
	
}



