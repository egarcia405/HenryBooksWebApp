package com.egarcia.web.jdbc;

public class Search {
private String title;
private int OnHand;
private String branchName;
private String authorLast;
private String authorFirst;
private String publisherName;
public Search(String title, int OnHand, String branchName, String authorLast,
		String authorFirst, String publisherName) {
	super();
	this.title = title;
	this.OnHand = OnHand;
	this.branchName = branchName;
	this.authorLast = authorLast;
	this.authorFirst = authorFirst;
	this.publisherName = publisherName;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getOnHand() {
	return OnHand;
}
public void setOnHand(int OnHand) {
	this.OnHand = OnHand;
}
public String getBranchName() {
	return branchName;
}
public void setBranchName(String branchName) {
	this.branchName = branchName;
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
public String getPublisherName() {
	return publisherName;
}
public void setPublisherName(String publisherName) {
	this.publisherName = publisherName;
}


}
