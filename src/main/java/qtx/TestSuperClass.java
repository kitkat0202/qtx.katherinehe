package qtx;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestSuperClass {
	protected WebDriver driver;
	
	protected String Baseurl;

	@BeforeTest
	public void beforeTest() {
		driver = DriverManagerFactory.getManager(DriverType.CHROME).getDriver();
		Baseurl = "http://the-internet.herokuapp.com";
	}

	@AfterTest
	public void afterTest() {
				  driver.quit();
	}

}
