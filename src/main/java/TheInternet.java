import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TheInternet {
  
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	  driver = new ChromeDriver();
	  String url = "http://the-internet.herokuapp.com/login";
	  driver.navigate().to(url);
  }
 
  // As a user
  // I want to log in
  // So users can do stuff
  @Test
  public void canLogIn() {
	  // arrange
	  String userName = new String("tomsmith");
	  String password = new String("SuperSecretPassword!");
	  String expectedGreenBoxText = new String("You logged into a secure area!\n×");
	  
	  // act
	  WebElement userNameTextBox = driver.findElement(By.id("username"));
	  WebElement passwordTextBox = driver.findElement(By.id("password"));
	  WebElement submitBox = driver.findElement(By.tagName("button"));
	  
	  userNameTextBox.sendKeys(userName);
	  passwordTextBox.sendKeys(password);
	  submitBox.click();
	  
	  //assert
	  WebElement greenBox = driver.findElement(By.id("flash"));
	  String actualGreenBoxText = greenBox.getText();
	  
	  Assert.assertEquals(actualGreenBoxText, expectedGreenBoxText, "Can not Log in!");
  }
  
  @AfterTest
  public void afterTest() {
//	  driver.quit();
  }
  
  private WebDriver driver;

}
