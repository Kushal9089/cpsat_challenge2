package cpsat_challenge2;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Exercise5 {
	WebDriver driver;
	
	
	@BeforeClass
	//Method open browser and redirect to URL
	public void initialSetup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_w\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.shoppersstop.com/");
		Thread.sleep(5000);
	}
	
	@Test(priority=1)
	//Method to move banner
	public void bannerMover() throws InterruptedException {
		Thread.sleep(3000);
		//adding banner elements into a list
		List<WebElement> slider=driver.findElements(By.xpath("//div[@role='tabpanel']"));
		//Size of list
		int siZe=slider.size();
		System.out.println(siZe);
		//For loop to move banner 
		for(int i=0;i<siZe;i++) {
			driver.findElement(By.xpath("//div[@class='dy-slick-arrow dy-next-arrow slick-arrow'][1]")).click();
			Thread.sleep(1000);
		}
	}
	    @Test(priority=2)
	    //Method to Hover over Men's Fragrance
		public void mensFragnance() throws InterruptedException {
	    	//to Click on search icon, as sometimes the 
	    	driver.findElement(By.xpath("//div[contains(@class,'col-md-5 col-lg-5')]//input[2]")).click();
	    	Thread.sleep(5000);
	    	Actions actions=new Actions(driver);
	    	//to Hover over men
	    	WebElement hoverMen=driver.findElement(By.xpath("//a[@title='MEN']"));
	    	actions.moveToElement(hoverMen).perform();
	    	Thread.sleep(5000);
	    	WebElement fragnance=driver.findElement(By.xpath("//div[contains(@class,'lvl2-container')]/ul[contains(@class,'lvl2')]/li[6]/a[contains(text(),\"Men's Fragrance\")]"));
	    	//body[contains(@class,'language-en')]/main/nav[contains(@class,'ssl-homepage-nav')]/div[contains(@class,'container container-responsive')]/div[contains(@class,'row')]/ul[contains(@class,'lvl1')]/li[contains(@class,'yCmsComponent ac tive')]/div[contains(@class,'lvl2-main')]/div[contains(@class,'lvl2-container')]/ul[contains(@class,'lvl2')]/li[6]/a[1]
	    	actions.moveToElement(fragnance).perform();
	    	Thread.sleep(5000);
	    	List<WebElement> accessories=driver.findElements(By.xpath("//li[4]/div/div/ul/li[6]/div/ul/li[1]/div/ul/li"));
			//body[contains(@class,'language-en')]/main/nav[contains(@class,'ssl-homepage-nav')]/div[contains(@class,'container container-responsive')]/div[contains(@class,'row')]/ul[contains(@class,'lvl1')]/li[4]/div[1]/div[1]/ul[1]/li[6]/div[1]/ul[1]/li[1]/div[1]/ul[1]
			System.out.println("size is " +accessories);
			for(int i=0;i<accessories.size();i++) {
				System.out.println("accesories are: " +accessories.get(i).getText());
			}
			}
	    @Test(priority=3)
	    public void allStores() throws InterruptedException {
	    	driver.findElement(By.linkText("All Stores")).click();
	    	Thread.sleep(6000);
	    	Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='city-name']")));

	        //Get all options
	        List<WebElement> dd = dropdown.getOptions();

	        //Get the length
	        System.out.println(dd.size());

	        // Loop to print one by one
	        for (int j = 0; j < dd.size(); j++) {
	            System.out.println(dd.get(j).getText());

	        }
	    	System.out.println("Page Title is: " +driver.getTitle());
	    }
		 
	 
	 @Test(priority=4)
	 public void browserClose() {
		 driver.quit();
	  }
}

