package theInternet.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JQueryMenuPage extends SuperPageObject {
	
	@FindBy(css="a")
	List<WebElement> divs;
	
	private String dir;
	private String fileName;

	public JQueryMenuPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}


	public JQueryMenuPage navigate() {
		super.navigate("/jqueryui/menu");
		return this;
	}


	public JQueryMenuPage ClickItemByMenuPath(String[] navTexts) {
		for(String navText: navTexts) {
			for(WebElement div: divs) {
				if(div.getText().contains(navText)) {
					div.click();
				}
			}
		}
		return this;
	}


	public JQueryMenuPage waitTillDownloaded(String dlLocation) throws Exception{
		dir = dlLocation;

		do {
			Thread.sleep(1000);
			fileName = getLatestFilefromDir(dlLocation).getName();
		} while(!fileName.endsWith(".xls"));

		return this;
	}


	public String getDownloadedFile(){
		deleteDownloadedFile();
		return fileName;
	}
	
	private void deleteDownloadedFile() {
		String filesToDelete = dir + "\\" + fileName;

		try { 
            Files.deleteIfExists(Paths.get(filesToDelete)); 
        } catch(NoSuchFileException e) { 
            System.out.println("No such file/directory exists");
        } catch(DirectoryNotEmptyException e) { 
            System.out.println("Directory is not empty.");
        } catch(IOException e) { 
            System.out.println("Invalid permissions.");
        }
		
	}


	private File getLatestFilefromDir(String dirPath){
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }
	
	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile;
	}

}
