package qtx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogInPageObject extends SuperPageObject{


	public LogInPageObject(WebDriver driverInstance, String baseUrl) {
		super(driverInstance, baseUrl);
	}

	public LogInPageObject OpenLoginPage() {
		super.Navigate("/login");
		return this;
	}

	public LogInSecurePageObject login(String userName, String password) {
		WebElement userNameTextBox = driver.findElement(By.id("username"));
		WebElement passwordTextBox = driver.findElement(By.id("password"));
		WebElement submitBox = driver.findElement(By.tagName("button"));

		userNameTextBox.sendKeys(userName);
		passwordTextBox.sendKeys(password);
		submitBox.click();
		return new LogInSecurePageObject(driver, baseUrl);

	}

}
