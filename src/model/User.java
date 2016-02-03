package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import util.Encryption;

public class User extends DBConnect {
	// Global variable to hold user session data
	public static int user_id; 
	public static String first_name;
	public static String last_name;
	public static String username;
	public static boolean isLogged = false;
	
	// This method will create a new entry for user in the database
	// Note: Password must be encrypted already
	public boolean createUser(String username, String password, String firstName, String lastName, int status){
		String sql = "INSERT INTO \"users\" (\"username\", \"password\", \"first_name\", \"last_name\", \"created_at\", STATUS)"
				+ " VALUES (?,?,?,?,SYSDATE, ?)";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, firstName);
			stmt.setString(4, lastName);
			stmt.setInt(5, status);
			
			int flag = stmt.executeUpdate();
			
			if (flag == 1){
				System.out.println("Thank you for registering with us!");
				return true;
			}else{
				System.out.println("We don't like you!");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("There was some problem saving data into the database. Try Again!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}
		return false;
	}
	
	/*
	 *|-----------------------------------------------------------------------------------|
	 *| All the update methods are defined in this section
	 *|-----------------------------------------------------------------------------------|
	 */
	
	// This method will be used to update users by its primary key Id.
	public void updateUser(int userId, String firstName, String lastName){
		String sql = "UPDATE \"users\" SET \"first_name\"=?, \"last_name\"=? WHERE \"id\"=?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setInt(3, userId);
			int flag = stmt.executeUpdate();
			
			if (flag == 1){
				System.out.println("User has been updated successfully!");
			}else{
				System.out.println("No need to update!");
			}
		} catch (SQLException e) {
			System.out.println("There was some problem saving data into the database. Try Again!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}
	}
	
	// update user's first name
	public void updateUserFirstName(int userId, String firstName){
		String sql = "UPDATE \"users\" SET \"first_name\"=? WHERE \"id\"=?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setInt(2, userId);
			int flag = stmt.executeUpdate();
			
			if (flag == 1){
				System.out.println("User First Name has been updated successfully!");
			}else{
				System.out.println("No need to update!");
			}
		} catch (SQLException e) {
			System.out.println("There was some problem saving data into the database. Try Again!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}
	}
	
	// update user's last name
	public void updateUserLastName(int userId, String lastName){
		String sql = "UPDATE \"users\" SET \"last_name\" =? WHERE \"id\"=?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, lastName);
			stmt.setInt(2, userId);
			int flag = stmt.executeUpdate();
			
			if (flag == 1){
				System.out.println("User's Last Name has been updated successfully!");
			}else{
				System.out.println("No need to update!");
			}
		} catch (SQLException e) {
			System.out.println("There was some problem saving data into the database. Try Again!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}
	}
	
	// update user's password (password must be encrypted already)
	public void updateUserPassword(int userId, String password){
		String sql = "UPDATE \"users\" SET \"password\"=? WHERE \"id\"=?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, password);
			stmt.setInt(2, userId);
			int flag = stmt.executeUpdate();
			
			if (flag == 1){
				System.out.println("User Password has been updated to "+password+" successfully!");
			}else{
				System.out.println("No need to update!");
			}
		} catch (SQLException e) {
			System.out.println("There was some problem saving data into the database. Try Again!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}
	}
	
	/*
	 *|-----------------------------------------------------------------------------------|
	 *| All the delete methods are defined in this section
	 *|-----------------------------------------------------------------------------------|
	 */
	
	// This method will be used to delete users by its primary key Id.
	public void deleteUser(int userId){
		String sql = "DELETE FROM \"users\" WHERE \"id\"=?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			int flag = stmt.executeUpdate();
			
			if (flag == 1){
				System.out.println("User with id: "+userId+" has been delete successfully!");
			}else{
				System.out.println("We won't let you go!");
			}
		} catch (SQLException e) {
			System.out.println("User delete Successfully!");
//			 e.printStackTrace();
		} catch (Exception e){
			
		}
		
	}
	
	/*
	 *|-----------------------------------------------------------------------------------|
	 *| All the select methods are defined in this section
	 *|-----------------------------------------------------------------------------------|
	 */
	
	// returns all users in the database
	public ResultSet getAllUsers(){
		ResultSet rs = null;
		String sql = "SELECT * FROM \"users\"";
		
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("There were no user in the database!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}
		
		return rs;
	}
	
	// returns a particular user from database by its id
	public ResultSet getUserById(int userId){
		ResultSet rs = null;
		String sql = "SELECT * FROM \"users\" WHERE \"id\" = ?";
		
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("No user present by this User Id!");
//			e.printStackTrace();
		} catch(Exception e){
			
		}
		return rs;
	}
	
	// returns all the user with provided name
	public ResultSet getUserByName(String name){
		ResultSet rs = null;
		String sql = "SELECT * FROM \"users\" WHERE \"first_name\" LIKE '?%' OR last_name LIKE '?%'";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("No User found with this name");
//			e.printStackTrace();
		}
		return rs;
	}
	
	// check username against database
	public boolean checkDuplicateUsername(String username){
		String sql = "SELECT * FROM \"users\" WHERE \"username\"=?";		
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username.toString());
			ResultSet rs = stmt.executeQuery();
			int rowCount = 0;
			
			// if username is present increment counter
			while (rs.next()){
				rowCount++;
			}
			
			// if username present return true
			if (rowCount == 1){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Please provide a correct username!");
//			e.printStackTrace();
		}
		
		return false;
	}
	
	// it will authenticate user against database
	public boolean auth(String username, String password){
		boolean flag = this.checkDuplicateUsername(username); // returns true if username exists
		password = Encryption.cryptWithMD5(password);
		if (flag){
			String sql = "SELECT * FROM \"users\" WHERE \"username\"=? AND \"password\"=? AND STATUS=1";
			try {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, username);
				stmt.setString(2, password);
				
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next()){
					User.user_id = rs.getInt("id");
					User.first_name = rs.getString("first_name");
					User.last_name = rs.getString("last_name");
					User.username = username;
					User.isLogged = true;
					
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("Username does not exists! Please register first!");
		}
		
		return false;
	}
	
}
