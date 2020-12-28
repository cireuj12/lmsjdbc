package com.ss.sf.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.Author;

//TO MAKE IT CLEANER
//no of queries and parameters

//create parent class abstract/interface/default to put URL in all the DAOS
public class AuthorDAO extends BaseDAO<Author> { //NO TRY CATCH INSIDE OF DAO 

	public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
//		PreparedStatement pstmt = getConnection().prepareStatement(
//				"Insert into tbl_author (authorName) values (?)");
		save("Insert into tbl_author (authorName) values (?)", 
				new Object[] {author.getAuthorName()});
//		pstmt.setString(1, author.getAuthorName()); 
//		pstmt.executeUpdate(); 

	}
	  
	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException{
		save("update tbl_author set authorName = ? where authorId = ?", 
				new Object[] {author.getAuthorName(),
								author.getAuthorId()});
//		pstmt.setString(1, author.getAuthorName()); 
//		pstmt.setInt(2, author.getAuthorId());
//		pstmt.executeUpdate(); 
	}
	
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException{
		save("delete from tbl_author where authorId = ?", 
				new Object[] {author.getAuthorId()}) ;
	}
	
	public List<Author> readAuthors() throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_author", null);
		
		//read on base
		//base return extract data
		//then return this
	}
	
	public List<Author> readAuthorsbyNameString(String authorName) throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_author where authorNAme = ? ", new Object[] {authorName});
		
		//read on base
		//base return extract data
		//then return this
	}


	@Override
	List<Author> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Author> authors = new ArrayList<>();
		
		while (rs.next()) {
			Author author = new Author(); //  this part is specific to each entity domain, so hard for Base
			
			author.setAuthorId(rs.getInt("authorID"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
			// YOU CAN DO CONSTRUCTOR //1:41
		};
		return authors;
	} 
	
}
