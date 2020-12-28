package com.ss.sf.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.Book;

public class BookDAO extends BaseDAO {
	
	public void addBook(Book book) throws ClassNotFoundException, SQLException {
		save("Insert into tbl_book (bookId, title, authID, pubId ) values (?)", 
				new Object[] {book.getBookId(),
								book.getTitle(),
								book.getAuthId(),
								book.getPubId()});
	}
	  
	public void updateBook(Book book) throws ClassNotFoundException, SQLException{
		save("update tbl_book set title = ?, authID = ?, pubId = ? where bookId = ?", 
				new Object[] {book.getTitle(),
								book.getAuthId(),
								book.getPubId(),
								book.getBookId()});
	}
	
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException{
		save("delete from tbl_book where bookId = ?", 
				new Object[] {book.getBookId()}) ;
	}
	
	public List<Book> readBooks() throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_book", new Object[] {});
	
	}
	
	public List<Book> readBooksAuthor() throws ClassNotFoundException, SQLException { //slightly different
		return read("select bookId, title, tbl_author.authorName, authID, pubId from tbl_book left join tbl_author on tbl_book.authID = tbl_author.authorId ", new Object[] {});
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readBooksbyBranch(Integer branchId) throws ClassNotFoundException, SQLException { //slightly different
		return read("select tbl_book.bookId, title, tbl_author.authorName, authID, pubId "
				+ "from tbl_book "
				+ "left join tbl_author "
				+ "on tbl_book.authID = tbl_author.authorId "
				+ "left join tbl_book_copies "
				+ "on tbl_book.bookId = tbl_book_copies.bookId "
				+ "where tbl_book_copies.branchId = ? and noOfCopies > 0", new Object[] {branchId}); //greater than 0
		//This query works
	}

	@Override
	List<Book> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<>();
		
		while (rs.next()) {
			Book book = new Book(); //  this part is specific to each entity domain, so hard for Base
			
			book.setBookId(rs.getInt("tbl_book.bookId"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("tbl_author.authorName"));
			book.setAuthId(rs.getInt("authID"));
			book.setPubId(rs.getInt("pubId"));
			books.add(book);
			// YOU CAN DO CONSTRUCTOR //1:41
		};
		return books;
	} 

}
