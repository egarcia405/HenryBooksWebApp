package com.egarcia.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class BookControllerServlet
 */
@WebServlet("/BookControllerServlet")
public class BookControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookDbUtil bookDbUtil;
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Resource(name= "jdbc/HenryBooksWebApp1")
	private DataSource dataSource;
	
	
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		//to create an instance of BookDbUtil and pass in the connection pool/dataSource obj
		try{
			bookDbUtil = new BookDbUtil(dataSource);
		}
		catch(Exception exc){
			throw new ServletException(exc);
			
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//read the "command" from the parameter
			String theCommand = request.getParameter("command");
			//check if  the command is missing if so then just return list
			if(theCommand == null){
				theCommand = "LIST";
			}
			switch(theCommand){
			case "LIST": listBooks(request,response);
			break;
			case "ADD": addBooks(request,response);
			break;
			case "LOAD": loadBook(request,response);
			break;
			case "UPDATE": updateBook(request,response);
			break;
			case "DELETE": deleteBook(request,response);
			break;
			default: listBooks(request,response);
		
			}} //listBooks(request,response); uncomment line  incase books don't list
			catch(Exception exc){
				throw new ServletException(exc);
			}
			
		}
		
		private void deleteBook(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
			//read bookId from the form data
			String theBookId = request.getParameter("bookId");
			
			//delete book from database	
			bookDbUtil.deleteBook(theBookId);
			
			//return to bookList
			listBooks(request,response);
		
	}

		private void updateBook(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			//read book info from the form data
			String bookCodeId = request.getParameter("bookCode");
			String title = request.getParameter("title");
			String publisherCode = request.getParameter("publisherCode");
			String type = request.getParameter("type");
			String paperBack= request.getParameter("paperback");
			
			//create a new book object
			Book theBook = new Book(bookCodeId,title,publisherCode,type,paperBack);
			
			//perform update on database
			bookDbUtil.updateBook(theBook);
			
			//send them back to the "list books" page
			listBooks(request,response);
	}

		private void loadBook(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			
			//read bookCode from form data
			//String theBookId = request.getParameter("bookId");
			String theBookId = request.getParameter("bookId");
			//get book from database  (dbutil)
			Book theBook = bookDbUtil.getBook(theBookId);
			
			//place book in the request attribute
			request.setAttribute("THE_BOOK", theBook);
			
			//send to jsp : update-book-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-book-form.jsp");
			dispatcher.forward(request, response);
	}

		private void addBooks(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			//read book info from form data
			String bookCode = request.getParameter("bookCode");
			String title = request.getParameter("title");
			String publisherCode = request.getParameter("publisherCode");
			String type = request.getParameter("type");
			String paperBack = request.getParameter("paperback");
			
			//create a new book object
			Book theBook = new Book(bookCode,title,publisherCode,type,paperBack);
			
			//add the book to the database
			bookDbUtil.addBook(theBook);
			
			//send  back to book table
			listBooks(request,response);
		
	}


		

	private void listBooks(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//get books from db util
		List<Book> books = bookDbUtil.getBooks();
		
		//add book to the request
		request.setAttribute("BOOK_LIST", books);
		
		//send to JSP page
		RequestDispatcher  dispatcher = request.getRequestDispatcher("/list-books.jsp");
		
		dispatcher.forward(request, response);
		
	}
	}





