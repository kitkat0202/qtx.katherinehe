package qtx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import qtx.ui.control.extensions.CheckboxGroup;
import qtx.ui.control.extensions.NumericInput;
import qtx.ui.control.extensions.Slider;

public abstract class SuperPageObject {

	protected WebDriver driver;
	protected String baseUrl;

	protected SuperPageObject(WebDriver driverInstance, String baseUrl) {
		this.driver = driverInstance;
		this.baseUrl = baseUrl;
		PageFactory.initElements(driver,  this);
	}

	protected void Navigate(String extention) {
		String url = new String(baseUrl + extention);
		driver.navigate().to(url);
	}

	protected Select getSelect(WebElement element) {
		return new Select(element);
	}
	
	protected CheckboxGroup getCheckboxGroup(By locator) {
		return new CheckboxGroup(driver, driver.findElement(locator));
	}
	
	protected Slider getSlider(By locator) {
		return new Slider(driver, driver.findElement(locator));
	}
	
	protected NumericInput getNumericInput(By locator) {
		return new NumericInput(driver, driver.findElement(locator));
	}

}
