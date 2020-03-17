package theInternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckboxPage extends SuperPageObject{

	public CheckboxPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}
	
	// this is an alternative to using the Page Factory
	private By checkboxGroup = By.id("checkboxes");
	
	public CheckboxPage navigate() {
		super.navigate("/checkboxes");

		return this;
	}

	public CheckboxPage setCheckbox1(boolean isChecked) {	
		super.getCheckboxGroup(checkboxGroup).getCheckbox(0).set(isChecked);

		return this;
	}

	public CheckboxPage setCheckbox2(boolean isChecked) {
		super.getCheckboxGroup(checkboxGroup).getCheckbox(1).set(isChecked);

		return this;
	}

	public boolean getCheckbox1Checked() {

		return super.getCheckboxGroup(checkboxGroup).getCheckbox(0).getIsChecked(); 
	}	

	public boolean getCheckbox2Checked() {

		return super.getCheckboxGroup(checkboxGroup).getCheckbox(1).getIsChecked();
	}	
}
