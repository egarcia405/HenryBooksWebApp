<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE HTML>
<html>
<head><title>HenryBooks : Book List</title>
<link type = "text/css" rel= "stylesheet" href = "css/style.css">

</head>
<body>
<div id="wrapper">
	<div id="header">
	<h2>Henry Books : Books</h2>
	</div>
</div>
<div id="container">
	<div id="content">
	<!--  placing and Add Book button -->
		<input type ="button" value = "Add Book" onclick ="window.location.href='add-book-form.jsp'; return false;"
	class="add-student-button"/>
	<table>
	
	<tr>
	<th>Book Code</th>
	<th>Title</th>
	<th>Publisher Code</th>
	<th>Book Type</th>
	<th>Paperback? (Y or N)</th>
	
	</tr>
	<c:forEach var = "tempBook" items="${BOOK_LIST}">
	<!--  setting link for each book -->
	<c:url var  = "tempLink" value = "BookControllerServlet">
	<c:param name = "command" value= "LOAD"/>
	<c:param name ="bookId" value = "${tempBook.bookCode}"/>     <!-- might need to be changed to temoBook.bookCodeId -->
	</c:url>
	
<%-- 		<c:url var  = "tempLink2" value = "BookControllerServlet">
	<c:param name = "command" value= "UPDATE"/>
	<c:param name ="bookId" value = "${tempBook.bookCode} }"/>     <!-- might need to be changed to temoBook.bookCodeId -->
	</c:url> --%>
	
	
	
	<!--  setting link to delete a book -->
	<c:url var ="deleteLink" value="BookControllerServlet">
	<c:param name ="command" value = "DELETE" />
	<c:param name ="bookId" value="${tempBook.bookCode}"/>
	</c:url>
	<tr>
	<td>${tempBook.bookCode} </td>
	<td>${tempBook.title} </td>
	<td>${tempBook.publisherCode} </td>
	<td>${tempBook.type} </td>
	<td>${tempBook.paperBack} </td>
	<td><a href= "${tempLink}">Update</a>
	|
	<a href="${deleteLink}" onclick ="if (!(confirm('Are you sure you want to delete this book? '))) return false">Delete</a>
	</td>
	</tr>
	</c:forEach>
	</table>


	</div>
	<a href = "MainPage.jsp"> Back to the Main Menu</a>
</div>
</body>
</html>