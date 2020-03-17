package theInternet.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends SuperPageObject {

	public LoginPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}

	@FindBy(id="username")
	WebElement userNameTextbox;
	
	@FindBy(id="password")
	WebElement passwordTextbox;
	
	@FindBy(how=How.TAG_NAME, using="button")
	WebElement submitButton;
	
	public SecurePage login(String userName, String userPassword) {
		userNameTextbox.sendKeys(userName);
		passwordTextbox.sendKeys(userPassword);
		
		submitButton.click();
		
		return new SecurePage(driver, baseUrl);
	}

	public LoginPage navigate() {
		super.navigate("/login");	

		return this;
	}
}
