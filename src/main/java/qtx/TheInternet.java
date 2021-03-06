package qtx;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TheInternet extends TestSuperClass {
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
		String actualGreenBoxText = new LogInPageObject(driver, baseUrl)
				.OpenLoginPage()
				.login(userName, password) // On Login page -  driver enter in password and username
				.getLogInConfirmation(); // On secure page - get the test from greenbox

		// assert
		Assert.assertEquals(actualGreenBoxText , expectedGreenBoxText, "Can not Log in!");
	}


	// As a user
	// I want to select option 2 from a drop down list
	// So that some option is selected
	@Test
	public void canSelectDrobdownListItem() {

		String expectedSelection = new String("Option 2");

		String actualSelection = new DropDownPageObject(driver, baseUrl)
				.OpenDropDownPage()
				.setDropdownList(expectedSelection)
				.getDropDownListValue();

		Assert.assertEquals(actualSelection, expectedSelection, "Cannot select a dropdown list value!");
	}


	// As a User
	// I want to check all the available check boxes
	// So that I know all check boxes are active
	@Test
	public void selectAllCheckBoxes() {
		boolean isAllBoxesChecked = new CheckBoxPageObject(driver, baseUrl)
				.OpenCheckBoxPage() 			// Open Page
				.getAllUncheckBoxes() 			// List unchecked boxes
				.checkAllUncheckBoxes() 		// Click all unchecked boxes
				.getAllUncheckBoxes() 			// List unchecked boxes
				.confirmNoUncheckBoxes(); 		// see if there are any empty boxes

		Assert.assertTrue(isAllBoxesChecked, "The Checkbox could not be checked");
	}
	
	
	//As a user
	//I want to move the slider to the max value
	//So I can prove that I know how to use the Actions builder
	@Test
	public void testHorizontalSlider() {
		//arrange
		String expectedSpanValue = "5";
		
		//act
		String actualSpanValue = new SliderPageObject(driver,baseUrl)
				.OpenSliderPage()
				.MoveSlider()
				.getSpanInfo();
		
		//assert
		Assert.assertEquals(actualSpanValue, expectedSpanValue, "The Slider Doesnot work");
	}
	
	// As a user
	// I want to enter numbers in input
	// So I know it can have numbers enterd in
	@Test(dataProvider="objNum")
	public void testNumberInput(int num) {
		int actualNum = new InputPageObject(driver, baseUrl)
				.OpenInputPage()
				.EnterNumber(num)
				.getNum();
		
		Assert.assertEquals(actualNum, num);
	}
	
	
	@DataProvider(name = "objNum")
	public Object[] getNumberData() {
		return new Object[] { 2, 3, 5, 8, 13 };
	}
}
