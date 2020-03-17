package theInternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SliderPage extends SuperPageObject {

	public SliderPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}

	By sliderContainerLocator = By.cssSelector("div.sliderContainer");
	
	public SliderPage navigate() {
		super.navigate("/horizontal_slider");
		
		return this;
	}

	public SliderPage setSliderMaxValue() {	
		getSlider(sliderContainerLocator).setMaxValue();
		
		return this;
	}

	public float getSliderValue() {
		return getSlider(sliderContainerLocator).getValue();
	}
}
