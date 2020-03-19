package theInternet.pages;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DownladPage extends SuperPageObject {
	
	private String dir;
	private File file;
	private String fileName;

	public DownladPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}

	public DownladPage navigate() {
		super.navigate("/download");
		return this;
	}

	public DownladPage downloadFile(String linkName) {
//		WebElement button = driver.findElement(By.xpath("//*[text()='" + linkName + "']"));
		WebElement button = driver.findElement(By.xpath("//*[contains(text(),'" + linkName + "')]"));

		button.click();
		return this;
	}
	
	public DownladPage waitTillDownloaded(String dlLocation) throws Exception {
		dir = dlLocation;

		do {
			Thread.sleep(1000);
			file = getLatestFilefromDir(dir);
			fileName = file.getName();
		} while(!fileName.endsWith(".txt"));

		return this;
	}

	public String getTextInFile() throws Exception {
		String Text = readFileText(getLatestFilefromDir(dir));
		deleteDownloadedFile();
		return Text;
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
            System.out.println("Invalid permissions:" + e);
        }
		
	}

	private String readFileText(File TestFile) throws Exception {
		FileReader FR = new FileReader(TestFile);
		BufferedReader BR = new BufferedReader(FR);
		String FullContent = "";
		String ContentHolder = "";

		//Loop to read all lines one by one from file and print It.
		while((ContentHolder = BR.readLine())!= null){
			FullContent = FullContent + ContentHolder;
		}

		BR.close();
		return FullContent;
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
