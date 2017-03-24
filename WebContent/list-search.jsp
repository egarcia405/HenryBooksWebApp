<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Henry Books : Search</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Henry Books: Search a Book</h2>
		</div>
	</div>
	<div id="container">
	<div id = "content">
	
		<table>
			
				<tr>
					<th>Title</th>
					<th>On Hand</th> 
					<th>Branch Name</th>
					<th>Author Last Name</th>
					<th>Author First Name</th>
					<th>Publisher Name</th>
				</tr>
	<c:forEach var ="tempSearch" items="${SEARCH_LIST}">
	<c:url var = "tempLink" value = "SearchControllerServlet">
		<c:param name="command" value ="LIST"/>
		<c:param name = "title" value = "${tempLink.title}"/>
	</c:url>
	
	<tr>
						<td> ${tempSearch.title} </td>
						<td> ${tempSearch.onHand} </td> 
						<td> ${tempSearch.branchName} </td>
						<td> ${tempSearch.authorLast} </td>
						<td> ${tempSearch.authorFirst} </td>
						<td> ${tempSearch.publisherName} </td>
					
						</tr>
	
	
	</c:forEach>
	</table>
	</div>
	<a href = "MainPage.jsp"> Back to the Main Menu</a>
	</div>
	
	</body></html>