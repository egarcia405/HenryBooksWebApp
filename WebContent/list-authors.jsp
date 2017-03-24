<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head><title>Henry Books: Authors</title></head>
	<link type="text/css" rel="stylesheet" href="css/style.css">

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Henry Books Author List</h2>
		</div>
	</div>
<div id="container">

<div id="content">
<input type ="button" value = "Add Author" onclick="window.location.href='add-author-form.jsp'; return false;"
				   class="add-student-button"/>
<table>
<tr>
<th>Author Number</th>
<th>Last Name</th>
<th>First Name</th>
</tr>
<c:forEach var = "tempAuthor" items = "${AUTHOR_LIST}">
<c:url var= "tempLink" value="AuthorControllerServlet">
	<c:param name ="command" value = "LOAD" />
	<c:param name = "authorNum" value = "${tempAuthor.authorNum}"/>
</c:url>

<c:url var = "deleteLink" value = "AuthorControllerServlet">
	<c:param name ="command" value = "DELETE" />
	<c:param name = "authorNum" value = "${tempAuthor.authorNum}"/>
</c:url>
<tr>
<td> ${tempAuthor.authorNum} </td>
<td> ${tempAuthor.authorLast} </td>
<td> ${tempAuthor.authorFirst} </td>
<td>
<a href = "${tempLink}">Update</a>
|
<a href = "${deleteLink}" 
onclick="if (!(confirm('Are you sure you want to delete this author?'))) return false">
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