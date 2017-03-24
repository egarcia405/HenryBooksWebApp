package com.egarcia.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CopyDbUtil {
	private DataSource dataSource;
	public CopyDbUtil(DataSource theDataSource){
		dataSource = theDataSource;
	}
	
	public List<Copy> getCopies() throws Exception {
		List<Copy> copies = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myConn = dataSource.getConnection();
			
			String sql = "select * from copy";
			
			myStmt= myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {

				// retrieve data from result set row
				String bookCode = myRs.getString("bookCode");
				int branchNum = myRs.getInt("branchNum");
				int copyNum = myRs.getInt("copyNum");
				String quality = myRs.getString("quality");
				double price = myRs.getDouble("price");

				// create new student object
				Copy tempCopy = new Copy(bookCode, branchNum, copyNum, quality, price);

				// add it to the list of students
				copies.add(tempCopy);
			}

			return copies;
			
		}
		finally{
			close(myConn, myStmt, myRs);

		}
		
		
	}

	public Copy getCopy(String theBookCodeId, String theBranchNumId, String theCopyNumId) throws Exception {
		Copy theCopy = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		String bookCodeId;
		int branchNumId;
		int copyNumId;
		try{
			bookCodeId= theBookCodeId;
			branchNumId= Integer.parseInt(theBranchNumId);
			copyNumId = Integer.parseInt(theCopyNumId);
			myConn = dataSource.getConnection();
			
			String sql = "select * from copy where bookCode=? AND branchNum=? AND copyNum=?";
		
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, bookCodeId);
			myStmt.setInt(2, branchNumId);
			myStmt.setInt(3, copyNumId);
			
			myRs = myStmt.executeQuery();
			
			if (myRs.next()) {
				String bcId = myRs.getString("bookCode");
				int bnId = myRs.getInt("branchNum");
				int copyNum = myRs.getInt("copyNum");
				String  quality = myRs.getString("quality");
				double price = myRs.getDouble("price");

				// use the studentId during construction
				theCopy = new Copy(bcId, bnId , copyNum , quality, price);
			}
			else {
				throw new Exception("Could not find Copy ID ");
			}

			return theCopy;
			
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

	public void updateCopy(Copy theCopy) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{
			
			myConn= dataSource.getConnection();
			
			String sql = "update copy "
					+ "set quality=?, price=? "
					+ "where bookCode=? AND branchNum=? AND copyNum=?";
			myStmt= myConn.prepareStatement(sql);
			
			myStmt.setString(1, theCopy.getQuality());
			myStmt.setDouble(2, theCopy.getPrice());
			myStmt.setString(3, theCopy.getBookCode());
			myStmt.setInt(4, theCopy.getBranchNum());
			myStmt.setInt(5, theCopy.getCopyNum());
			
			
			myStmt.execute();
		}
		finally{
			close(myConn, myStmt, null);

		}
		
	}

	public void deleteCopy(String theBookCodeId, String theBranchNum,
			String theCopyNum) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{
			String bcId = theBookCodeId;
			int bnId = Integer.parseInt(theBranchNum);
			int copyNum = Integer.parseInt(theCopyNum);
			
			myConn = dataSource.getConnection();
			String sql  =  "delete from copy where bookCode=? AND branchNum=? AND copyNum=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, bcId);
			myStmt.setInt(2, bnId);
			myStmt.setInt(3, copyNum);
			
			myStmt.execute();
			
		}
		finally{
			close(myConn, myStmt, null);

		}
		
	}

	public void addCopy(Copy theCopy) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try{
			myConn = dataSource.getConnection();
			
			String sql = "insert into copy "
					+ "(bookCode, branchNum, copyNum, quality, price) "
					+ "values (?,?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, theCopy.getBookCode());	
			myStmt.setInt(2, theCopy.getBranchNum());
			myStmt.setInt(3, theCopy.getCopyNum());
			myStmt.setString(4, theCopy.getQuality());
			myStmt.setDouble(5, theCopy.getPrice());
			
			myStmt.execute();
		}
		finally{
			
			close(myConn, myStmt, null);

		}

		
	}
}
