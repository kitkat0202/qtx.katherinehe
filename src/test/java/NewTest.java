import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest {
	
//  @Test
//  public void testCase() {
//	  System.out.println("Hello World!");
//  }
  
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	  driver = new ChromeDriver();
	  String url = "http://google.com";
	  driver.navigate().to(url);
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
  @Test
  public void canClickLuckyButton() {
	  WebElement element = driver.findElement(By.id("gbqfbb"));
	  element.click();
  }
  
  private WebDriver driver;

}