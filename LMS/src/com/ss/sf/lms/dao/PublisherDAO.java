package com.ss.sf.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.Publisher;

public class PublisherDAO extends BaseDAO {
	public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException { //auto increment
		save("Insert into tbl_publisher (publisherName, publisherAddress, publisherPhone ) values (?, ?, ?)", 
				new Object[] {
								publisher.getPublisherName(),
								publisher.getPublisherAddress(),
								publisher.getPublisherPhone()});
	}
	  
	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		save("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?", 
				new Object[] {
								publisher.getPublisherName(),
								publisher.getPublisherAddress(),
								publisher.getPublisherPhone(),
								publisher.getPublisherId()});
	}
	
	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		save("delete from tbl_publisher where publisherId = ?", 
				new Object[] {publisher.getPublisherId()}) ;
	}
	
	public List<Publisher> readPublisherById(Integer publisherId) throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_publisher where publisherId = ?", new Object[] {publisherId});
	
	}
	
	public List<Publisher> readPublishers() throws ClassNotFoundException, SQLException { //slightly different
		return read("select * from tbl_publisher", new Object[] {});
	
	}
	

	@Override
	List<Publisher> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Publisher> publishers = new ArrayList<>();
		
		while (rs.next()) {
			Publisher publisher = new Publisher(); //  this part is specific to each entity domain, so hard for Base
			
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(publisher);
			// YOU CAN DO CONSTRUCTOR //1:41
		};
		return publishers;
	} 
}
