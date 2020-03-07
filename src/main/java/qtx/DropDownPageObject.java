package qtx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DropDownPageObject extends SuperPageObject {
	
	public DropDownPageObject(WebDriver driverInstance, String baseUrl) {
		super(driverInstance, baseUrl);
	}
	
	public DropDownPageObject OpenDropDownPage() {
		super.Navigate("/dropdown");
		return this;
	}

	
	public DropDownPageObject setDropdownList(String value) {
		WebElement dropdown = driver.findElement(By.id("dropdown"));
		getSelect(dropdown).selectByVisibleText(value);
		return this;
	}
	
	public String getDropDownListValue() {
		WebElement dropdown = driver.findElement(By.id("dropdown"));
		return getSelect(dropdown).getFirstSelectedOption().getText();
	}

}
