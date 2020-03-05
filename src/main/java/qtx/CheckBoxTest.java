package qtx;

import org.testng.annotations.Test;

public class CheckBoxTest extends TestSuperClass {

	// As a User
	// I want  o check all the available check boxes
	// So that I know all check boxes are active
	@Test
	public void selectAllCheckBoxes() {

		new CheckBoxPageObject(driver, Baseurl)
			.OpenCheckBoxPage() 			// Open Page
			.getAllUncheckBoxes() 			// Find all unchecked boxes
			.checkAllUncheckBoxes() 		// Check all boxes
			.getAllUncheckBoxes() 			// See if there is anymore unchecked boxes
			.confirmUncheckBoxListEmpty(); 	// see if there are any empty boxes
		}
}
