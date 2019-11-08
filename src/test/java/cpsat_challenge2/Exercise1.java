package cpsat_challenge2;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.util.FileCopyUtils;


public class Exercise1 {
	WebDriver driver;
	
	
	 @Before
	 //Method for Browser and lauch
	public void initialSetup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".\\Resources\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://agiletestingalliance.org/");
		Thread.sleep(5000);
		
	}
	 //Method for capturing all the certifications
	 public void certificationMenu() throws InterruptedException, IOException {
		 driver.findElement(By.linkText("Certifications")).click();
		 Thread.sleep(5000);
		List<WebElement> icons= driver.findElements(By.xpath("//body//area"));
		
		System.out.println("icons size"+icons.size());
	 }
	 
	 //Method to check all the links
	 public void linkChecking() {
		 String url = "";
		 String homePage = "https://agiletestingalliance.org/";
		 HttpURLConnection huc = null;
	        int respCode = 200;
		 List<WebElement> icons1= driver.findElements(By.xpath("//body//area"));
		 Iterator<WebElement> it = icons1.iterator();
	        
	        while(it.hasNext()){
	            
	            url = it.next().getAttribute("href");
	            
	            System.out.println("URL is: " +url);
	        
	            if(url == null || url.isEmpty()){
	System.out.println("URL is either not configured for anchor tag or it is empty");
	                continue;
	            }
	            
	            if(!url.startsWith(homePage)){
	                System.out.println("URL belongs to another domain, skipping it.");
	               continue;
	            }
	            
	            
	            try {
	                huc = (HttpURLConnection)(new URL(url).openConnection());
	                
	                huc.setRequestMethod("HEAD");
	                
	                huc.connect();
	                
	                respCode = huc.getResponseCode();
	                
	                if(respCode >= 400){
	                    System.out.println(url+" is a broken link");
	                    TakesScreenshot scrShot =((TakesScreenshot)driver);

	        	        //Call getScreenshotAs method to create image file
	        	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	        	        FileCopyUtils.copy(SrcFile, new File(".\\Resources\\screenshots\\sc"+ "__" + timestamp() +".png"));
	                }
	                else{
	                    System.out.println(url+" is a valid link");
	                }
	                    
	            } catch (MalformedURLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	
		}
		 
	 //Method for TimeStamp
	 public static String timestamp()  {
		 return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		
	}
	//Method to hover on CCT Certificate
	 public void hoverCertification() throws IOException, InterruptedException, HeadlessException, AWTException {
		 Thread.sleep(5000);
         Actions actions = new Actions(driver);
         WebElement ele = driver.findElement(By.xpath("//map/area[4]"));
         actions.moveToElement(ele).build().perform();
         Thread.sleep(5000);
     BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
     // This will store screenshot on Specific location
     ImageIO.write(image, "png", new File("D:\\chromedriver_w\\screeshots\\sc_cct1"+System.currentTimeMillis()+".jpg"));



		/* TakesScreenshot scrShot =((TakesScreenshot)driver);

	        //Call getScreenshotAs method to create image file
	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	        FileCopyUtils.copy(SrcFile, new File("D:\\chromedriver_w\\screeshots\\sc_cct"+ "__" + timestamp() +".png"));*/
	 }
	 
	 //JNUIT TEST SUITE
	 @Test
	 public void testSuite() throws InterruptedException, IOException, HeadlessException, AWTException {
		 certificationMenu();
		 linkChecking();
		 hoverCertification();
	 }
	

}
