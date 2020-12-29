package com.ss.sf.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.Branch;

public class BranchDAO extends BaseDAO {

	
	public void addBranch(Branch branch) throws ClassNotFoundException, SQLException { //primary key should auto increment
		save("Insert into tbl_library_branch (branchName, branchAddress) values (? , ?) ", //always have commas
				new Object[] {branch.getBranchName(),
						branch.getBranchAddress()});
	}
	  
	public void updateBranch(Branch branch) throws ClassNotFoundException, SQLException{
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?", 
				new Object[] {branch.getBranchName(),
								branch.getBranchAddress(),
								branch.getBranchId()});
	}
	
	public void deleteBranch(Branch branch) throws ClassNotFoundException, SQLException{
		save("delete from tbl_library_branch where branchId = ?", 
				new Object[] {branch.getBranchId()}) ;
	}
	
	public List<Branch> readBranchs() throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_library_branch", new Object[] {});
	
	}
	
	public List<Branch> readBranchById(Integer branchId ) throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_library_branch where branchId = ?", new Object[] {branchId});
	
	}
	
	public List<Branch> readBranchsbyNameString(String branchName) throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_library_branch where branchName = ? ", new Object[] {branchName});
	}


	@Override
	List<Branch> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Branch> branchs = new ArrayList<>();
		
		while (rs.next()) {
			Branch branch = new Branch(); //  this part is specific to each entity domain, so hard for Base
			
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branch.setBranchAddress(rs.getString("branchAddress"));
			branchs.add(branch);
			// YOU CAN DO CONSTRUCTOR //1:41
		};
		return branchs;
	} 
}
 