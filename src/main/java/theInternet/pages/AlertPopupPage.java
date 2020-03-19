package theInternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlertPopupPage extends SuperPageObject {

	public AlertPopupPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}

	public AlertPopupPage navigate() {
		super.navigate("/javascript_alerts");
		return this;
	}

	public AlertPopupPage clickAlertBtn(String string) {
		WebElement button = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/ul[1]/li[1]/button[1]"));
		button.click();
		return this;
	}

	public AlertPopupPage acceptprompt() {
		
		return this;
	}

	public String getResultText() {
		
		return "Hello";
	}

}
