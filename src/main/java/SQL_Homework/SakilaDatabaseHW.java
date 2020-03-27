package SQL_Homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import SQL_Homework.devKeys;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SakilaDatabaseHW {
	int PORT = new devKeys().getMySQLPort();
	String url = "jdbc:mysql://127.0.0.1:" + PORT +"/sakila";
	String userId = new devKeys().getMySQLUsername();
	String password = new devKeys().getMySQLPassword();
	Connection connection = null;

//	@Test
	public void canConnectToDatabase() throws SQLException {
		try {
			connection = DriverManager.getConnection(url, userId, password);

			if (connection == null) {
				throw new RuntimeException((new Exception("Failed to make connection!")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(new Exception(String.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage())));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(connection != null ) {
				connection.close();
			}
		}
	}
	
	// 2. Get all films from the films table where the title contains "airplane".
//	@Test
	public void canGetFilmTitlesThatIncludesAirplane() throws SQLException {

		List<String> expectedTitles = Arrays.asList("AIRPLANE SIERRA", "RAGING AIRPLANE");
		List<String> actualTitles = new ArrayList<String>();
		String wordMatch = "airplane";
		
		String sql = "SELECT title FROM sakila.film WHERE title LIKE ?";

	    try {
	    	connection = getDatabaseConnection();
	    	
	    	PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setString(1, "'%" + wordMatch + "%'");;
	    		    	
	    	ResultSet resultSet = preparedStatement.executeQuery();
	    	
	    	while (resultSet.next()) {
	    		actualTitles.add(resultSet.getString("title"));
	    	}
	    } catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(connection != null ) {
				connection.close();
			}
		}
	    
	    for(int i = 0; i < actualTitles.size(); i++) {
			Assert.assertEquals(actualTitles.get(i), expectedTitles.get(i), "Incorrect Title at index: " + i);
		}
	}
	
	// 7. Get the 4 inventory ids for the film "Alien Center" at Store #2. Hint: Use variables & Stored Procedures.
//	@Test
	public void canGetInvitoryWhereFilmIsAlienCenter() throws SQLException {

		List<Integer> expectedInventory_id = Arrays.asList(73, 74, 75, 76);
		List<Integer> actualInventory_id = new ArrayList<Integer>();
		String wordMatch = "Alien Center";
		
		int film_id = 0;
		
		String sql1 = "SELECT film_id FROM film WHERE title = ?";
		String sql2 = "CALL film_in_stock( ? ,2,@count)";

	    try {
	    	connection = getDatabaseConnection();
	    	
	    	// Getting the Film ID
	    	PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
	    	preparedStatement1.setString(1, wordMatch);
	    		    	
	    	ResultSet resultSet1 = preparedStatement1.executeQuery();
	    	
	    	resultSet1.next();
	    	
	    	film_id = resultSet1.getInt("film_id");
	    	
	    	// Getting the Inventory ID's
	    	PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
	    	preparedStatement2.setInt(1, film_id);
	    	
	    	ResultSet resultSet2 = preparedStatement2.executeQuery();
	    	
	    	while (resultSet2.next()) {
	    		actualInventory_id.add(resultSet2.getInt("inventory_id"));
	    	}
	    	
	    } catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(connection != null ) {
				connection.close();
			}
		}
	    for(int i = 0; i < actualInventory_id.size(); i++) {
			Assert.assertEquals(actualInventory_id.get(i), expectedInventory_id.get(i), "Incorrect Invetory ID at index: " + i);
		}
	}
	
	//8. Insert a new store in the store table. Ensure that you use TRANSACTION.
//	@Test
	public void canCreateNewStoreAndUser() throws SQLException {
		
		PreparedStatement insertStaff = null;
	    PreparedStatement insertStore = null;
	    PreparedStatement updateStaff = null;
	    PreparedStatement selectStaff = null;
	    PreparedStatement selectStore = null;
	    
	    int Jim_id = 0;
	    int store_id = 0;
	    String actualName = "";
	    int actualManagerID = 0;
	    
	    String sql1 = "INSERT INTO sakila.staff(first_name, last_name, address_id, email, store_id, username) VALUES('Jim', 'Halpert', 27, 'Jim.Halpert@DunderMiflan.com', 1, 'JimH')";
	    String sql2 = "INSERT INTO sakila.store(manager_staff_id, address_id) VALUE( ? , 7)";
	    String sql3 = "UPDATE sakila.staff SET store_id = ? WHERE staff_id = ?";
	    String sql4 = "SELECT * FROM sakila.staff WHERE staff_id = ?";
	    String sql5 = "SELECT * FROM sakila.store WHERE store_id = ?";
	    
	    try {
	    	connection = getDatabaseConnection();
	    	connection.setAutoCommit(false);
	    	
	    	// Enter New Staff
	    	insertStaff = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
	    	insertStaff.executeUpdate();
	    	
	    	ResultSet staffkeys = insertStaff.getGeneratedKeys();
	    	staffkeys.next();
	    	Jim_id = staffkeys.getInt(1);
	    	
	    	if (Jim_id == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
	    	
	    	
	    	// Enter New Store
	    	insertStore = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
	    	insertStore.setInt(1, Jim_id);
	    	insertStore.executeUpdate();
	    	
	    	ResultSet storekeys = insertStore.getGeneratedKeys();
	    	storekeys.next();
	    	store_id = storekeys.getInt(1);
	    	
	    	if (store_id == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
	    	
	    	
	    	// Update Jim Store
	    	updateStaff = connection.prepareStatement(sql3);
	    	updateStaff.setInt(1, store_id);
	    	updateStaff.setInt(2, Jim_id);
	    	updateStaff.executeUpdate();
	    	
	    	// Check Staff before committing
	    	selectStaff = connection.prepareStatement(sql4);
	    	selectStaff.setInt(1, Jim_id);
	    		    	
	    	ResultSet resultName = selectStaff.executeQuery();
	    	
	    	resultName.next();
	    	
	    	actualName = resultName.getString("first_name");
	    	
	    	
	    	// Check Store before committing
	    	selectStore = connection.prepareStatement(sql5);
	    	selectStore.setInt(1, store_id);
	    		    	
	    	ResultSet resultManagerid = selectStore.executeQuery();
	    	
	    	resultManagerid.next();
	    	
	    	actualManagerID = resultManagerid.getInt("manager_staff_id");

	    	
	    	if(actualName.equals("Jim") && Integer.compare(actualManagerID, Jim_id) == 0) {
	    		connection.commit();
	    		connection.setAutoCommit(true);
	    	} else {
	    		connection.rollback();
	    	}
	    	
	    } catch (Exception e) {
	    	System.out.println("Error occured: " + e);
	    	connection.rollback();
	    	connection.setAutoCommit(true);
	    } finally {
			if(connection != null ) {
				connection.close();
			}
		}
	    
	    Assert.assertEquals(actualName, "Jim", "The Name is incorrect");
	    Assert.assertEquals(actualManagerID, Jim_id, "The ID is incorrect");
	    
	}
	// 9. Update the timestamp (last_update) to the current date/time for the new store you entered in the previous question. 
//	@Test
	public void canUpdateDateOnLastStore() throws SQLException {
		
		String sql = "SELECT * FROM sakila.store WHERE store_id = (SELECT MAX(store_id) FROM sakila.store)";
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
		
		Timestamp updatedTimestamp = null;

	    try {
	    	connection = getDatabaseConnection();
	    	
	    	PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    		    	
	    	ResultSet resultStoreID = preparedStatement.executeQuery();
	    	
	    	resultStoreID.next();
	    	
	    	resultStoreID.updateTimestamp("last_update", currentTimestamp);
	    	
	    	resultStoreID.updateRow();
	    	
	    	updatedTimestamp = resultStoreID.getTimestamp("last_update");
	    	
	    } catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(connection != null ) {
				connection.close();
			}
		}

		Assert.assertEquals(updatedTimestamp, currentTimestamp, "Timestamp update unsuccessful");
	}
	// 10. Delete the new store from the store table.
	@Test
	public void canDeleteStoreRow() throws SQLException {
		
		String getStaff_sql = "SELECT * FROM sakila.staff WHERE staff_id = (SELECT MAX(staff_id) FROM sakila.staff)";
		String getStore_sql = "SELECT MAX(store_id) FROM sakila.store";

		String deleteStaff_sql = "DELETE FROM sakila.staff WHERE staff_id = ?";
		String deleteStore_sql = "DELETE FROM sakila.store WHERE store_id = ?";
		
		String countStaff = "SELECT COUNT(staff_id) FROM sakila.staff";
		String countStore = "SELECT COUNT(store_id) FROM sakila.store";
		
		int staffID = 0;
		int storeID = 0;
		int actualNumOfStaff = 0;
		int actualNumOfStore = 0;
		
	    try {
	    	connection = getDatabaseConnection();
	    	// Get Expected Elements And Update
	    	PreparedStatement getStaffStatement = connection.prepareStatement(getStaff_sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    	ResultSet resultGetStaff = getStaffStatement.executeQuery();
	    	resultGetStaff.next();
	    	resultGetStaff.updateInt("store_id", 1);
	    	resultGetStaff.updateRow();
	    	staffID = resultGetStaff.getInt("staff_id");
	    	
	    	Statement getStoreStatement = connection.createStatement();
	    	ResultSet resultGetStore = getStoreStatement.executeQuery(getStore_sql);
	    	resultGetStore.next();
	    	storeID = resultGetStore.getInt(1);
	    	
	    	
	    	// Delete Elements
	    	PreparedStatement removeStoreStatement = connection.prepareStatement(deleteStore_sql);
	    	removeStoreStatement.setInt(1, storeID);
	    	removeStoreStatement.executeUpdate();
	    	
	    	PreparedStatement removeStaffStatement = connection.prepareStatement(deleteStaff_sql);
	    	removeStaffStatement.setInt(1, staffID);
	    	removeStaffStatement.executeUpdate();
	    	
	    	// Count Rows
	    	Statement countStaffStatement = connection.createStatement();
	    	ResultSet countStaffResultSet = countStaffStatement.executeQuery(countStaff);
	    	countStaffResultSet.next();
	    	actualNumOfStaff = countStaffResultSet.getInt(1);

	    	Statement countStoreStatement = connection.createStatement();
	    	ResultSet countStoreResultSet = countStoreStatement.executeQuery(countStore);
	    	countStoreResultSet.next();
	    	actualNumOfStore = countStoreResultSet.getInt(1);

	    }
		catch (Exception e) {
			throw new RuntimeException(e);
		} 
		finally {
			if(connection != null ) {
				connection.close();
			}
		}
	    
	    Assert.assertEquals(actualNumOfStaff, 2, "the staff record was not deleted");
	    Assert.assertEquals(actualNumOfStore, 2, "the staff record was not deleted");
	}
	
	private Connection getDatabaseConnection() throws Exception {
		connection = DriverManager.getConnection(url, userId, password);

		if (connection == null) {
			throw new Exception("Failed to make connection!");
		}	

		return connection;
	}
}
