<!DOCTYPE html>
<html>

<head>
	<title>Update Author</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2> Henry Books : Update an Author</h2>
		</div>
	</div>
	<div id = "container">
	<h3>Fill in new info for Author:</h3>
	<h6>SPECIAL INSTRUCTIONS : To change Author Number (key) please delete this entry and enter a new one with new Author Number </h6>	
	<form action="AuthorControllerServlet" method="GET">
		<input type="hidden" name="command" value="UPDATE" />

		<input type="hidden" name="authorNum" value="${THE_AUTHOR.authorNum}" />
		<table>
		<tbody>
		<tr>
		<td><label>Author's last name:</label></td>
		<td><input type = "text" name="authorLast" value="${THE_AUTHOR.authorLast}"/></td>	
		</tr>
		<tr>
		<td><label>Author's First name:</label></td>
		<td><input type = "text" name="authorFirst" value="${THE_AUTHOR.authorFirst}"/></td>	
		</tr>
		
			<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
		
		</tbody>
		</table>
	
	</form>
		<div style="clear: both;"></div>
		
		<p>
			<a href="AuthorControllerServlet">Back to List</a>
		</p>
	
	</div>
	
	
	</body>
	</html>