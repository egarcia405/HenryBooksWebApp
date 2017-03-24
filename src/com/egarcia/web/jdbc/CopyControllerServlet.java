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
 * Servlet implementation class CopyControllerServlet
 */
@WebServlet("/CopyControllerServlet")
public class CopyControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CopyDbUtil copyDbUtil;
	
	@Resource(name="jdbc/HenryBooksWebApp1")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			copyDbUtil = new CopyDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
       
    /**
     * 
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
				listCopies(request, response);
				break;
				
			case "ADD":
				addCopy(request, response);
				break;
				
			case "LOAD":
				loadCopy(request, response);
				break;
				
			case "UPDATE":
				updateCopy(request, response);
				break;
			
			case "DELETE":
				deleteCopy(request, response);
				break;
				
			default:
				listCopies(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void deleteCopy(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String theBookCodeId = request.getParameter("bookCode");
		String theBranchNum =  request.getParameter("branchNum");
		String theCopyNum = request.getParameter("copyNum");

		copyDbUtil.deleteCopy(theBookCodeId, theBranchNum, theCopyNum);
		
		listCopies(request,response);
		
	}

	private void updateCopy(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bookCode = request.getParameter("bookCode");
		int branchNum = Integer.parseInt(request.getParameter("branchNum"));
		int copyNum = Integer.parseInt(request.getParameter("copyNum"));
		String quality = request.getParameter("quality");
		double price = Double.parseDouble(request.getParameter("price"));
		
		Copy theCopy = new Copy(bookCode,branchNum,copyNum, quality, price);
		
		copyDbUtil.updateCopy(theCopy);
		
		listCopies(request,response);
		
		
	}

	private void loadCopy(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String theBookCodeId = request.getParameter("bookCode");
		String theBranchNumId = request.getParameter("branchNum");
		String theCopyNumId= request.getParameter("copyNum");
		Copy theCopy = copyDbUtil.getCopy(theBookCodeId, theBranchNumId, theCopyNumId);
		request.setAttribute("THE_COPY", theCopy);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-copy-form.jsp");
		dispatcher.forward(request, response);		
		
	}

	private void addCopy(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String bookCode = request.getParameter("bookCode");
		int branchNum = Integer.parseInt(request.getParameter("branchNum"));
		int copyNum = Integer.parseInt(request.getParameter("copyNum"));
		String quality = request.getParameter("quality");
		double price = Double.parseDouble(request.getParameter("price"));
		
		Copy theCopy = new Copy(bookCode,branchNum,copyNum,quality,price);
		
		copyDbUtil.addCopy(theCopy);
		listCopies(request,response);
		
		
		
	}

	private void listCopies(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		List<Copy> copies = copyDbUtil.getCopies();
		
		request.setAttribute("COPY_LIST", copies);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-copies.jsp");
		dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}
