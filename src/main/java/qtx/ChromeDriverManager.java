package qtx;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager {

	@Override
	protected void startService() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void stopService() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createDriver() {
		 System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		 driver = new ChromeDriver();
		 
		 long time = 1000;
		 TimeUnit unit = TimeUnit.MILLISECONDS;
		 driver.manage().timeouts().implicitlyWait(time, unit);
		 driver.manage().timeouts().pageLoadTimeout(time, unit);
	}

}
