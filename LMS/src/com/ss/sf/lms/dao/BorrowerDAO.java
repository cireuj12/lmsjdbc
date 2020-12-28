package com.ss.sf.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.Borrower;

public class BorrowerDAO extends BaseDAO {

	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("Insert into tbl_borrower (cardNo, name, authID, pubId ) values (?)", 
				new Object[] {borrower.getCardNo(),
								borrower.getName()});
	}
	  
	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		save("update tbl_borrower set name = ? where cardNo = ?", 
				new Object[] {borrower.getName(),
								borrower.getCardNo()});
	}
	
	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		save("delete from tbl_borrower where cardNo = ?", 
				new Object[] {borrower.getCardNo()}) ;
	}
	
	public List<Borrower> readBorrowerById(Integer cardNo) throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_borrower where cardNo = ?", new Object[] {cardNo});
	
	}
	

	@Override
	List<Borrower> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Borrower> borrowers = new ArrayList<>();
		
		while (rs.next()) {
			Borrower borrower = new Borrower(); //  this part is specific to each entity domain, so hard for Base
			
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrowers.add(borrower);
			// YOU CAN DO CONSTRUCTOR //1:41
		};
		return borrowers;
	} 
}
