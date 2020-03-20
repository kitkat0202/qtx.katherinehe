package theInternet.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InfiniteScrollPage extends SuperPageObject {
	
	@FindBy(css=".jscroll-added")
	List<WebElement> paragraphs;
	
	private String ElementHiddenTillScroll;
	private String paragraphText;
	private int NextHiddenPara;

	public InfiniteScrollPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}

	public InfiniteScrollPage navigate() {
		super.navigate("/infinite_scroll");
		return this;
	}

	public InfiniteScrollPage getNumberOfParagraphShown() {
		NextHiddenPara = paragraphs.size() + 1;
		ElementHiddenTillScroll = ".jscroll-inner div:nth-child(" + NextHiddenPara + ")";
		return this;
	}
	
	public InfiniteScrollPage scrollToGetNextParagraph() throws Exception {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		List<WebElement> listOfShownDivs;

		do {
			je.executeScript("window.scrollBy(0,250)", "");
			Thread.sleep(500);
			listOfShownDivs = driver.findElements(By.cssSelector(ElementHiddenTillScroll));
		} while(listOfShownDivs.isEmpty());
		paragraphText = listOfShownDivs.get(0).getText();
		return this;
	}

	public String getParagraph() {
		System.out.println("\n\nScroll ang get next paragraph: " + paragraphText + "\n\n");
		return paragraphText;
	}

}
