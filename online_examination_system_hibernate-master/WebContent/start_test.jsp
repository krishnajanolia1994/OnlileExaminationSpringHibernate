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
	<title>start test</title>
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
<body>
<%
	int total_sec=(Integer) session.getAttribute("time_in_sec");
	int sec=total_sec % 60;
	int min=total_sec / 60;
	min%=60;
	int hour=total_sec / 3600;
%>

	<div>
		 <ul class="nav navbar-nav">
        <li class="active"><a href="#intro">Home</a></li>
         <li class="active"><a href="student_logout.jsp">logout</a></li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Login Menu <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="index1.jsp">student Login</a></li>
            <li><a href="index2.jsp">Teacher Login</a></li>
          </ul>
        </li>
      </ul>
      <p>
      <span>time left</span>
      <span id ="h"><%= hour %></span>
      <span id ="m"><%= min %></span>
      <span id ="s"><%= sec %></span>
      </p>
	</div>
  <div id="di">
 <header>
  <h1 id="para2">START YOUR TEST</h1></header>
  <div class="container">
  <form id = "test" action="Evalute_result" method="post">
  
  </h1>
  	<% 
  		int i=(Integer)session.getAttribute("avable_question_option");
  		int j;
  		for(j=0;j<i;j+=5)
  		{ 
  			int q=j;
  			int o1=j+1;
  			int o2=j+2;
  			int o3=j+3;
  			int o4=j+4;
  	%>
  	
  	<h4><%= session.getAttribute(q+"Question") %></h4>
    <div class="radio"> 
      <label><input type="radio" name=<%=q+"Question" %>  value="<%= session.getAttribute(o1+"OptionI") %>"><%= session.getAttribute(o1+"OptionI") %> </label>
    </div>
    <div class="radio">
      <label><input type="radio" name=<%=q+"Question" %>  value="<%= session.getAttribute(o2+"OptionII") %>"><%= session.getAttribute(o2+"OptionII") %> </label>
    </div>
    <div class="radio">
      <label><input type="radio" name=<%=q+"Question" %>  value="<%= session.getAttribute(o3+"OptionIII") %>"><%= session.getAttribute(o3+"OptionIII") %> </label>
    </div>
    <div class="radio">
      <label><input type="radio" name=<%=q+"Question" %> value="<%= session.getAttribute(o4+"OptionIV") %>"><%= session.getAttribute(o4+"OptionIV") %> </label>
    </div><br>
    
    <% } %>
   	<div class=radio>
   	<input type="submit">
   	</div>
  </form>
</div>
 <footer>
 <br><br>
 </footer>
 </div>
 <script >
 
 var h=<%=hour %>
 
 var m=<%=min %>;
 var s=<%=sec %>;
  function update()
 {
	 if(s==0)
		 {
		 	if(m>0)
		 		{
		 		s=59;
			 	m--;
		 		}
		 	
		 	if(m==0)
		 		{
		 		if(h>0)
		 			{
		 			m=59;
		 			h--;
		 			}
		 		
		 			if(h==0)
		 				if(m==0)
		 					if(s==0)
		 						document.getElementById("test").submit();
		 		}
		 
		 }
	 else
		 s--;
	 document.getElementById("h").innerHTML=h+" : ";
	 document.getElementById("m").innerHTML=m+" : ";
	 document.getElementById("s").innerHTML=s;
 }
 window.onload = setInterval(update , 1000);
 </script>
</body>
</html>
