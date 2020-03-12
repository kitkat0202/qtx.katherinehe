package qtx.ui.control.extensions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseUiControlExtension {

	protected WebElement mappedElement;
	protected WebDriver driver;

	public BaseUiControlExtension(WebDriver driver, WebElement mappedElement) {
		this.driver = driver;
		this.mappedElement = mappedElement;
	}

}
