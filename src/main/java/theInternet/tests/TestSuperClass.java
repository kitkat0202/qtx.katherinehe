package theInternet.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import framework.DriverManager;
import framework.DriverManagerFactory;
import framework.DriverTypes;

public class TestSuperClass {
	protected WebDriver driver;
	protected String baseUrl;
	protected DriverManager driverManager;
	
	@BeforeMethod
	public void beforeTest() throws Exception {
		this.baseUrl = "http://the-internet.herokuapp.com";
		driverManager = DriverManagerFactory.getManager(DriverTypes.CHROME);	
		driver = driverManager.getDriver();
		
		long time = 10;
		TimeUnit unit = TimeUnit.SECONDS;
		driver.manage().timeouts().implicitlyWait(time , unit );
		driver.manage().timeouts().pageLoadTimeout(time, unit);
	}

	@AfterMethod
	public void afterTest() {
//		driverManager.quitDriver();
	}
}
