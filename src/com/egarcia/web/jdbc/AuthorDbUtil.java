package com.egarcia.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class AuthorDbUtil {
	private DataSource dataSource;

	public AuthorDbUtil( DataSource theDataSource){
		dataSource=theDataSource;
	}
	

	
	public List<Author> getAuthors() throws Exception{
		List<Author> authors = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myConn = dataSource.getConnection();
			String sql = "select * from author";
			myStmt = myConn.createStatement();
			
			myRs= myStmt.executeQuery(sql);
			
			while(myRs.next()){
				
				int num = myRs.getInt("authorNum");
				String authorLast = myRs.getString("authorLast");
				String authorFirst = myRs.getString("authorFirst");
			
				Author tempAuthor = new Author(num,authorLast,authorFirst);
				
				authors.add(tempAuthor);
			}
			return authors;
			
		}
		finally{
			close(myConn,myStmt,myRs);
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

	public void addAuthor(Author theAuthor) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{
			myConn = dataSource.getConnection();
			
			String sql = "insert into author "
					   + "(authorNum, authorLast, authorFirst) "
					   + "values (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			
			
		myStmt.setInt(1, theAuthor.getAuthorNum());
		myStmt.setString(2, theAuthor.getAuthorLast());
		myStmt.setString(3,theAuthor.getAuthorFirst());
		
		myStmt.execute();
		}
		finally{
			close(myConn, myStmt, null);

		}
		
	}
	
	public Author getAuthor(String theAuthorNum) throws Exception {
		Author theAuthor = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int authorNum;
		
		try{
			authorNum = Integer.parseInt(theAuthorNum);
			myConn = dataSource.getConnection();

			String sql="select * from author where authorNum =?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, authorNum);
			myRs= myStmt.executeQuery();
			
			if(myRs.next()){
				int authorNumId = myRs.getInt("authorNum");
				String authorLast =  myRs.getString("authorLast");
				String authorFirst = myRs.getString("authorFirst");
				
				theAuthor = new Author(authorNumId,authorLast,authorFirst);
				
			}
			else{
				throw new Exception("Invalid author number: " + authorNum);
			}
			return theAuthor;
		}
		finally{
			close(myConn, myStmt, myRs);

		}
	}

	public void updateAuthor(Author theAuthor) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{
			myConn= dataSource.getConnection();
			
			String sql = "update author "
					+ "set authorLast =?, authorFirst=? "
					+ "where authorNum=?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, theAuthor.getAuthorLast());
			myStmt.setString(2, theAuthor.getAuthorFirst());
			myStmt.setInt(3, theAuthor.getAuthorNum());
			myStmt.execute();

		}
		finally{
			close(myConn, myStmt, null);

		}

		
	}

	public void deleteAuthor(String theAuthorNum) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{
			theAuthorNum= theAuthorNum.trim();
			int authorNum = Integer.parseInt(theAuthorNum);
			
			myConn= dataSource.getConnection();
			
			String sql = "delete from author where authorNum=?";
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, authorNum);
			
			// execute sql statement
			myStmt.execute();
			
			
		}
		finally{
			close(myConn, myStmt, null);

		}
		
		
	}



	


}
