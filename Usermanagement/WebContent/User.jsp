<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% 
    session.setAttribute("statusMsg","");
    System.out.println("Trying to process....");
    
    
    if (request.getParameter("name") != null) 
    { 
    	 User projectObj = new User(); 
    	 String stsMsg = ""; 
    	//Insert--------------------------
    	if (request.getParameter("hidItemIDSave") == "") 
    	 { 
    		stsMsg = projectObj.insertItem(request.getParameter("username"), 
    				request.getParameter("institue"),
    				request.getParameter("phone"),  
    				request.getParameter("email"),
    				request.getParameter("password")); 
    	 } 
    	else//Update----------------------
    	 { 
    	 stsMsg = projectObj.updateItem(request.getParameter("hidItemIDSave"), 
    			 	request.getParameter("username"),
    			 	request.getParameter("institue"),
 					request.getParameter("phone"),  
 					request.getParameter("email"),
 					request.getParameter("password")); 
    	 
    	 } 
    	 session.setAttribute("statusMsg", stsMsg); 
    	} 
    	//Delete-----------------------------
    	if (request.getParameter("hidItemIDDelete") != null) 
    	{ 
    	 User projectObj = new User();
    	 String stsMsg = projectObj.deleteItem(request.getParameter("hidItemIDDelete"));
    	 session.setAttribute("statusMsg", stsMsg);
    	 		
    	} %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.css">

<script src="Components/jquery-3.6.0.min.js"> </script> 

<script src="Components/User.js"></script>  

<script>

</script>
</head>
<body>

<div class="row">
    <div class = "col-6">
<h1>User Details</h1>
<form id="formUser" name="formUser" method="post" action="User.jsp">

		Customer Username: 
	<input id="username" name="username" type="text" class="form-control form-control-sm">
   
   <br> Institue: 
   
   <input id="institue" name="institue" type="text" class="form-control form-control-sm">
   
   <br> Phone Number: 
   <input id="phone" name="phone" type="text" class="form-control form-control-sm">
   
   <br> Email:
    <input id="email" name="email" type="text" class="form-control form-control-sm">
    
   
   <br> Password:
    <input id="password" name="password" type="text" class="form-control form-control-sm">
    
    <br>
    <input id="btnSave" name="btnSave" type="button" value="Save"
                class="btn btn-primary">
    
    <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
    
</form>
</div>
</div>


<br>
<div id="alertSuccess" class="alert alert-success">
       <% out.print(session.getAttribute("statusMsg")); %>
</div>
<br>
<div>
			<%
			 User projectObj = new  User();
				out.print(projectObj.readItems());
			%>
</div>

		
		
		<br>

</body>
</html>