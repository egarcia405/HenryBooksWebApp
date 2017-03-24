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
 * Servlet implementation class SearchControllerServlet
 */
@WebServlet("/SearchControllerServlet")
public class SearchControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SearchDbUtil searchDbUtil;
	
	@Resource(name="jdbc/HenryBooksWebApp1")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			searchDbUtil = new SearchDbUtil(dataSource);
		}
		catch (Exception exc) {
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
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listSearches(request, response);
				break;
				
	
				
			case "LOAD":
				loadSearch(request, response);
				//listSearches(request,response);
				break;
				
		
			
	
				
			default:
				listSearches(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void loadSearch(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String title = request.getParameter("title");
		Search theSearch = searchDbUtil.getSearch(title);
		
		
		
		request.setAttribute("THE_SEARCH", theSearch);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-search.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

	private void listSearches(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String title2 = request.getParameter("title");
		List<Search> searches = searchDbUtil.getSearches(title2);
	request.setAttribute("SEARCH_LIST", searches);
		
	RequestDispatcher dispatcher = request.getRequestDispatcher("/list-search.jsp");
	dispatcher.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}
