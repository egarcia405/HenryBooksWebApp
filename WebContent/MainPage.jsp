
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>

<link type = "text/css" rel= "stylesheet" href = "css/style.css">
<title>Welcome to Henry Books</title>
</head>
<body>

<div id="wrapper">
	<div id="header">
	<h2>Henry Books Main Menu</h2>
	</div>
</div>
<div id="container">

	<div id="content">
	
	<!--  putting a new  button : Add Student -->
	<input type = "button" value ="View Books" onclick="window.location.href='BookControllerServlet'"
	/>
	<input type = "button" value = "View Authors" onclick = "window.location.href='AuthorControllerServlet'"/>
	
	<input type = "button" value = "View Publishers" onclick = "window.location.href='PublisherControllerServlet'"/>
	
		<input type = "button" value = "View Copies" onclick = "window.location.href='CopyControllerServlet'"/>
	
	
<form action="SearchControllerServlet" method = "GET">
<input type="hidden" name="command" value="LIST"/>

<h3>Search a Book :</h3>
<input type= "text" name ="title"/>

<input type="submit" value="Search" class="save"/>
</form>

</div>
</div>	

</body>
</html>