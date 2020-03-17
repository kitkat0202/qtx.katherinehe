package ui.control.extensions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkbox extends BaseUiControlExtension{

	public Checkbox(WebDriver driver, WebElement mappedElement) {
		super(driver, mappedElement);
	}

	public void set(boolean doCheck) {
		if(doCheck != getIsChecked()) {	
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			
			String script = "arguments[0].click()";
			js.executeScript(script, mappedElement);
						
			waitUntilIsSelected(doCheck);
		}
	}

	private void waitUntilIsSelected(boolean isSelected) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementSelectionStateToBe(mappedElement, isSelected));
	}

	public boolean getIsChecked() {
		return mappedElement.isSelected();	
	}
}
