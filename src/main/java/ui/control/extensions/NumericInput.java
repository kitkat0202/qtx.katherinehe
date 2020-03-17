package ui.control.extensions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NumericInput {

	private WebElement mappedElement;
	public NumericInput(WebDriver driver, WebElement findElement) {
		this.mappedElement = findElement;
	}

	public void setValue(int number) {
		mappedElement.sendKeys(String.valueOf(number));
		mappedElement.sendKeys(Keys.TAB);
	}

	public int getValue() {			
		String value = mappedElement.getAttribute("value");

		return Integer.parseInt((String) value);
	}

}
