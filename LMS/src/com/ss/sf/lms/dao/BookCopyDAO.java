package com.ss.sf.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.BookCopy;

public class BookCopyDAO extends BaseDAO {
	
	public void addBookCopy(BookCopy bookCopy) throws ClassNotFoundException, SQLException {
		save("Insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?)", 
				new Object[] {bookCopy.getBookId(),
								bookCopy.getBranchId(),
								bookCopy.getNoOfCopies()});
	}
	  
	public void updateBookCopy(BookCopy bookCopy) throws ClassNotFoundException, SQLException{
		save("update tbl_book_copies set noOfCopies = ? where bookId = ?", 
				new Object[] {
						bookCopy.getNoOfCopies(),
						bookCopy.getBookId()});
	}
	
	public void deleteBookCopy(BookCopy bookCopy) throws ClassNotFoundException, SQLException{
		save("delete from tbl_book_copies where bookdId = ?", 
				new Object[] {bookCopy.getBookId()}) ;
	}
	
	public List<BookCopy> readBookCopies() throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_book_copies", new Object[] {});
	}
	
	
	public List<BookCopy> readBookbyId(Integer bookId) throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_book_copies where bookId = ?", new Object[] {bookId});
	}
	

	@Override
	List<BookCopy> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<BookCopy> bookCopys = new ArrayList<>();
		
		while (rs.next()) {
			BookCopy bookCopy = new BookCopy(); //  this part is specific to each entity domain, so hard for Base
			
			bookCopy.setBookId(rs.getInt("bookId"));
//			bookCopy.setTitle(rs.getString("title")); //title not neccesary for updating Copies
			bookCopy.setBranchId(rs.getInt("branchId"));
			bookCopy.setNoOfCopies(rs.getInt("noOfCopies"));
			
			bookCopys.add(bookCopy);
			// YOU CAN DO CONSTRUCTOR //1:41
		};
		return bookCopys;
	} 

}
