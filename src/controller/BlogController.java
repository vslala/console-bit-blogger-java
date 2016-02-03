package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Blog;
import model.User;
import util.Utility;
import util.Validation;

public class BlogController extends Blog{
	// data members which will be used to temporarily store data for blogs	private static int _author;
	private String _title;
	private String _summary;
	private String _content;
	private int _categoryId;
	public static int temp_id;

	Scanner in = new Scanner(System.in);
	private Scanner input;
	private Scanner input2;
	private Scanner input3;

	// default constructor
	public BlogController(){

	}

	// sets the data temporarily to this class data members
	public void setBlogData(String title, String summary, String content){
		this._title = title;
		this._summary = summary;
		this._content = content;
	}

	/*
	 *| -----------------------------------------------------------------------------------
	 *| Input Functions and Corresponding Display
	 *| ----------------------------------------------------------------------------------- 
	 */
	// This method will take input from the user and set the temp variables
	public void getData(){

		while (true){
			System.out.println("Enter title for the blog (In a single line): ");
			String title = in.nextLine();

			if (Validation.alphabetsOnly(title)){
				this._title = title;
				break;
			}else{
				System.out.println("Only alphabets are allowed!");
			}
		}

		Utility.instructions();
		System.out.println("Enter the summary for the blog");
		Utility.hr(50);
		this._summary = Utility.multilineInput();

		Utility.instructions();
		System.out.println("Start Writing your Blog:");
		Utility.hr(50);
		this._content = Utility.multilineInput();

		Utility.hr(50, "*");
		Utility.indent(20);
		System.out.println("Blog Categories");
		Utility.hr(50, "*");
		this.showAllCategories();
		String catId;
		while(true){
			try{
				catId = in.nextLine();
				if (! Validation.alphabetsOnly(catId)){
					int catId1 = Integer.parseInt(catId);
					if (catId1 < 11){
						this._categoryId = catId1;
						break;
					}else{
						System.out.println("There is no such category! Select a valid category from the list!");
					}
				} else{
					System.out.println("Enter Valid Input Choice From the list!");
				}
			}catch(NumberFormatException e){
				System.out.println("Select a valid category");
			}catch(InputMismatchException e){

			}catch(Exception e){

			}
		}

	}

	// Displays all the blogs by Author Name
	public void searchByAuthor(){
		input = new Scanner(System.in);
		ResultSet rs = null;
		System.out.print("Name of the Author: ");
		String author = input.nextLine();
		author = Utility.capitalizeFirstLetter(author);
		rs = super.getBlogByAuthor(author);
		this.showSearchResult(rs, "ALL BLOGS BY: " + author);
	}

	// Displays Blogs by Category Id
	public void searchByCategory(){
		ResultSet rs = null;
		input2 = new Scanner(System.in);

		this.showAllCategories();
		System.out.print("Type the Category Id: ");
		int catId = input2.nextInt();
		rs = super.getAllBlogsByCategoryId(catId);

		this.showSearchResult(rs, "ALL BLOGS BY "+super.getCategoryNameById(catId));	
	}

	// displays all blog which matches the given keyword/term
	public void searchByKeyword(){
		ResultSet rs = null;
		input3 = new Scanner(System.in);

		System.out.print("Type the Keyword: ");
		String term = input3.nextLine();
		rs = super.getBlogsByKeyword(term);
		this.showSearchResult(rs, "SHOWING ALL BLOGS WITH THE KEYWORD: " + term);	
	}

	public void saveBlog(){
		this.getData();
		Blog blog = new Blog();
		blog.createBlog(User.user_id, this._title, this._summary, this._content, this._categoryId);
	}

	/*
	 *| -----------------------------------------------------------------------------------------
	 *| Update Blog Controller Methods
	 *| -----------------------------------------------------------------------------------------
	 */

	// This function will update the title for a particular blog
	public void updateBlogByTitle(){
		String title;
		while(true){
			System.out.println("Enter the title for this blog!");
			title = in.nextLine();
			if (Validation.alphabetsOnly(title)){
				this._title = title;			
				super.updateBlogTitle(BlogController.temp_id, this._title);
				break;
			}else{
				System.out.println("Invalid Input, Please provide valid input!");
			}
		}
	}

	/// this function will update the summary for a particular blog
	public void updateBlogBySummary(){
		System.out.println("Enter the summary for this blog!");
		String summary = Utility.multilineInput();
		this._summary = summary;
		super.updateBlogSummary(BlogController.temp_id, this._summary);
	}

	// this function will update blog content
	public void updateBlogByContent(){
		System.out.println("Enter the content for this blog");
		this._content = Utility.multilineInput();
		super.updateBlogContent(BlogController.temp_id, this._content);
	}


	/*
	 *| ------------------------------------------------------------------------------------------
	 *| Display Functions Will reside here
	 *| ------------------------------------------------------------------------------------------ 
	 */

	// display the search result
	public void showSearchResult(ResultSet rs, String searchMessage){
		Utility.hr(50, "*");
		System.out.println(searchMessage);
		Utility.hr(50, "*");
		try {
			while (rs.next()){
				System.out.println("Blog Id: "+ rs.getInt("id"));
				Utility.explode(rs.getString("content"), "#");
				Utility.hr(50, "--");
			}
			Utility.hr(50, "**");
			Utility.vIndent(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// display all blogs
	public void showAllBlogs(){
		ResultSet rs = super.getAllBlogs();

		Utility.hr(50, "**");
		System.out.println("SHOWING ALL BLOGS...");
		Utility.hr(50, "**");
		try {
			while (rs.next()){
				System.out.println("Blog Id: "+ rs.getInt("id"));
				Utility.explode(rs.getString("summary"), "#");
				System.out.println("Author: " + rs.getString("first_name") + " "+ rs.getString("last_name"));
				System.out.println("Created At: " + rs.getString("created_at"));
				Utility.hr(50, "--");
				Utility.vIndent(3);
			}
			Utility.hr(50, "**");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// display all categories
	public void showAllCategories(){
		ResultSet rs = super.getAllCategories();

		try {
			while (rs.next()){
				Utility.indent(10);
				System.out.println(rs.getInt("id")+ " "+rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// display all blogs of a particular author by author Id
	public int showBlogsByAuthorId() throws SQLException{
		ResultSet rs = super.getAllBlogByUserId();
		ResultSet temp = super.getAllBlogByUserId();
		
		if (! temp.next()){
			Utility.showBoxMessage("   NO BLOGS FOUND!  ");
			return 0;
		}
			
			
		Utility.hr(50, "*");
		Utility.indent(20);
		System.out.println("SHOWING ALL BLOGS");
		Utility.hr(50, "*");
		
		try {
			while (rs.next()){
				System.out.println("Blog ID: "+ rs.getInt("id"));
				System.out.println("Blog ID: "+ rs.getInt("author"));
				System.out.println("Title: " + rs.getString("title"));
				Utility.explode("Summary: "+rs.getString("summary"), "#");
			}
			Utility.hr(50, "--");
			Utility.vIndent(2);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return 1;
	}


	// this will show blog by its id
	public void showSingleBlog(int blogId){
		model.Comment comment = new model.Comment();
		ResultSet rs = null, comments = null;	

		rs = super.getBlogById(blogId);
		comments = comment.getCommentsByBlogId(blogId);
		
		Utility.vIndent(2);

		try {
			while (rs.next()){
				Utility.hr(50, "#");
				System.out.println(rs.getString("title"));
				Utility.hr(50, "#");
				System.out.println("By: " + rs.getString("first_name")+ " " + rs.getString("last_name"));
				System.out.println("Created At: " + rs.getString("created_at"));
				System.out.println("Category: " + rs.getString("name"));
				Utility.vIndent(1);
				System.out.print("<blog>\n");Utility.explode(rs.getString("content"), "#");System.out.println("</blog>");
				Utility.hr(50, "--");
				while (comments.next()){
					System.out.println("<comment>");
					System.out.println("Username: "+comments.getString("username"));
					Utility.explode(comments.getString("comments"), "#");
					System.out.println("</comment>");
				}
				Utility.hr(50, "/\\");
				Utility.vIndent(1);
			}
		} catch (SQLException e) {
			System.out.println("There was some problem fetching the blog from the database!");
//			e.printStackTrace();
		} catch (Exception e){
			
		}
	}
	
	public int selectBlogIdDisplay(){
		int blogId = 0;
		System.out.println("Enter the Blog Id to read the complete blog");
		while (true){
			try{
				blogId = in.nextInt();
				BlogController.temp_id = blogId;
				break;
			}catch(InputMismatchException e){
				System.out.println("Please provide a valid Input");
				break;
			}
		}
		return blogId;
	}

	/*
	 * ------------------------------------------------------------------------------------
	 * DELETE BLOG
	 * ------------------------------------------------------------------------------------
	 */
	public void deleteBlog(){
		super.deleteBlog(BlogController.temp_id);
	}
}
