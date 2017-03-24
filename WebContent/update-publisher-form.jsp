<!DOCTYPE html>
<html>

<head>
	<title>Update Publisher</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Henry Books  : Update a Publisher</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Fill out info to update Publisher</h3>
		<h6>SPECIAL INSTRUCTIONS : To change publisher code (key) please delete this entry and enter a new one with new publisher code </h6>	
		
		<form action="PublisherControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="pubCode" value="${THE_PUBLISHER.pubCode}" />
			<table>
				<tbody>
					<tr>
						<td><label>Publisher Name:</label></td>
						<td><input type="text" name="publisherName" 
								   value="${THE_PUBLISHER.publisherName}" /></td>
					</tr>

					<tr>
						<td><label>City :</label></td>
						<td><input type="text" name="city" 
								   value="${THE_PUBLISHER.city}" /></td>
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