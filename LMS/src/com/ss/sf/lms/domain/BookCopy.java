package com.ss.sf.lms.domain;

import java.io.Serializable;

public class BookCopy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3166651089528960716L;
	
	private int bookId;
	private String title;
	private int branchId;
	private int noOfCopies;
	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the noOfCopies
	 */
	public int getNoOfCopies() {
		return noOfCopies;
	}
	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + bookId;
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		BookCopy other = (BookCopy) obj;
//		if (bookId != other.bookId)
//			return false;
//		return true;
//	}
	
	
	
}
