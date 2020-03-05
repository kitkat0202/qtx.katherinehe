package qtx;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInTest extends TestSuperClass {

	// As a user
	// I want to log in
	// So users can do stuff
	@Test
	public void canLogIn() {
		// arrange
		String userName = new String("tomsmith");
		String password = new String("SuperSecretPassword!");
		String expectedGreenBoxText = new String("You logged into a secure area!\n�");

		// act
		String actualGreenBoxText = new LogInPageObject(driver, Baseurl)
				.OpenLoginPage()
				.login(userName, password) // On Login page -  driver enter in password and username
				.getLogInConfirmation(); // On secure page - get the test from greenbox

		// assert
		Assert.assertEquals(actualGreenBoxText , expectedGreenBoxText, "Can not Log in!");
	}

}
