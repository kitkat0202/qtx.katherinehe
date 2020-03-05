package qtx;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class SuperPageObject {

	protected WebDriver driver;

	protected SuperPageObject(WebDriver driverInstance) {
		driver = driverInstance;
		PageFactory.initElements(driver,  this);
	}

	protected void Navigate(String url) {
		driver.navigate().to(url);
	}

}
