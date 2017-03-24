<!DOCTYPE html>
<html>

<head>
	<title>Add Copy</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Henry Books : Add a Copy</h2>
	
		</div>
	</div>
	<div id ="container">
	<h3>Add info for Copy </h3>
	<form action = "CopyControllerServlet" method ="GET">
	<input type = "hidden" name = "command" value = "ADD"/>
	<table>
	<tbody>
	<tr>
	<td><label>Book Code: </label></td>
	<td><input type = "text" name = "bookCode"/></td></tr>
	<tr>
	<td><label>Branch Number: </label></td>
	<td><input type = "text" name = "branchNum"/></td></tr>
	<tr>
	<td><label>Copy Number: </label></td>
	<td><input type = "text" name = "copyNum"/></td></tr>
	<tr>
	<td><label>Quality</label></td>
	<td><input type = "text" name = "quality"/></td></tr>
	
	<tr>
	<td><label>Price: </label></td>
	<td><input type = "text" name = "price"/></td>
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
			<a href="CopyControllerServlet">Back to List</a>
		</p>
	
	</div>
	
	</body>
	
	</html>