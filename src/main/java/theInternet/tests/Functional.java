package theInternet.tests;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import theInternet.pages.AlertPopupPage;
import theInternet.pages.CheckboxPage;
import theInternet.pages.DownladPage;
import theInternet.pages.DropdownListPage;
import theInternet.pages.HomePage;
import theInternet.pages.HoverProfilePage;
import theInternet.pages.InputPage;
import theInternet.pages.JQueryMenuPage;
import theInternet.pages.LoginPage;
import theInternet.pages.SliderPage;
import theInternet.tests.TestSuperClass;

public class Functional extends TestSuperClass{
	
	//As a SDET
	//I want navigate to the website
	//So that I know I can create the webdriver object 
//	@Test
	public void canLaunchChromeDriver() {	
		//arrange
		String expectedPageTitle = "The Internet";

		//act
		String actualPageTitle = new HomePage(driver, baseUrl)
			.navigate()
			.getTitle();
		
		//assert
		Assert.assertEquals(actualPageTitle , expectedPageTitle, "Could not navigate to the url"); 
	}
	
	//As a user
	//I want select option 2 from the dropdownlist 
	//so that an option is selected
//	@Test
	public void canSelectDropdownListItem() {
		//arrange
		String expectedSelection = "Option 2";
		
		//act
		String selectionOptionText = new DropdownListPage(driver, baseUrl)
			.navigate()
			.selectOption(expectedSelection)
			.getSelectedDropdownlistItemText();			
		
		//assert
		Assert.assertEquals(selectionOptionText,  expectedSelection, "Cannot select a dropdown list value.");
	}
	
	//As a user 
	//I want to login 
	//So that users can do stuff
//	@Test
	public void canLogIn() {
		//arrange
		String userName = "tomsmith";
		String userPassword = "SuperSecretPassword!";
		String expectedMessage = "You logged into a secure area!\n×";
				
		//act
		String flashMessageText = new LoginPage(driver, baseUrl)
			.navigate()	
			.login(userName, userPassword)
			.getMessageText();

		//assert
		Assert.assertEquals(flashMessageText, expectedMessage);
	}

	//As a user
	//I want to toggle the checkboxes on the checkbox page
	//So that I can prove I know how
//	@Test
	public void canToggleCheckboxes() {
		//arrange
		boolean isChecked1expected = true;
		boolean isChecked2expected = false;
		
		//act
		CheckboxPage page = new CheckboxPage(driver, baseUrl)
			.navigate()
			.setCheckbox1(isChecked1expected)
			.setCheckbox2(isChecked2expected);
				
		boolean actual1 = page.getCheckbox1Checked();
		boolean actual2 = page.getCheckbox2Checked();
		
		//assert	
		Assert.assertEquals(actual1, isChecked1expected);
		Assert.assertEquals(actual2, isChecked2expected);
	}
	
	//As a user
	//I want to move the slider to maximum value
	//So that I prove I can automate mouse actions
//	@Test
	public void canMoveSliderToMaxRange() {
		//arrange	
		float expectedSliderValue = 5f;
		
		//act
		float actualSliderValue = new SliderPage(driver, baseUrl)
			.navigate()
			.setSliderMaxValue()
			.getSliderValue();
		
		//assert
		Assert.assertEquals(actualSliderValue, expectedSliderValue);
	}
	
	//As a user 
	//I want to enter values in the input box 
	//So I can prove I know how to use data provider
//	@Test(dataProvider="numberData")
	public void canInputNumbers(int number) {

		int actualNumberInput = new InputPage(driver, baseUrl)
				.navigate()
				.setInputValue(number)
				.getInputValue();
		
		//assert
		Assert.assertEquals(actualNumberInput, number);
	}
	
	@DataProvider(name = "numberData")
	public Object[] getNumberData() {
		return new Object[] { 2, 3, 5, 8, 13 };
	}
	
	// As a User
	// I want to click the last item on the MENU AND DOWNLOAD
	// So that I can navigate to that page
//	@Test
	public void canNavigateMenu() throws Exception {
		String expectedHeading = "menu.xls";
		String downloadLocation = "C:\\Users\\hekat\\Downloads";
		
		String actualHeading = new JQueryMenuPage(driver, baseUrl)
				.navigate()
				.ClickItemByMenuPath(new String[] { "Enabled", "Downloads", "Excel" })
				.waitTillDownloaded(downloadLocation)
				.getDownloadedFile();

		Assert.assertEquals(actualHeading, expectedHeading, "\n\nThe Jquery Menu Failed. \n\n");
	}
	
	// As a User
	// I want test ALERT POPUP
	// So that I know it works
//	@Test
	public void canTestAlerts() {
		String promptString = "Hello";

		String expectedTextJSAlert = "You successfuly clicked an alert";
		String expectedTextJSConfirm = "You clicked: Ok";
		String expectedTextJSDecline = "You clicked: Cancel";
		String expectedTextJSPrompt = "You entered: " + promptString;
		
		
		String actualTextJSAlert = new AlertPopupPage(driver, baseUrl)
				.navigate()
				.clickAlertBtn("Click for JS Alert")
				.acceptprompt()
				.getResultText();

		String actualTextJSConfirm = new AlertPopupPage(driver, baseUrl)
				.navigate()
				.clickAlertBtn("Click for JS Confirm")
				.acceptprompt()
				.getResultText();
		
		String actualTextJSDecline = new AlertPopupPage(driver, baseUrl)
				.navigate()
				.clickAlertBtn("Click for JS Confirm")
				.declineprompt()
				.getResultText();
		
		String actualTextJSPrompt = new AlertPopupPage(driver, baseUrl)
				.navigate()
				.clickAlertBtn("Click for JS Prompt")
				.sendTextToAlertandAccept(promptString)
				.getResultText();
		
		
		Assert.assertEquals(actualTextJSAlert, expectedTextJSAlert, "Alert Test - JS Alert Test Failed");
		Assert.assertEquals(actualTextJSConfirm, expectedTextJSConfirm, "Alert Test - JS Confirm Test Failed");
		Assert.assertEquals(actualTextJSDecline, expectedTextJSDecline, "Alert Test - JS Decline Test Failed");
		Assert.assertEquals(actualTextJSPrompt, expectedTextJSPrompt, "Alert Test - JS Prompt Test Failed");
		
	}
	
	// As a User
	// I want to HOVER OVER IMAGE and get the profile url
	// so I know the hover over and url are working and correct
//	@Test
	public void checkHoverAndProfileUrl() {
		List<String> expectedUrls = Arrays.asList("http://the-internet.herokuapp.com/users/1", "http://the-internet.herokuapp.com/users/2", "http://the-internet.herokuapp.com/users/3");
		
		List<String> actualUrls = new HoverProfilePage(driver, baseUrl)
				.navigate()
				.hoverOverImg()
				.getViewProfileUrl();

		for(int i = 0; i < actualUrls.size(); i++) {
			Assert.assertEquals(actualUrls.get(i), expectedUrls.get(i), "Profile Hover Test Fail at index: " + i);
		}
	}
	
	// As a User
	// I want to
	// So I 
	@Test
	public void getTextFileAndRead() throws Exception {
		String downloadedLocation = "C:\\Users\\hekat\\Downloads";
		String Link = "text.txt";
		String expectedText = "text";
		
		String actualText = new DownladPage(driver, baseUrl)
				.navigate()
				.downloadFile(Link)
				.waitTillDownloaded(downloadedLocation)
				.getTextInFile();
		
		Assert.assertEquals(actualText, expectedText, "File text.txt Download Test Fail");
		
	}
}
