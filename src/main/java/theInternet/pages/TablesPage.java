package theInternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TablesPage extends SuperPageObject {
	
	private WebElement FocusTable;

	public TablesPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}

	public TablesPage navigate() {
		super.navigate("/tables");
		return this;
	}

	public TablesPage getTableWithID(String TableId) {
		FocusTable = driver.findElement(By.id(TableId));
		return this;
	}

	public String getAdjacentValueOf(String Email) {
		WebElement TableDataDue = FocusTable.findElement(By.xpath("//*[contains(text(),'" + Email + "')]/following-sibling::td"));
		return TableDataDue.getText();
	}

}
