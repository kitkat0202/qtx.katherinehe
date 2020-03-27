package SQL_Homework;

// To use the devkeys:
// Rename this file to "devKeys.java"
// enter in your MySQL - Username, - Password, - Port #

// This file has already been added t gitignore.

public class sample_devKeys {
	private String MySQLUsername = ""; // most possible "root"
	private String MySQLPassword = "";
	private int MySQLPort; // most posibly 3306

	
	public String getMySQLUsername() {
		return MySQLUsername;
	}
	
	public String getMySQLPassword() {
		return MySQLPassword;
	}
	
	public int getMySQLPort() {
		return MySQLPort;
	}
}
