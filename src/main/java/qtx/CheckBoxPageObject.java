package qtx;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBoxPageObject extends SuperPageObject {

	private List<WebElement> uncheckBoxList;
	private List<WebElement> checkBoxList;

	public CheckBoxPageObject(WebDriver driverInstance, String baseUrl) {
		super(driverInstance, baseUrl);
	}

	public CheckBoxPageObject OpenCheckBoxPage() {
		super.Navigate("/checkboxes");
		return this;
	}

	public CheckBoxPageObject getAllUncheckBoxes() {
		uncheckBoxList = driver.findElements(By.cssSelector("input[type='checkbox']:not(:checked)"));
		return this;
	}


	public CheckBoxPageObject checkAllUncheckBoxes() {
		for(WebElement checkBox:uncheckBoxList) {
			checkBox.click();
		}
		return this;
	}
	
	public CheckBoxPageObject uncheckAllCheckBoxes() {
		for(WebElement checkBox:checkBoxList) {
			checkBox.click();
		}
		return this;
	}
	
	

	public boolean confirmNoUncheckBoxes() {
		if(!uncheckBoxList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
