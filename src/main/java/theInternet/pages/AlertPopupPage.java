package theInternet.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertPopupPage extends SuperPageObject {
	
	@FindBy(id="result")
	WebElement resultMsg;

	public AlertPopupPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}

	public AlertPopupPage navigate() {
		super.navigate("/javascript_alerts");
		return this;
	}

	public AlertPopupPage clickAlertBtn(String string) {
//		WebElement button = driver.findElement(By.xpath("//*[text()='" + string + "']"));
		WebElement button = driver.findElement(By.xpath("//*[contains(text(),'" + string + "')]"));

		button.click();
		return this;
	}

	public AlertPopupPage acceptprompt() {
		Alert alert = driver.switchTo().alert();
		
		alert.accept();
		return this;
	}
	
	public AlertPopupPage declineprompt() {
		Alert alert = driver.switchTo().alert();
		
		alert.dismiss();
		return this;
	}
	
	public AlertPopupPage sendTextToAlertandAccept(String promptString) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(promptString);
		alert.accept();
		return this;
	}

	public String getResultText() {
		return resultMsg.getText();
	}



}
