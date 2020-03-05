package qtx;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DropDownTest extends TestSuperClass {

	// As a user
	// I want to select option 2 from a drop down list
	// So that some option is selected
	@Test
	public void canSelectDrobdownListItem() {

		String expectedSelection = new String("Option 2");

		String actualSelection = new DropDownPageObject(driver, Baseurl)
				.OpenDropDownPage()
				.setDropdownList(expectedSelection)
				.getDropDownListValue();

		Assert.assertEquals(actualSelection, expectedSelection, "Can not select a dropdown list value!");
	}

}
