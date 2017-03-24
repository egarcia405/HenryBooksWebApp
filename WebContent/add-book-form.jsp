<html>
<head><title>Add Book</title></head>

<link type ="text/css" rel = "stylesheet" href="css/style.css">
<link type = "text/css" rel="stylesheet" href="css/add-student-style.css">
<body>
<div id="wrapper">
	<div id="header">
		<h2>Henry Books : Add a new book</h2>
	</div>
</div>

<div id="container">
<h3> Fill out info for new book</h3>
<form action="BookControllerServlet" method = "GET">
<input type="hidden" name="command" value="ADD"/>

<table>
<tbody>
<tr>
<td><label>Book Code:</label></td>
<td><input type= "text" name ="bookCode"/></td>
</tr>
<tr>
<td><label>Title:</label></td>
<td><input type= "text" name ="title"/></td>
</tr>
<tr>
<td><label>Publisher Code:</label></td>
<td><input type= "text" name ="publisherCode"/></td>
</tr>
<tr>
<td><label>Book Type:</label></td>
<td><input type= "text" name ="type"/></td>
</tr>
<tr>
<td><label>Paper Back? (Enter Y for "yes"" or N for "no"):</label></td>
<td><input type= "text" name ="paperback"/></td>
</tr>
<tr>
<td><label></label></td>
<td><input type="submit" value="Save" class="save"/></td>
</tr>
</tbody>
</table>


</form>
<div style ="clear:both;"></div>
<p>
<a href="BookControllerServlet">Back to Book List</a>
</p>


</div></body></html>