<!DOCTYPE html>
<html>
<head><title>Add Author</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	


</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Henry Books : Add Author</h2>
		</div>
	</div>
<div id="container">
<h3>Fill out info for new Author</h3>
<form action="AuthorControllerServlet" method="GET">
<input type="hidden" name="command" value="ADD" />
<table>
<tbody>


	<tr>
		<td><label>Author's last name:</label></td>
		<td><input type="text" name="authorLast" /></td>
		</tr>

		<tr>
			<td><label>Author's first name:</label></td>
			<td><input type="text" name="authorFirst" /></td>
					</tr>

					<tr>
						<td><label>Author number:</label></td>
						<td><input type="text" name="authorNum" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					




</tbody></table>

</form>
	<div style="clear: both;"></div>
		
		<p>
			<a href="AuthorControllerServlet">Back to List</a>
		</p>


</div>
</html>