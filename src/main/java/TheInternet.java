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
	  
  }
  
  private WebDriver getWebDriver(String url) {
	  System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	  WebDriver driver = new ChromeDriver();
	  driver.navigate().to(url);
	  return driver;
  }
  
  
  
  // As a user
  // I want to log in
  // So users can do stuff
  @Test
  public void canLogIn() {
	  driver = getWebDriver("http://the-internet.herokuapp.com/login");
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
	  
	  // assert
	  WebElement greenBox = driver.findElement(By.id("flash"));
	  String actualGreenBoxText = greenBox.getText();
	  
	  Assert.assertEquals(actualGreenBoxText, expectedGreenBoxText, "Can not Log in!");
	  driver.quit();
  }
 
  
  
  // As a user
  // I want to select option 2 from a drop down list
  // So that some option is selected
  @Test
  public void selectOption2() {
	  driver = getWebDriver("http://the-internet.herokuapp.com/dropdown");
	  // arrange
	  String expectedTextInOption = new String("Option 2");
	  String expectedValueOnOptionSelect = new String("true");
  
	  // act
	  WebElement dropdownElement = driver.findElement(By.id("dropdown"));
	  WebElement option2Element = driver.findElement(By.cssSelector("#dropdown option:nth-child(3)"));

	  dropdownElement.click();
	  option2Element.click();
	  
	  // assert
	  String actualTextInOption = option2Element.getText();
	  String actualValueOnOptionSelect = option2Element.getAttribute("selected");
	  
	  Assert.assertEquals(actualTextInOption, expectedTextInOption, "The selected option was not Option 2 - Test has failed");
	  Assert.assertEquals(actualValueOnOptionSelect, expectedValueOnOptionSelect, "Option 2 was not selected - Test has failed");
	  driver.quit();
  }
  
  @AfterTest
  public void afterTest() {
	  
  }
  
  private WebDriver driver;

}
