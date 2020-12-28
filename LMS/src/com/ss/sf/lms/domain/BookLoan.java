package com.ss.sf.lms.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class BookLoan implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5838851205213752694L;
	
	private Integer bookId;
	private Integer branchId;
	private Integer cardNo;
	private Timestamp dateOut;
	private Timestamp dueDate;
	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the cardNo
	 */
	public Integer getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the dateOut
	 */
	public Timestamp getDateOut() {
		return dateOut;
	}
	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(Timestamp dateOut) {
		this.dateOut = dateOut;
	}
	/**
	 * @return the dueDate
	 */
	public Timestamp getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}
	
	

}
