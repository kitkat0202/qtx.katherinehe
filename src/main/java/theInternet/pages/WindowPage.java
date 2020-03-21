package theInternet.pages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WindowPage extends SuperPageObject {
	
	@FindBy(css=".example a")
	WebElement basePageLink;
	
	@FindBy(css=".example h3")
	WebElement newTabHeader3;
	
	ArrayList<String> tabs;

	public WindowPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}
	
	private void pt(String string) {
		System.out.println("\n" + string + "\n");
	}

	public WindowPage navigate() {
		super.navigate("/windows");
		return this;
	}

	public WindowPage clickLinkToNewTab() {
		basePageLink.click();
		tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		return this;
	}

	public String getTextAndClose() {
		String Header3Text = newTabHeader3.getText();
		driver.close();
		driver.switchTo().window(tabs.get(0));
		return Header3Text;
	}

}
