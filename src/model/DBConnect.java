package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnect {
	protected Connection con;
	protected PreparedStatement stmt;
	protected DBConnect(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vs_lala","tiger");
		}catch (SQLException e) {
			System.out.println("There was problem connecting to the database");
		} catch (ClassNotFoundException e) {
			System.out.println("Database Connection error! Please Try Again!!");
//			e.printStackTrace();
		}catch (Exception e){
			
		}
	}

}
