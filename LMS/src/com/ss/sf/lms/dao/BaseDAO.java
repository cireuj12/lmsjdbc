package com.ss.sf.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {
	
	public static String driver = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/library";
	public static String username = "root";
	public static String password = "root";
	
	
	//make connection into SINGLETON OBJECT?
	
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		return DriverManager.getConnection(url,username,password);
	}
	
	public void save(String sql, Object[] vals) throws ClassNotFoundException, SQLException { //for Create Update and Delete NOT READ
		//a different string with diff parameters for pstmt
		
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		int count = 1;
		for (Object o: vals) { // for o in vals
			pstmt.setObject(count, o); 
			count++;
		}
		
		pstmt.executeUpdate(); 

		
	}
	
	public List<T> read(String sql, Object[] vals) throws ClassNotFoundException, SQLException { //for Create Update and Delete NOT READ
		//a different string with diff parameters for pstmt
		
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		int count = 1;
		for (Object o: vals) { // for o in vals
			pstmt.setObject(count, o); 
			count++;
		}
		
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);

	}
	
	abstract List<T> extractData(ResultSet rs) throws ClassNotFoundException, SQLException;
	
}
