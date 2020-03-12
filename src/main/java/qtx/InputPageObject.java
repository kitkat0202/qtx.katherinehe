package qtx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputPageObject  extends SuperPageObject {

	public InputPageObject(WebDriver driverInstance, String baseUrl) {
		super(driverInstance, baseUrl);
	}
	
	By NumericInputLocator = By.tagName("input");

	public InputPageObject OpenInputPage() {
		super.Navigate("/inputs");
		return this;
	}
	
	public InputPageObject EnterNumber(int number) {
		getNumericInput(NumericInputLocator).setValue(number);
		return this;
	}
	
	public int getNum() {
		return getNumericInput(NumericInputLocator).getValue();
	}

}
