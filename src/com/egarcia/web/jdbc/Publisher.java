package com.egarcia.web.jdbc;

public class Publisher {
	private String pubCode;
	private String publisherName;
	private String city;
	
	public Publisher(String publisherName, String city){
	
		this.publisherName = publisherName;
		this.city = city;
		
	}
	
	public Publisher(String pubCode, String publisherName, String city){
		this.pubCode= pubCode;
		this.publisherName = publisherName;
		this.city = city;
		
	}

	public String getPubCode() {
		return pubCode;
	}

	public void setPubCode(String pubCode) {
		this.pubCode = pubCode;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "Publisher [publisherCode=" + pubCode + ", publisherName=" + publisherName + ", city=" + city +"]";
	}	
}
