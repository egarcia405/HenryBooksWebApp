<!DOCTYPE html>

<html>
<head><title>Update: Book</title></head>
<link type ="text/css" rel = "stylesheet" href="css/style.css">
<link type = "text/css" rel="stylesheet" href="css/add-student-style.css">
<body>
<div id="wrapper">
	<div id="header">
		<h2>Henry Books : Update a Book</h2>
	</div>
</div>
<div id="container">
<h3>Enter new info for current Book</h3>
<h6>SPECIAL INSTRUCTIONS : To change Book Code (key) please delete this entry and enter a new one with new Book Code </h6>	
<form action = "BookControllerServlet" method="GET">
<input type="hidden" name="command" value="UPDATE"/>
<input type="hidden" name="bookCode" value="${THE_BOOK.bookCode}"/>

<table>
<tbody>

<tr>
<td><label>Title:</label></td>
<td><input type="text" name ="title" value="${THE_BOOK.title }"/></td>
</tr>
<tr>
<td><label>Publisher Code:</label></td>
<td><input type="text" name ="publisherCode" value="${THE_BOOK.publisherCode}"/></td>
</tr>
<tr>
<td><label>Book Type:</label></td>
<td><input type="text" name ="type" value="${THE_BOOK.type }"/></td>
</tr>
<tr>
<td><label>Paperback? (Y for "yes" N for "no"):</label></td>
<td><input type="text" name ="paperback" value="${THE_BOOK.paperBack}"/></td>
</tr>
<tr>
<td><label></label></td>
<td><input type="submit" value="Save" class="save"/></td>


</tr>

</tbody>
</table>
</form>
<div style="clear:both;"></div>
<p>
<a href="BookControllerServlet">Back to Books</a>
</p>
</div>










</body>

</html>