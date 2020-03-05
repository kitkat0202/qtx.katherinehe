package qtx;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
	protected WebDriver driver;
	protected abstract void startService();
	protected abstract void stopService();
	protected abstract void createDriver();
	
	public WebDriver getDriver() {
		createDriver();
		return driver;
	}
	
	public void quitDriver() {
		driver.quit();
	}
}
