package com.ss.sf.lms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetAuthors {

	
	public static String driver = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/library";
	public static String username = "root";
	public static String password = "root";
	
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//1. Register your driver
		
		Connection conn = null; // if this was inside, then it would be out of scope for finally
		Statement stmt = null;
		try {
			Class.forName(driver);
			
			//2. Creating a connection
			
			conn = DriverManager.getConnection(url, username, password);
			
			//3. Create a statement object
			
			stmt = conn.createStatement();
			
			//4. Query + Execution
			
			String sql = "select * from tbl_author";
			ResultSet rs = stmt.executeQuery(sql); //just like any collection
			
			while (rs.next()) {
				System.out.println("Author ID: "+rs.getInt("authorId"));
				System.out.println("Author Name: "+rs.getString("authorName"));
				System.out.println("----------");
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			if (stmt != null) {
				stmt.close();
			} if (conn != null) {}
				conn.close();
		}
		
		
		
		//INSERT INTO `library`.`tbl_author` (`authorId`, `authorName`) VALUES ('2', 'Bob');
	
	}

}
