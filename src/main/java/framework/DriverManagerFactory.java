package framework;

public class DriverManagerFactory {

	public static DriverManager getManager(DriverTypes driverType) throws Exception {
		
		DriverManager driverManager;
		
		switch (driverType) {
		case CHROME:
			driverManager = new ChromeDriverManager();
			break;
		default:
			throw new Exception("The requested driver type is not supported.");
		}	
		
		return driverManager;
	}
}
