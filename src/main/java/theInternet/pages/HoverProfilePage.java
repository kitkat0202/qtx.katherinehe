package theInternet.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HoverProfilePage extends SuperPageObject {
	
	@FindBy(css="#content > div > div")
	List<WebElement> divs;
	
	private List<String> UrlList = new ArrayList<String>();

	public HoverProfilePage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}
	
	public HoverProfilePage navigate() {
		super.navigate("/hovers");
		return this;
	}

	public HoverProfilePage hoverOverImg() {
		Actions hover = new Actions(driver);
		for(WebElement divToHover: divs) {
			hover.moveToElement(divToHover).perform();
			UrlList.add(divToHover.findElement(By.cssSelector("div a")).getAttribute("href"));
		}
		
		return this;
	}

	public List<String> getViewProfileUrl() {
		return UrlList;
	}

}
