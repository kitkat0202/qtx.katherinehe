package ui.control.extensions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class Slider extends BaseUiControlExtension {

	public Slider(WebDriver driver, WebElement sliderContainer) {
		super(driver, sliderContainer);
	}

	private By rangeLocator = By.id("range");
	private By handleLocator = By.tagName("input");

	public void setMaxValue() {

		WebElement sliderHandle = getSliderHandle();
		
		float maxSliderValue = Float.parseFloat(sliderHandle.getAttribute("max"));

		if(getValue() == maxSliderValue) {
			return;
		}

		int sliderWidth = sliderHandle.getSize().getWidth();
		int sliderXposition = sliderHandle.getLocation().getX();
		int xOffset = sliderWidth + sliderXposition;

		new Actions(driver)
		.moveToElement(sliderHandle)
		.click()
		.dragAndDropBy(sliderHandle, xOffset, 0)
		.build()
		.perform();		

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(200));

		String expectedValueString = String.valueOf(maxSliderValue);
		wait.until(ExpectedConditions.textToBe(rangeLocator,  expectedValueString));
	}

	public float getValue() {
		String valueString = mappedElement.findElement(rangeLocator).getText();
		return Float.parseFloat(valueString); //todo: compensate for zero precision
	}
	
	private WebElement getSliderHandle() {
		return mappedElement.findElement(handleLocator);
	}
}
