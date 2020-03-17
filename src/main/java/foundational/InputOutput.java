package foundational;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Scanner;

import org.testng.annotations.Test;

public class InputOutput {

	@Test
	public void canReadFromFileUsingFileReader() throws Exception {

		File file = getFileFromResources();

		FileReader reader = null;
		try {
			reader = new FileReader(file.getPath());
			int line;
			while((line = reader.read()) != -1) {
				System.out.print((char) line);	
			}
		}	
		finally {
			if(reader != null) {
				reader.close();
			}
		}
	}

	@Test
	public void canReadFromFileUsingScanner() {

		File file = getFileFromResources();

		Scanner scanner = null;
		try {
			scanner = new Scanner(file);

			int lineNumber = 1;
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println("line " + lineNumber++ + " :" + line);
			}   
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			scanner.close();
		}
	}

	@Test 
	public void canReadFromFileUsingBufferedReader() throws IOException {
		File file = getFileFromResources();
		FileReader reader = null;
		BufferedReader bufferedReader = null;

		try {
			reader = new FileReader(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedReader = new BufferedReader(reader);

			StringBuilder stringBuilder = new StringBuilder();
			String line = bufferedReader.readLine();

			while (line != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.getProperty("line.separator"));

				line = bufferedReader.readLine();
			}

			System.out.print(stringBuilder);

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(bufferedReader != null) {
				bufferedReader.close();
			}

			if(reader != null) {
				reader.close();
			}
		}
	}

	@Test 
	public void canWriteToFromFileUsingBufferedWriter() throws IOException {

		long epochTimeStamp = Instant.now().toEpochMilli();
		File file = new File(epochTimeStamp + "logFile.txt");

		if (!file.exists()) {
			file.createNewFile();
		}

		String fileContent = "This is some file content";

		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(fileContent);

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(bufferedWriter != null) {
				bufferedWriter.close();
			}

			if(fileWriter != null) {
				fileWriter.close();
			}
		}
	}

	private File getFileFromResources() {

		URL url = ClassLoader.getSystemResource("FileWithLines.txt");
		String file = url.getFile();

		if (file == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			return new File(file);
		}
	}
}
