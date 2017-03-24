package com.egarcia.web.jdbc;

public class Copy {

	private String bookCode;
	private int branchNum;
	private int copyNum;
	private String quality;
	private double price;
	
	
	
	
	public Copy(String bookCode, int branchNum, int copyNum, String quality,
			double price) {
		super();
		this.bookCode = bookCode;
		this.branchNum = branchNum;
		this.copyNum = copyNum;
		this.quality = quality;
		this.price = price;
	}




	@Override
	public String toString() {
		return "Copy [bookCode=" + bookCode + ", branchNum=" + branchNum
				+ ", copyNum=" + copyNum + ", quality=" + quality + ", price="
				+ price + "]";
	}




	public String getBookCode() {
		return bookCode;
	}




	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}




	public int getBranchNum() {
		return branchNum;
	}




	public void setBranchNum(int branchNum) {
		this.branchNum = branchNum;
	}




	public int getCopyNum() {
		return copyNum;
	}




	public void setCopyNum(int copyNum) {
		this.copyNum = copyNum;
	}




	public String getQuality() {
		return quality;
	}




	public void setQuality(String quality) {
		this.quality = quality;
	}




	public double getPrice() {
		return price;
	}




	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
