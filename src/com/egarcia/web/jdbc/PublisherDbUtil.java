package com.egarcia.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class PublisherDbUtil {
	private DataSource dataSource;

	
public PublisherDbUtil(DataSource theDataSource){
	dataSource = theDataSource;
	
}
	
public List<Publisher> getPublishers() throws Exception {
	List<Publisher> publishers = new ArrayList<>();
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;

	try {
		// get a connection
		myConn = dataSource.getConnection();

		// create sql statement
		String sql = "select * from publisher";

		myStmt = myConn.createStatement();

		// execute query
		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {

			// retrieve data from result set row
			
			String pubCode = myRs.getString("publisherCode");
			String publisherName = myRs.getString("publisherName");
			String city = myRs.getString("city");

			// create new student object
			Publisher tempPublisher = new Publisher(pubCode, publisherName, city);

			// add it to the list of students
			publishers.add(tempPublisher);
		}

		return publishers;
	}
	finally {
		// close JDBC objects
		close(myConn, myStmt, myRs);
	}
	
}


private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

	try {
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();   // doesn't really close it ... just puts back in connection pool
		}
	}
	catch (Exception exc) {
		exc.printStackTrace();
	}
}

public void addPublisher(Publisher thePublisher) throws Exception {
	
	Connection myConn = null;
	PreparedStatement myStmt = null;

	try {
		// get db connection
		myConn = dataSource.getConnection();

		// create sql for insert
		String sql = "insert into publisher "
				   + "(publisherCode, publisherName, city) "
				   + "values (?, ?, ?)";

		myStmt = myConn.prepareStatement(sql);

		// set the param values for the student
		myStmt.setString(1, thePublisher.getPubCode());
		myStmt.setString(2, thePublisher.getPublisherName());
		myStmt.setString(3, thePublisher.getCity());

		// execute sql insert
		myStmt.execute();
	}
	finally {
		// clean up JDBC objects
		close(myConn, myStmt, null);
	}
	
	
}

public Publisher getPublisher(String thePubCode) throws Exception  {
	
	
	Publisher thePublisher = null;

	Connection myConn = null;
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
	String pubCode;

	try {
		// convert student id to int
		pubCode = thePubCode;

		// get connection to database
		myConn = dataSource.getConnection();

		// create sql to get selected student
		String sql = "select * from publisher where publisherCode=?";

		// create prepared statement
		myStmt = myConn.prepareStatement(sql);

		// set params
		myStmt.setString(1, pubCode);

		// execute statement
		myRs = myStmt.executeQuery();

		// retrieve data from result set row
		if (myRs.next()) {
			String publisherCode = myRs.getString("publisherCode");
			String publisherName = myRs.getString("publisherName");
			String city = myRs.getString("city");

			// use the studentId during construction
			thePublisher = new Publisher(publisherCode, publisherName, city);
		}
		else {
			throw new Exception("Could not find publisher Code: " + pubCode);
		}

		return thePublisher;
	}
	finally {
		// clean up JDBC objects
		close(myConn, myStmt, myRs);
	}
	
}

public void updatePublisher(Publisher thePublisher) throws Exception{
	
	Connection myConn = null;
	PreparedStatement myStmt = null;

	try {
		// get db connection
		myConn = dataSource.getConnection();

		// create SQL update statement
		String sql = "update publisher "
					+ "set publisherName =?, city=? "
					+ "where publisherCode =?";

		// prepare statement
		myStmt = myConn.prepareStatement(sql);

		// set params
		myStmt.setString(1, thePublisher.getPublisherName());
		myStmt.setString(2, thePublisher.getCity());
		myStmt.setString(3, thePublisher.getPubCode());
	

		// execute SQL statement
		myStmt.execute();
	}
	finally {
		// clean up JDBC objects
		close(myConn, myStmt, null);
	}
	
}

public void deletePublisher(String thePubCode) throws Exception {
	Connection myConn = null;
	PreparedStatement myStmt = null;

	try {
		// convert student id to int
		String pubCode = thePubCode;

		// get connection to database
		myConn = dataSource.getConnection();

		// create sql to delete student
		String sql = "delete from publisher where publisherCode=?";

		// prepare statement
		myStmt = myConn.prepareStatement(sql);

		// set params
		myStmt.setString(1, pubCode);

		// execute sql statement
		myStmt.execute();
	}
	finally {
		// clean up JDBC code
		close(myConn, myStmt, null);
	}
	
}

}
