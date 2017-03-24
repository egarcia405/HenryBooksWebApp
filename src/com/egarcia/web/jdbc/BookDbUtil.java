package com.egarcia.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;


public class BookDbUtil {
	
	private DataSource dataSource;
	public BookDbUtil (DataSource theDataSource){
		
		dataSource= theDataSource;
	}
	
	public List<Book> getBooks() throws Exception{
		List<Book> books = new ArrayList<>();
		
		Connection myConn= null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			
			//get a connection
			myConn = dataSource.getConnection();
			
			//create SQL statement
			String sql = "select * from book"; 
			myStmt = myConn.createStatement();
	
			//execute query
			myRs=myStmt.executeQuery(sql);
			
			//process result set
			while(myRs.next()){
				
			//retrieve data from result set row this is each attribute from the book relation
			String bookCode = myRs.getString("bookCode");
			String title = myRs.getString("title");
			String publisherCode = myRs.getString("publisherCode");
			String type = myRs.getString("type") ;
			String paperBack= myRs.getString("paperback");
			
			//create a new book object
			Book tempBook = new Book(bookCode, title, publisherCode, type, paperBack);
			
			//add it to the list of books
			books.add(tempBook);
			}
			return books;
		}
		finally{
			//closing jdbc objects
			close(myConn,myStmt,myRs);
		}
		
		
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
	
		try{
			if(myRs != null){
				myRs.close();
			}
			if(myStmt != null ){
				myStmt.close();
			}
			if(myConn != null){
				myConn.close();
			}
		}
		catch (Exception exc){
			
			exc.printStackTrace();
			
		}
	}
	
	public void addBook(Book theBook) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{
			myConn = dataSource.getConnection();
			//Create SQL statement for inserting a book
			String sql = "insert into book" + "(bookCode,title,publisherCode,type,paperback)" + "values(?,?,?,?,?)";
			myStmt = myConn.prepareStatement(sql);
			
			//set the parameter values for the book
			myStmt.setString(1, theBook.getBookCode());
			myStmt.setString(2, theBook.getTitle());
			myStmt.setString(3, theBook.getPublisherCode());
			myStmt.setString(4, theBook.getType());
			myStmt.setString(5, theBook.getPaperBack());
			
			//execute insert query for book
			myStmt.execute();
			
			
		}
		finally{
			//clean jdbc objects
			close(myConn, myStmt, null);
		}
		
	}
	
	public Book getBook(String theBookId) throws Exception {
		
		Book theBook = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		String bookId;
		try{
			//assign theBookId to bookId
			bookId = theBookId;
			
			//connect to the database
			myConn = dataSource.getConnection();
			
			//create sql to get selected bookCode
			String sql = "select * from book where bookCode = ? ";
			
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set parameters
			//bookId =  bookId.substring(0,5);
			myStmt.setString(1, bookId);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from result set row
			if(myRs.next()){
			
				String bookCode = myRs.getString("bookCode");
				String title =  myRs.getString("title");
				String publisherCode = myRs.getString("publisherCode");
				String type = myRs.getString("type");
				String paperBack = myRs.getString("paperback");
				
				//construct book using bookCode
				
				theBook = new Book(bookCode,title,publisherCode,type,paperBack);
			
			}
			else{
				throw new Exception ("Invalid BookCode: " + bookId);
			}
			
			return theBook;
		}
		finally{
			close(myConn,myStmt,myRs);
		}
		
		
	}
	
	public  void updateBook(Book theBook) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{
			//get connection to the DB
			myConn = dataSource.getConnection();
			
			//create SQL statement to edit a book
			String sql = "update book "
			+ "set title=?, publisherCode=?, type=?, paperback=? "
			+ "where bookCode=?";
			
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set parameters
			
		
			myStmt.setString(1, theBook.getTitle());
			myStmt.setString(2, theBook.getPublisherCode());
			myStmt.setString(3, theBook.getType());
			myStmt.setString(4, theBook.getPaperBack());
			myStmt.setString(5, theBook.getBookCode());
			
		
			
			//execute SQL statement
			
			myStmt.execute();
			
		}
		finally{
			close(myConn,myStmt,null);
		}
		
		
	}

	public void deleteBook(String theBookId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{
			//get bookCode
			String bookId = theBookId;
			
			//connect to the database
			myConn = dataSource.getConnection();
			
			//create SQL statement to delete book
			String sql = "delete from book where bookCode=?";
			
			//prepare statemtn
			myStmt = myConn.prepareStatement(sql);
			
			//set parameter
			myStmt.setString(1, bookId);
			
			//execute sql statement
			myStmt.execute();
			
			
			
		}
		finally{
			//clean jdbc objects
			close(myConn, myStmt, null);
		}
		
	}

}
