package com.egarcia.web.jdbc;

public class Author {
	private int authorNum;
	private String authorLast;
	private String authorFirst;
	
	
	public Author(String authorLast, String authorFirst){
		this.authorLast = authorLast;
		this.authorFirst = authorFirst;
		
		
	}
	public Author(int authorNum, String authorLast, String authorFirst){
		this.authorNum = authorNum;
		this.authorLast = authorLast;
		this.authorFirst = authorFirst;
		}
	public int getAuthorNum() {
		return authorNum;
	}
	public void setAuthorNum(int authorNum) {
		this.authorNum = authorNum;
	}
	public String getAuthorLast() {
		return authorLast;
	}
	public void setAuthorLast(String authorLast) {
		this.authorLast = authorLast;
	}
	public String getAuthorFirst() {
		return authorFirst;
	}
	public void setAuthorFirst(String authorFirst) {
		this.authorFirst = authorFirst;
	}

	@Override
	public String toString (){
		return "Author [authorNum=" + authorNum + ", authorLast=" + authorLast + ", authorFirst="+ authorFirst + "]";
	}
	
}
