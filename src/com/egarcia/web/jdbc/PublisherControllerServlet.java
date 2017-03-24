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
 * Servlet implementation class PublisherControllerServlet
 */
@WebServlet("/PublisherControllerServlet")
public class PublisherControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PublisherDbUtil publisherDbUtil;
       
	@Resource(name="jdbc/HenryBooksWebApp1")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			publisherDbUtil = new PublisherDbUtil(dataSource);
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
				listPublishers(request, response);
				break;
				
			case "ADD":
				addPublisher(request, response);
				break;
				
			case "LOAD":
				loadPublisher(request, response);
				break;
				
			case "UPDATE":
				updatePublisher(request, response);
				break;
			
			case "DELETE":
				deletePublisher(request, response);
				break;
				
			default:
				listPublishers(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void listPublishers(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Publisher> publishers = publisherDbUtil.getPublishers();
		
		// add students to the request
		request.setAttribute("PUBLISHER_LIST", publishers);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-publishers.jsp");
		dispatcher.forward(request, response);
		
	}

	private void deletePublisher(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	String thePubisherId = request.getParameter("publisherId");
	publisherDbUtil.deletePublisher(thePubisherId);
	listPublishers(request,response);
	
		
	}

	private void updatePublisher(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// read student info from form data
		
		String pubCode = request.getParameter("pubCode");
		String publisherName = request.getParameter("publisherName");
		String city = request.getParameter("city");
		
		// create a new student object
		Publisher thePublisher = new Publisher(pubCode, publisherName, city);
		
		// perform update on database
		publisherDbUtil.updatePublisher(thePublisher);
		
		// send them back to the "list students" page
		listPublishers(request, response);
		
	}

	private void loadPublisher(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// read student id from form data
		String thePubCode = request.getParameter("publisherId");
		
		// get student from database (db util)
		Publisher thePublisher = publisherDbUtil.getPublisher(thePubCode);
		
		// place student in the request attribute
		request.setAttribute("THE_PUBLISHER", thePublisher);
		
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-publisher-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addPublisher(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pubCode = request.getParameter("pubCode");
		String publisherName = request.getParameter("publisherName");
		String city = request.getParameter("city");		
		
		// create a new student object
		Publisher thePublisher = new Publisher(pubCode, publisherName, city);
		
		// add the student to the database
		publisherDbUtil.addPublisher(thePublisher);
				
		// send back to main page (the student list)
		listPublishers(request, response);
		
	}

/*	private void listPublisher(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}
