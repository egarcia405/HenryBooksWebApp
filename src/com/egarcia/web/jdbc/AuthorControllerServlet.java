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
 * Servlet implementation class AuthorControllerServlet
 */
@WebServlet("/AuthorControllerServlet")
public class AuthorControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AuthorDbUtil authorDbUtil;
	
	@Resource(name="jdbc/HenryBooksWebApp1")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException{
		super.init();
		
		try {
			authorDbUtil = new AuthorDbUtil(dataSource);
		}
		catch(Exception exc){
			throw new ServletException(exc);
		}
		
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String theCommand = request.getParameter("command");
		
		// if the command is missing, then default to listing students
		if (theCommand == null) {
			theCommand = "LIST";
		}
		
		// route to the appropriate method
		switch (theCommand) {
		
		case "LIST":
			listAuthors(request, response);
			break;
			
		case "ADD":
			addAuthor(request, response);
			break;
			
		case "LOAD":
			loadAuthor(request, response);
			break;
			
		case "UPDATE":
			updateAuthor(request, response);
			break;
		
		case "DELETE":
			deleteAuthor(request, response);
			break;
			
		default:
			listAuthors(request, response);
		}
			
	}
	catch (Exception exc) {
		throw new ServletException(exc);
	}
	}

	private void deleteAuthor(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String theAuthorNum = request.getParameter("authorNum");
		authorDbUtil.deleteAuthor(theAuthorNum);
		listAuthors(request,response);
		
	}
	


	private void updateAuthor(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int num = Integer.parseInt(request.getParameter("authorNum"));
		String authorLast= request.getParameter("authorLast");
		String authorFirst = request.getParameter("authorFirst");
		
		Author theAuthor = new Author(num, authorLast, authorFirst);
		authorDbUtil.updateAuthor(theAuthor);
		listAuthors(request,response);
		
	}





	private void loadAuthor(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String theAuthorNum = request.getParameter("authorNum");
		Author theAuthor =  authorDbUtil.getAuthor(theAuthorNum);
		request.setAttribute("THE_AUTHOR", theAuthor);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-author-form.jsp");
		dispatcher.forward(request,response);
	}

	private void addAuthor(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		int authorNum = Integer.parseInt(request.getParameter("authorNum"));
		String authorLast = request.getParameter("authorLast");
		String authorFirst = request.getParameter("authorFirst");
		
		Author theAuthor = new Author (authorNum, authorLast, authorFirst);
		
		authorDbUtil.addAuthor(theAuthor);
		listAuthors(request, response);
		
		
		
	}
	private void listAuthors(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		List<Author> authors = authorDbUtil.getAuthors();
		request.setAttribute("AUTHOR_LIST", authors);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-authors.jsp");
		dispatcher.forward(request, response);
		
	}



}
