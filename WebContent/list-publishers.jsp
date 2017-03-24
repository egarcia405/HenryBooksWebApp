<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>Henry Books : Publishers</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Henry Books : List of Publishers</h2>
		</div>
	</div>
	<div id= "container">
	<div id = "content">
	
	<input type = "button" value ="Add Publisher" onclick = "window.location.href='add-publisher-form.jsp';return false;"
	class = "add-student-button"/>
	<table>
	<tr>
	<th>publisherCode</th>
	<th>publisherName</th>
	<th>City</th>
	<th>Action</th>
	
	</tr>
	<c:forEach var ="tempPublisher" items ="${PUBLISHER_LIST }">
	<c:url var= "tempLink" value = "PublisherControllerServlet">
	<c:param name = "command" value = "LOAD"/>
	<c:param name = "publisherId" value = "${tempPublisher.pubCode}" />
	
	</c:url>
	
			<c:url var="deleteLink" value="PublisherControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="publisherId" value="${tempPublisher.pubCode}" />
					</c:url>
			<tr>
						<td> ${tempPublisher.pubCode} </td>
						<td> ${tempPublisher.publisherName} </td>
						<td> ${tempPublisher.city} </td>
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this publisher?'))) return false">
							Delete</a>	
						</td>
					</tr>
	
	</c:forEach>
	
	
	
	
	</table>
	
	</div>
	<a href = "MainPage.jsp"> Back to the Main Menu</a>
	</div>
</body></html>