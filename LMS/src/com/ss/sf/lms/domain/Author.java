package com.ss.sf.lms.domain;

import java.io.Serializable;

public class Author implements Serializable { /**
	 * 
	 */
	private static final long serialVersionUID = -7861230401013371841L; //make it serializable
	//domain outside of JVM into database
	// transmit to collections, outside of Java

	private Integer authorId; //make gettors and settors
	private String authorName;
 
	/**
	 * @return the authorId
	 */
	public Integer getAuthorId() {
		return authorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
	}
	
	@Override
	public int hashCode() { //make hashcode why?
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
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
		Author other = (Author) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		return true;
	}
	

}
