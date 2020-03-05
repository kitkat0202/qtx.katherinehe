package qtx;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CheckBoxPageObject extends SuperPageObject {
	
	private String BaseURL;
	private List<WebElement> checkBoxList;

	public CheckBoxPageObject(WebDriver driverInstance, String Baseurl) {
		super(driverInstance);
		BaseURL = Baseurl;
	}

	public CheckBoxPageObject OpenCheckBoxPage() {
		String url = "/checkboxes";
		Navigate(BaseURL + url);
		return this;
	}
	
	public CheckBoxPageObject getAllUncheckBoxes(String cssSelector) {
		checkBoxList = driver.findElements(By.cssSelector(cssSelector));
		
		return this;
	}

	public CheckBoxPageObject checkAllUncheckBoxes() {
		for(WebElement checkBox:checkBoxList) {
			checkBox.click(); 
		}
		
		return this;
	}
	
	public CheckBoxPageObject confirmUncheckBoxListEmpty() {
		if(!checkBoxList.isEmpty()) {
			Assert.fail("Checkboxes can not be selected");
		}

		return null;
	}
}
