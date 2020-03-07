package qtx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInSecurePageObject extends SuperPageObject{

	@FindBy(id="flash")
	private WebElement greenBox;

	public LogInSecurePageObject(WebDriver driverInstance, String baseUrl) {
		super(driverInstance, baseUrl);
	}

	public String getLogInConfirmation() {
		WebElement greenBox = driver.findElement(By.id("flash"));
		return greenBox.getText();
	}

}
