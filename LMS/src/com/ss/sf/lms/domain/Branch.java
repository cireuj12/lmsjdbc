package com.ss.sf.lms.domain;

import java.io.Serializable;

public class Branch implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2849030324195280347L;
	private Integer branchId;
	private String branchName;
	private String branchAddress;
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
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the branchAddress
	 */
	public String getBranchAddress() {
		return branchAddress;
	}
	/**
	 * @param branchAddress the branchAddress to set
	 */
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		return true;
	}
	
	
	
}
