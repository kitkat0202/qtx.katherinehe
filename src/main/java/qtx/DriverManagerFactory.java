package qtx;

public class DriverManagerFactory {
	public static DriverManager getManager(DriverType type) {
		
		switch(type) {
		case CHROME:
			return new ChromeDriverManager();
		default:
			System.out.printf("We do not support this driver: %s", type);
		}
		return null;
	}
}
