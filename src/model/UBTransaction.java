package model;

import java.sql.SQLException;

public class UBTransaction extends DBConnect {
	// default constructor
	public UBTransaction() {
		
	}
	
	// parameterized constructor
	public UBTransaction(int user_id, int blog_id){
		this.createTransaction(user_id, blog_id);
	}
	// this method will create a transaction between a user and a blog
	public boolean createTransaction(int user_id, int blog_id){
		String sql = "INSERT INTO \"ub_transactions\" (\"user_id\", \"blog_id\") VALUES (?,?);";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, user_id);
			stmt.setInt(2, blog_id);
			int flag = stmt.executeUpdate();
			
			if (flag == 1){
				return true;
			} else{
				return false;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main (String [] args){
		
	}
}
