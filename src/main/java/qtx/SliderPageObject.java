package qtx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SliderPageObject extends SuperPageObject{
	

	public SliderPageObject(WebDriver driverInstance, String baseUrl) {
		super(driverInstance, baseUrl);
	}

	public SliderPageObject OpenSliderPage() {
		super.Navigate("/horizontal_slider");
		return this;
	}

	public SliderPageObject MoveSlider() {
		WebElement slider = driver.findElement(By.cssSelector(".sliderContainer input"));

		int width = slider.getSize().getWidth();

		Actions move = new Actions(driver);
		Action action = (Action) move.dragAndDropBy(slider, width, 0).build();
		action.perform();
		return this;
	}
	
	@FindBy(id="range")
	WebElement span;

	public String getSpanInfo() {
		return span.getText();
	}

}
