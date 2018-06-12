<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <title>Bootstrap Example</title>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Online Examination System</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="css/animate.css" rel="stylesheet" />
    <!-- Squad theme CSS -->
    <link href="css/style.css" rel="stylesheet">
	<link href="color/default.css" rel="stylesheet">
 
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
<head>
	<title>available email</title>
	<style>
		div.container {
    width: 100%;
    border: 1px solid gray;
}

header {
    padding: 1em;
    color: white;
    background-color: grey;
    clear: left;
}
footer{
 padding: 1em;
    color: white;
    background-color: grey;
    clear: left;
    text-align: center;
}
.button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 20px;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
  outline: none;
  color: #fff;
  background-color: #4CAF50;
  border: none;
  border-radius: 10px;
  box-shadow: 0 9px black;
}

.button:hover {background-color: #3e8e41}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
  #di{
    border: 10px solid grey;
    background-color:powderblue;
  }
  p {
	  text-align: center;
	  font-size: 30px;
	}
	</style>
</head>
<body >

	<div>
		 <ul class="nav navbar-nav">
        <li class="active"><a href="index.html">Home</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Login Menu <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="index1.jsp">student Login</a></li>
            <li><a href="index2.jsp">Teacher Login</a></li>
          </ul>
        </li>
      </ul>
	</div>
		<p id="time">.</p>
	
  <div id="di">
 <header>
  <h1 id="para2">Choose a email</h1></header>
  <div class="container">
  
  <form action="Add_Email_to_session" method="post">
  <%
  if(session.getAttribute("avable_Email_number")==null)
	{
		session.setAttribute("erro", "No subject available");
		response.sendRedirect("error.jsp");
	}
  %>
  
  	<% 
  		int i=(Integer)session.getAttribute("avable_Email_number");
  		int j;
  		for(j=0;j<i;j++)
  		{
  	%>
  	
  	
    <div class="radio">
      <label><input type="radio" name="Email_Eddress" value="<%= session.getAttribute(j+"Email_of_teacher") %>"><%= session.getAttribute(j+"Email_of_teacher") %> </label>
    </div>
    <% } %>
   	<div class=radio>
   	<input type="submit">
   	</div>
  </form>
</div>
 <footer><p>  
 <form action ="logout" method= post >
 	            <input class="button" type="submit" value="logout" align="left">                         <br>      </p>
    </form> 
 </footer>
 </div>
</body>
</html>