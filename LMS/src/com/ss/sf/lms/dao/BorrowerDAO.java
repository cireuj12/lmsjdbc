package com.ss.sf.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.Borrower;

public class BorrowerDAO extends BaseDAO {

	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException { //auto increment
		save("Insert into tbl_borrower (name, address, phone ) values (?, ?, ?)", 
				new Object[] {
								borrower.getName(),
								borrower.getAddress(),
								borrower.getPhone()});
	}
	  
	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?", 
				new Object[] {borrower.getName(),
								borrower.getAddress(),
								borrower.getPhone(),
								borrower.getCardNo()});
	}
	
	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		save("delete from tbl_borrower where cardNo = ?", 
				new Object[] {borrower.getCardNo()}) ;
	}
	
	public List<Borrower> readBorrowerById(Integer cardNo) throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_borrower where cardNo = ?", new Object[] {cardNo});
	
	}
	
	public List<Borrower> readBorrowers() throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_borrower", new Object[] {});
	
	}
	

	@Override
	List<Borrower> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Borrower> borrowers = new ArrayList<>();
		
		while (rs.next()) {
			Borrower borrower = new Borrower(); //  this part is specific to each entity domain, so hard for Base
			
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrower.setAddress(rs.getString("address"));
			borrower.setPhone(rs.getString("phone"));
			borrowers.add(borrower);
			// YOU CAN DO CONSTRUCTOR //1:41
		};
		return borrowers;
	} 
}
