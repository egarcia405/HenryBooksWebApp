<!DOCTYPE html>
<html>

<head>
	<title>Update Copy</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Henry Books: Update a Copy</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Fill info for Copy</h3>
		<h6> SPECIAL INSTRUCTIONS:  To change book code, branch number, or copy number of this copy, go back to the list and click on "Add Copy" with new information 
		Make sure to delete this entry to avoid a duplicate entry error. </h6>
			<form action="CopyControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="bookCode" value="${THE_COPY.bookCode}" />
			<input type="hidden" name="branchNum" value="${THE_COPY.branchNum}" />
			<input type="hidden" name="copyNum" value="${THE_COPY.copyNum}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Quality:  </label></td>
						<td><input type="text" name="quality" 
								   value="${THE_COPY.quality}" /></td>
					</tr>

					<tr>
						<td><label>Price:</label></td>
						<td><input type="text" name="price" 
								   value="${THE_COPY.price}" /></td>
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