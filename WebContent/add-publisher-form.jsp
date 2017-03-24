<!DOCTYPE html>
<html>

<head>
	<title>Add Publisher</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Henry Books : Add a Publisher</h2>
		</div>
	</div>
	<div id="container">
		<h3>Fill info for Publisher</h3>
		
		<form action="PublisherControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
				<table>
				<tbody>
					<tr>
						<td><label>Publisher Code:</label></td>
						<td><input type="text" name="pubCode" /></td>
					</tr>

					<tr>
						<td><label>Publisher Name:</label></td>
						<td><input type="text" name="publisherName" /></td>
					</tr>

					<tr>
						<td><label>City:</label></td>
						<td><input type="text" name="city" /></td>
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
			<a href="PublisherControllerServlet">Back to List</a>
		</p>
			
			</div>
	
	
	</body></html>