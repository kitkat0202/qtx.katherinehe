package theInternet.pages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WindowPage extends SuperPageObject {
	
	@FindBy(css="")
	WebElement basePageLink;
	
	@FindBy(css="h3")
	WebElement newTabHeader3;
	
	ArrayList<String> tabs;

	public WindowPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}

	public WindowPage navigate() {
		super.navigate("/windows");
		return this;
	}

	public WindowPage clickLinkToNewTab() {
		basePageLink.click();
		tabs = new ArrayList<String> (driver.getWindowHandles());
		return this;
	}

	public String getTextAndClose() {
		// TODO Auto-generated method stub
		return null;
	}

}
