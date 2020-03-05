package qtx;

import org.testng.annotations.Test;

public class CheckBoxTest extends TestSuperClass {

	// As a User
	// I want  o check all the available check boxes
	// So that I know all check boxes are active
	@Test
	public void selectAllCheckBoxes() {
		String cssSelectorForNotCheck = ("input[type='checkbox']:not(:checked)");
		
		new CheckBoxPageObject(driver, Baseurl)
				.OpenCheckBoxPage() // Open Page
				.getAllUncheckBoxes(cssSelectorForNotCheck)
				.checkAllUncheckBoxes() // Check all boxes
				.getAllUncheckBoxes(cssSelectorForNotCheck)
				.confirmUncheckBoxListEmpty(); // see if there are any empty boxes
	}
}
