package qtx;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestSuperClass {
	protected WebDriver driver;
	protected String baseUrl;
	protected DriverManager driverManager;

	@BeforeTest
	public void beforeTest() throws Exception {
		this.baseUrl = "http://the-internet.herokuapp.com";
		driverManager = DriverManagerFactory.getManager(DriverTypes.CHROME);
		driver = driverManager.getDriver();
	}

	@AfterTest
	public void afterTest() {
		//		driverManager.quitDriver();
	}

}
