package com.ss.sf.lms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchAuthors {
	
	
	public static String driver = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/library";
	public static String username = "root";
	public static String password = "root";

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//1. Register your driver
		
		Connection conn = null; // if this was inside, then it would be out of scope for finally
		PreparedStatement pstmt = null;
		try {
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter Author Name to search");
			String authorName = scan.nextLine();
			System.out.println("Enter Author ID to search");
			Integer authorId = Integer.parseInt(scan.nextLine());
			
			
			Class.forName(driver);
			
			//2. Creating a connection
			
			conn = DriverManager.getConnection(url, username, password);
			
			//3. Create a statement object or preparedStatement to save compile and execution time
			
			//PREVENTS SQL INJECTIONS ONE CONST
			
			pstmt = conn.prepareStatement("select * from tbl_author where authorID = ?");
			//as many questions marks, set it on right order
			pstmt.setInt(1, authorId); //adds single quotes to string
			
			//database starts with 1 in parameter and not 0
			
			//4. Query + Execution
			
//			String sql = "select * from tbl_author where authorId ="+authorId;
			ResultSet rs = pstmt.executeQuery(); //just like any collection
			
			while (rs.next()) {
				System.out.println("Author ID: "+rs.getInt("authorId"));
				System.out.println("Author Name: "+rs.getString("authorName"));
				System.out.println("----------");
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			if (pstmt != null) {
				pstmt.close();
			} if (conn != null) {}
				conn.close();
		}
		
		
		
		//INSERT INTO `library`.`tbl_author` (`authorId`, `authorName`) VALUES ('2', 'Bob');
	
	}

}
