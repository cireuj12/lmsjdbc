package com.ss.sf.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.BookLoan;

public class BookLoanDAO extends BaseDAO {
	public void addBookLoan(BookLoan bookLoan) throws ClassNotFoundException, SQLException {
		save("Insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values (?, ?, ?, ?, ?)", 
				new Object[] {bookLoan.getBookId(),
								bookLoan.getBranchId(),
								 bookLoan.getCardNo(),
								 bookLoan.getDateOut(),
								 bookLoan.getDueDate()});
	}
	  
	public void updateBookLoan(BookLoan bookLoan) throws ClassNotFoundException, SQLException{
		save("update tbl_book_loans set dateOut = ? setdueDate = ? where cardNo = ? and bookId = ?",
				new Object[] {bookLoan.getDateOut(),
								bookLoan.getDueDate(),
								bookLoan.getCardNo(),
								bookLoan.getBookId()});
	}
	
	public void deleteBookLoan(BookLoan bookLoan) throws ClassNotFoundException, SQLException{
		save("delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?", 
				new Object[] {bookLoan.getBookId(),
								bookLoan.getBranchId(),
								bookLoan.getCardNo()}) ;
	}
	
	public List<BookLoan> readBookLoans() throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_book_loans?", new Object[] {});
	
	}
	

	@Override
	List<BookLoan> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<BookLoan> bookLoans = new ArrayList<>();
		
		while (rs.next()) {
			BookLoan bookLoan = new BookLoan(); //  this part is specific to each entity domain, so hard for Base
			
			bookLoan.setBookId(rs.getInt("bookId"));
			bookLoan.setBranchId(rs.getInt("branchId"));
			bookLoan.setCardNo(rs.getInt("cardNo"));
			bookLoan.setDateOut(rs.getTimestamp("dateOut"));
			bookLoan.setDueDate(rs.getTimestamp("dueDate"));
			
			bookLoans.add(bookLoan);

		};
		return bookLoans;
	} 
}
