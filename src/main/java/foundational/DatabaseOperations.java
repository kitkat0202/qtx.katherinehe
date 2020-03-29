package foundational;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DatabaseOperations {
	String url = "jdbc:mysql://127.0.0.1:3306/sakila";
	String userId = "root";
	String password = "$kipBeat456";
	Connection connection = null;

	@Test
	public void canConnectToDatabase() throws SQLException {
		try {
			connection = DriverManager.getConnection(url, userId, password);

			if (connection == null) {
				throw new RuntimeException((new Exception("Failed to make connection!")));
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(new Exception(String.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage())));
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		} 
		finally {
			if(connection != null ) {
				connection.close();
			}
		}
	}
	
	@Test
	public void canExecuteUsingTheStatementInterface() throws SQLException {

		double expectedPaymentAmount = 5.99;
		double actualPaymentAmount = 0;
		String sql = "SELECT amount FROM sakila.payment WHERE rental_id = 78";

	    try {
	    	connection = getDatabaseConnection();
	    	
	    	Statement statement = connection.createStatement();
	    	
	    	ResultSet resultSet = statement.executeQuery(sql);
	    	
	    	resultSet.next();
	    	
	    	actualPaymentAmount = resultSet.getDouble("amount");
	    }
		catch (Exception e) {
			throw new RuntimeException(e);
		} 
		finally {
			if(connection != null ) {
				connection.close();
			}
		}
	    
	    Assert.assertEquals(actualPaymentAmount, expectedPaymentAmount);
	}

	@Test
	public void canExecutePreparedStatement() throws SQLException {
		
		double expectedPaymentAmount = 5.99;
		double actualPaymentAmount = 0;
		String sql = "SELECT amount FROM sakila.payment WHERE rental_id = ?";

	    try {
	    	connection = getDatabaseConnection();
	    	
	    	PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setInt(1, 78);
	    		    	
	    	ResultSet resultSet = preparedStatement.executeQuery();
	    	
	    	resultSet.next();
	    	
	    	actualPaymentAmount = resultSet.getDouble("amount");
	    }
		catch (Exception e) {
			throw new RuntimeException(e);
		} 
		finally {
			if(connection != null ) {
				connection.close();
			}
		}
	    
	    Assert.assertEquals(actualPaymentAmount, expectedPaymentAmount);
	}
	
	@Test
	public void canUpdateRowsInResultSetObjects() throws SQLException {
		
		double actualPaymentAmount = 0;
		double updatedActualPaymentAmount = 0;
		String sql = "SELECT * FROM sakila.payment WHERE rental_id = ?";

	    try {
	    	connection = getDatabaseConnection();
	    	
	    	PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    	preparedStatement.setInt(1, 76);
	    		    	
	    	ResultSet resultSet = preparedStatement.executeQuery();
	    	
	    	resultSet.next();
	    	
	    	actualPaymentAmount = resultSet.getDouble("amount");

	    	resultSet.updateDouble("amount", actualPaymentAmount + 1);
	    	
	    	resultSet.updateRow();
	    	
	    	updatedActualPaymentAmount = resultSet.getDouble("amount");    	
	    }
		catch (Exception e) {
			throw new RuntimeException(e);
		} 
		finally {
			if(connection != null ) {
				connection.close();
			}
		}
	    
	    Assert.assertEquals(updatedActualPaymentAmount, actualPaymentAmount + 1, "the recordset value should be updated.");
	}
	
	private Connection getDatabaseConnection() throws Exception {
		connection = DriverManager.getConnection(url, userId, password);

		if (connection == null) {
			throw new Exception("Failed to make connection!");
		}	

		return connection;
	}
}
