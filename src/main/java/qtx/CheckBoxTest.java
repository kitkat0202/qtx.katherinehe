package qtx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CheckBoxTest extends TestSuperClass {

	// As a User
	// I want  o check all the available check boxes
	// So that I know all check boxes are active
	@Test
	public void selectAllCheckBoxes() {

		new CheckBoxPageObject(driver, Baseurl)
			.OpenCheckBoxPage() 			// Open Page
			.getAllUncheckBoxes() 			// List unchecked boxes
			.checkAllUncheckBoxes() 		// Chick all unchecked boxes
			.getAllUncheckBoxes() 			// List unchecked boxes
			.confirmUncheckBoxListEmpty(); 	// see if there are any empty boxes
		}
		
		
		
		
//		Solution for Joshua to select individual check boxes
//		new CheckBoxPageObject(driver, Baseurl)
//			.OpenCheckBoxPage();
//		
//		WebElement firstCheckBox = driver.findElement(By.cssSelector("input[type='checkbox']:nth-child(1)"));
//		firstCheckBox.click();
}
