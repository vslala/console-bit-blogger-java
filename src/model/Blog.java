/*
 * Author: Varun Shrivastava
 * Description: This class is responsible for all the database operations related to the Blog.
 * Operations such as-
 * -Create
 * -Update
 * -Read
 * -Delete
 */

package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Blog extends DBConnect{

	/*
	 * This method is responsible for creating a blog in the database.
	 */
	public void createBlog(int author, String title, String summary, String content, int categoryId){
		try {
			stmt = con.prepareStatement("INSERT INTO \"blogs\" (\"author\", \"title\", \"summary\", \"content\", \"category_id\", \"created_at\")"
					+ " VALUES (?, ?, ?, ?, ?, SYSDATE)", Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, author);
			stmt.setString(2, title);
			stmt.setString(3, summary);
			stmt.setString(4, content);
			stmt.setInt(5, categoryId);

			stmt.executeUpdate();
			boolean flag = true;
			//			UBTransaction ubt = new UBTransaction();
			//			boolean flag = ubt.createTransaction(User.user_id, lastInsertId);

			// if data has been stored in the transaction table
			if (flag){
				System.out.println("Blog has been created Successfully!");
			}else{
				System.out.println("There was some error saving the blog in the database");
			}
		} catch (SQLException e) {
			System.out.println("There was some problem saving data into the database. Try Again!");
			//			e.printStackTrace();
		} catch (Exception e){

		}
	}

	/*
	 *|-----------------------------------------------------------------------------------|
	 *| All the update methods are defined in this section
	 *|-----------------------------------------------------------------------------------|
	 */

	// This method updates the values of a particular blog with the help of the blogs primary key id; 
	public void updateBlog(int blogId, int author, String title, String summary, String content){
		try {
			stmt = con.prepareStatement("UPDATE \"blogs\" SET \"author\"=?, \"title\"=?, \"summary\"=?, \"content\"=?, \"created_at\"=SYSDATE"
					+ "WHERE \"id\"=?");
			stmt.setInt(1, author);
			stmt.setString(2, title);
			stmt.setString(3, summary);
			stmt.setString(4, content);
			stmt.setInt(5, blogId);

			int flag = stmt.executeUpdate();

			if (flag == 1){
				System.out.println("Blog has been updated successfully!");
			}else{
				System.out.println("There was some error while updating blog in database!");
			}
		} catch (SQLException e) {
			System.out.println("There was some problem saving data into the database. Try Again!");
			//			e.printStackTrace();
		} catch (Exception e){

		}
	}

	// update blog author
	public void updateBlogAuthor(int blogId, int author){
		String sql = "UPDATE \"blogs\" SET \"author\"=? WHERE \"id\"=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, author);
			stmt.setInt(2, blogId);
			int flag = stmt.executeUpdate();

			if (flag == 1){
				System.out.println("Author name of the blog with id: "+ blogId + " has been updated successfully!" );
			}else{
				System.out.println("We can't update author name at this moment!");
			}
		} catch (SQLException e) {
			System.out.println("There was some problem saving data into the database. Try Again!");
			//			e.printStackTrace();
		} catch (Exception e){

		}
	}

	// update blog title
	public void updateBlogTitle(int blogId, String title){
		String sql = "UPDATE \"blogs\" SET \"title\"=? WHERE \"id\"=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setInt(2, blogId);
			int flag = stmt.executeUpdate();

			if (flag == 1){
				System.out.println("Blog Title of the blog with id: "+ blogId + " has been updated successfully!" );
			}else{
				System.out.println("We can't update Blog Title at this moment!");
			}
		} catch (SQLException e) {
			System.out.println("There was some problem saving data into the database. Try Again!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}
	}

	// update blog summary
	public void updateBlogSummary(int blogId, String summary){
		String sql = "UPDATE \"blogs\" SET \"summary\"=? WHERE \"id\"=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, summary);
			stmt.setInt(2, blogId);
			int flag = stmt.executeUpdate();

			if (flag == 1){
				System.out.println("Blog Summary of the blog with id: "+ blogId + " has been updated successfully!" );
			}else{
				System.out.println("We can't update Blog Summary at this moment!");
			}
		} catch (SQLException e) {
			System.out.println("There was some problem saving data into the database. Try Again!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}
	}

	// update blog content
	public void updateBlogContent(int blogId, String content){
		String sql = "UPDATE \"blogs\" SET \"content\"=? WHERE \"id\"=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, content);
			stmt.setInt(2, blogId);
			int flag = stmt.executeUpdate();

			if (flag == 1){
				System.out.println("Blog Content of the blog with id: "+ blogId + " has been updated successfully!" );
			}else{
				System.out.println("We can't update Blog Content at this moment! Blog Id provided does not exist!");
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

	/*
	 * This method delete a particular blog with the help of blog's primary key id;
	 */
	public void deleteBlog(int blogId){
		try {
			stmt = con.prepareStatement("DELETE FROM \"blogs\" WHERE \"id\"=?");
			stmt.setInt(1, blogId);

			int flag = stmt.executeUpdate();

			if (flag == 1){
				System.out.println("Blog with id "+blogId+" has been delete successfully!");
			}else{
				System.out.println("There was some error while trying to delete this blog in database!");
			}
		} catch (SQLException e) {
			System.out.println("There was some problem deleting data from the database. Try Again!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}
	}

	/*
	 *|-----------------------------------------------------------------------------------|
	 *| All the select/get methods are defined in this section
	 *|-----------------------------------------------------------------------------------|
	 */


	// This method will return all the rows from the blogs table 
	public ResultSet getAllBlogs(){
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT \"blogs\".*, \"users\".\"first_name\", \"users\".\"last_name\" FROM \"blogs\" "
					+ "LEFT JOIN \"users\" "
					+ "ON \"users\".\"id\"=\"blogs\".\"author\" "
					+ "ORDER BY \"blogs\".\"id\" ASC");
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("There was some problem fetching data from the database. Try Again!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}
		return rs;

	}

	// return blog by its id
	public ResultSet getBlogById(int blogId){
		ResultSet rs = null;
		String sql = "SELECT \"blogs\".*, \"users\".\"first_name\", \"users\".\"last_name\","
				+ " \"categories\".\"name\" "
				+ " FROM \"blogs\" LEFT JOIN \"users\" "
				+ "ON \"users\".\"id\" = \"blogs\".\"author\" "
				+ "LEFT JOIN \"categories\" "
				+ "ON \"blogs\".\"category_id\" = \"categories\".\"id\" "
				+ " WHERE \"blogs\".\"id\" = ?"
				+ "ORDER BY \"blogs\".\"id\" ASC";
		try{
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, blogId);
			rs = stmt.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}

	// This method will return all the rows of a particular blog by its Author
	public ResultSet getBlogByAuthor(String author){
		ResultSet rs = null;
		String sql = "SELECT * FROM \"blogs\" "
				+ "LEFT JOIN \"users\" "
				+ "ON \"blogs\".\"author\" = \"users\".\"id\" "
				+ "WHERE \"users\".\"first_name\" LIKE '%"+author+"%' OR \"users\".\"last_name\" LIKE '%"+author+"%'";
		try{
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
		}catch (SQLException e){
			System.out.println("Problem getting the blogs by author");
			e.printStackTrace();
		}catch(Exception e){
			
		}
		return rs;
	}

	// fetch all blogs by user_id
	public ResultSet getAllBlogByUserId(){
		ResultSet rs = null;
		String sql = "SELECT \"blogs\".*, \"users\".\"username\" FROM \"blogs\" "
				+ "LEFT JOIN \"users\" "
				+ "ON \"users\".\"id\" = \"blogs\".\"author\""
				+ "WHERE \"users\".\"id\" = ? "
				+ "ORDER BY \"blogs\".\"id\" ASC";

		try{
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, User.user_id);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("There was some problem fetching data from the database. Try Again!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}
		return rs;
	}

	// This method fetches all the blogs by author name
	public ResultSet getAllBlogByAuthorName(String name){
		ResultSet rs = null;
		String sql = "SELECT * FROM \"blogs\" "
				+ "LEFT JOIN \"users\" "
				+ "ON \"users\".\"id\" = \"blogs\".\"author\""
				+ "WHERE \"users\".\"first_name\" LIKE '%?%' OR \"users\".\"last_name\" LIKE '%?%'"
				+ "ORDER BY \"blogs\".\"created_at\" ASC";

		try{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, name);
			rs = stmt.executeQuery();
		}catch (SQLException e){
			System.out.println("Problem fetching the blog by this author name");
//			e.printStackTrace();
		}catch (Exception e){
			
		}
		return rs;
	}

	// this method will pull all the blogs from a certain category
	public ResultSet getAllBlogsByCategoryId(int catId){
		ResultSet rs = null;
		String sql = "SELECT * FROM \"blogs\" WHERE \"category_id\" = ?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, catId);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("Problem fetching data from the database!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}

		return rs;
	}

	// returns all blogs by category id
	public String getCategoryNameById(int catId){
		ResultSet rs = null;
		String categoryName = null;
		String sql = "SELECT \"name\" FROM \"categories\" WHERE \"id\" = ?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, catId);
			rs = stmt.executeQuery();
			if (rs.next()){
				categoryName = rs.getString("name");
			}

		} catch (SQLException e) {
			System.out.println("Problem fetching data from the database!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}

		return categoryName;
	}

	// returns all blogs that matches the keyword
	public ResultSet getBlogsByKeyword(String term){
		ResultSet rs = null;

		String sql = "SELECT * FROM \"blogs\" "
				+ "WHERE \"blogs\".\"title\" "
				+ "LIKE INITCAP('%"+term+"%') "
				+ "OR \"blogs\".\"title\" "
				+ "LIKE LOWER('%"+term+"%')";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("Problem fetching data from the database!");
//			e.printStackTrace();
		}catch (Exception e){
			
		}

		return rs;
	}

	/*
	 *|-----------------------------------------------------------------------
	 *| Fetching data from categories
	 *|-----------------------------------------------------------------------
	 */
	public ResultSet getAllCategories(){
		ResultSet rs = null;
		String sql = "SELECT * FROM \"categories\"";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("There was some error fetching categories from the database!");
//			e.printStackTrace();
		} catch (Exception e){

		}
		return rs;
	}

}

