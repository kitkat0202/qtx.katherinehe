package theInternet.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IframePage extends SuperPageObject {
	
	private List<WebElement> NestedIFrame = new ArrayList<WebElement>();
	private List<String> ResultList = new ArrayList<String>();

	public IframePage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}
	
	private void pt(String string) {
		System.out.println(string);
	}

	public IframePage navigate() {
		super.navigate("/frames");
		return this;
	}

	public IframePage clickLinkContaining(String string) {
		WebElement Link = driver.findElement(By.xpath("//*[contains(text(),'" + string + "')]"));
		Link.click();
		return this;
	}

	public List<String> enterIframeAndGetText() {
		NestedIFrame = driver.findElements(By.tagName("frame"));
		
		int num = 0;
		do {
			num ++;
			divideIframefromNested();
		} while(NestedIFrame.size() > 0 && num < 6);

		return ResultList;
	}
	
	private void divideIframefromNested() {
		List<WebElement> HoldList = new ArrayList<WebElement>();
		HoldList.addAll(NestedIFrame);
		NestedIFrame.clear();
		
		for (WebElement iframe : HoldList) {
			driver.switchTo().frame(iframe);
			List<WebElement> checkifnested = driver.findElements(By.tagName("frame"));

			if(checkifnested.isEmpty()) {
				String text = driver.findElement(By.cssSelector("body")).getText();
				ResultList.add(text);
				driver.switchTo().parentFrame();
			} else {
				NestedIFrame.addAll(checkifnested);
				divideIframefromNested();
			}
		}
		driver.switchTo().parentFrame();
	}

}
