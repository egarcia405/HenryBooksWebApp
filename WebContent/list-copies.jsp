<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Henry Books : Copy</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Henry Books : List of Copies</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		<input type="button" value="Add Copy" 
				   onclick="window.location.href='add-copy-form.jsp'; return false;"
				   class="add-student-button"
			/>
					<table>
			
				<tr>
					<th>Book Code</th>
					<th>Branch Number</th>
					<th>Copy Number</th>
				<th>Quality</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempCopy" items="${COPY_LIST}">
					
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="CopyControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="bookCode" value="${tempCopy.bookCode}" />
						<c:param name="branchNum" value = "${tempCopy.branchNum }"/>
						<c:param name = "copyNum" value = "${tempCopy.copyNum }"/>
					</c:url>

					<!--  set up a link to delete a student -->
					<c:url var="deleteLink" value="CopyControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="bookCode" value="${tempCopy.bookCode}" />
						<c:param name="branchNum" value = "${tempCopy.branchNum }"/>
						<c:param name = "copyNum" value = "${tempCopy.copyNum }"/>
					</c:url>
																		
					<tr>
						<td> ${tempCopy.bookCode} </td>
						<td> ${tempCopy.branchNum} </td>
						<td> ${tempCopy.copyNum} </td>
						<td>${tempCopy.quality}</td>
						<td>${tempCopy.price}</td>
						
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
				</table>
				
				
		</div>
		<a href = "MainPage.jsp"> Back to the Main Menu</a>
		</div>
		</body>
		</html>
		