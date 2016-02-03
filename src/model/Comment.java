package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Comment extends DBConnect{
	
	/*
	 * ------------------------------------------------------------------------------------
	 * INSERT INTO COMMENTS TABLE QUERIES
	 * ------------------------------------------------------------------------------------
	 */
	public void addComment(int blogId, int userId, String comment){
		String sql = "INSERT INTO \"comments\" (\"user_id\", \"blog_id\", \"comments\", \"created_at\") "
				+ "VALUES (?,?,?, SYSDATE)";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setInt(2, blogId);
			stmt.setString(3, comment);
			
			int flag = stmt.executeUpdate();
			
			if (flag == 1){
				System.out.println("Comment has been added to the blog.");
			}else{
				System.out.println("There was some error saving your comment!");
			}
		} catch (SQLException e) {
			System.out.println("Problem saving data into the database!");
//			e.printStackTrace();
		} catch(Exception e){
//			e.printStackTrace();
		}
	}
	
	public ResultSet getCommentsByBlogId(int blogId){
		String sql = "SELECT \"comments\".\"comments\", \"users\".\"first_name\", \"users\".\"last_name\", \"users\".\"username\" "
				+ " FROM \"comments\" "
				+ "LEFT JOIN \"users\""
				+ "ON \"users\".\"id\" = \"comments\".\"user_id\" "
				+ "WHERE \"comments\".\"blog_id\" = ? ";
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, blogId);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			
		}
		return rs;
	}
}
