package qtx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownPageObject extends SuperPageObject{
	
	private String BaseURL;
	
	public DropDownPageObject(WebDriver driverInstance, String Baseurl) {
		super(driverInstance);
		BaseURL = Baseurl;
	}
	
	public DropDownPageObject OpenDropDownPage() {
		String url = "/dropdown";
		Navigate(BaseURL + url);
		return this;
	}
	
	public Select getDropDown() {
		WebElement dropdown = driver.findElement(By.id("dropdown"));
		Select dropdownList = new Select(dropdown);
		return dropdownList;
	}
	
	public DropDownPageObject setDropdownList(String value) {
		getDropDown().selectByVisibleText(value);
		return this;
	}
	
	public String getDropDownListValue() {
		return getDropDown().getFirstSelectedOption().getText();
	}

}
