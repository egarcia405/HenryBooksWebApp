package com.egarcia.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class SearchDbUtil {

	private DataSource dataSource;
	
	public SearchDbUtil(DataSource theDataSource){
		dataSource = theDataSource;
	}
	
	public Search getSearch(String title) throws Exception {
		Search theSearch = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		String titleId;
		
		try{
			titleId = title;
			
			myConn = dataSource.getConnection();
			String sql = "SELECT title, OnHand, branchName, authorLast, authorFirst, publisherName FROM henrybooks.inventory, henrybooks.branch, henrybooks.author, henrybooks.publisher, henrybooks.book "
					+ "where title =? LIMIT 1";
			myStmt= myConn.prepareStatement(sql);
			
			myStmt.setString(1, titleId);
			
			myRs = myStmt.executeQuery();
			if (myRs.next()) {
				String title2 = myRs.getString("title");
				int OnHand = myRs.getInt("OnHand");
				String branchName = myRs.getString("branchName");
				String authorLast = myRs.getString("authorLast");
				String authorFirst = myRs.getString("authorFirst");
				String publisherName = myRs.getString("publisherName");

				// use the studentId during construction
				theSearch = new Search(title2, OnHand, branchName, authorLast, authorFirst, publisherName);
			}
			else {
				throw new Exception("Could not find book: " + title);
			}

			return theSearch;}finally{
				close(myConn, myStmt, myRs);

			}
			
		}

	public List<Search> getSearches(String title) throws Exception  {
	
		List<Search> searches = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		String titleId;
		
		try{
			titleId = title;
			myConn = dataSource.getConnection();
			
/*			String sql ="SELECT title, OnHand, branchName, authorLast, authorFirst, publisherName FROM  inventory, branch, author, publisher, book "
					+"where title =? LIMIT 1";*/
			//String sql  = "select * from book where title=?";
			
			String sql = "SELECT title, OnHand, branchName, authorLast, authorFirst, publisherName "
					+"FROM Book, Inventory, Author, Publisher, Wrote, Branch "
					+" Where Book.title =? AND inventory.BookCode = book.bookCode AND inventory.BranchNum = branch.branchNum "
					+ "AND book.bookCode = wrote.bookCode AND wrote.authorNum = author.authorNum AND Book.publisherCode = publisher.publisherCode LIMIT 1" ;
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, titleId);

			myRs = myStmt.executeQuery();
			while (myRs.next()) {

				// retrieve data from result set row
				String title2 = myRs.getString("title");
				int OnHand = myRs.getInt("OnHand");
				String branchName = myRs.getString("branchName");
				String authorLast = myRs.getString("authorLast");
				String authorFirst = myRs.getString("authorFirst");
				String publisherName = myRs.getString("publisherName");

				// create new student object
				Search tempSearch = new Search(title2, OnHand, branchName, authorLast, authorFirst, publisherName);

				// add it to the list of students
				searches.add(tempSearch);
			}

			return searches;
			
		}
		finally{
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
}
