package foundational;

import org.testng.annotations.*;

public class Data {
	
	@DataProvider(name = "credentialsProvider")
	public Object[][] getData() {
		Object[][] data = new Object[3][2];
		data[0][0] = "username1@gmail.com";
		data[0][1] = "pssword1";
		data[1][0] = "username3@gmail.com";
		data[1][1] = "password3";
		data[2][0] = "test@gmail.com";
		data[2][1] = "Testing";
		
		return data;
	}

	@Test(dataProvider = "credentialsProvider")
	public void canReadFromDataProvider(String userName, String password) {
		System.out.println(userName + "/" + password + "/");
	}
}
