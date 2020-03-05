package qtx;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecurePageObject extends SuperPageObject{

	@FindBy(id="flash")
	private WebElement greenBox;

	public SecurePageObject(WebDriver driverInstance) {
		super(driverInstance);
	}

	public String getLogInConfirmation() {
		return greenBox.getText();
	}

}
