package com.egarcia.web.jdbc;

public class Book {
	private String bookCode;
	private String title;
	private String publisherCode;
	private String type;
	private String paperBack;
	
	
	public Book(String bookCode, String title, String publisherCode,
			String type, String paperBack) {
	
		this.bookCode = bookCode;
		this.title = title;
		this.publisherCode = publisherCode;
		this.type = type;
		this.paperBack = paperBack;
	}


	public String getBookCode() {
		return bookCode;
	}


	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPublisherCode() {
		return publisherCode;
	}


	public void setPublisherCode(String publisherCode) {
		this.publisherCode = publisherCode;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getPaperBack() {
		return paperBack;
	}


	public void setPaperBack(String paperBack) {
		this.paperBack = paperBack;
	}
	
	@Override
	public String toString(){
		return "Book [bookCode=" + bookCode + ", title=" + title + ", publishercode="
				+ publisherCode + ", type =" + type + ", paperBack = "+paperBack + "]";
	}
}
