<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>result table</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
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

<table>
  <tr>
    <th>Test Name</th>
    <th>Total Marks</th>
    <th>Marks Obtain</th>
  </tr>
  <%
  int total =(Integer)session.getAttribute("avable_test_number");		
  %>
  <%
  int i;
  for(i=0;i<total;i++)
  {
	  %>
	  <tr>
	    <td><%= session.getAttribute(i+"test_name_tab") %></td>
	    <td><%= session.getAttribute(i+"max_mark") %></td>
	    <td><%= session.getAttribute(i+"marks_obtain") %></td>
	  </tr>
	
	  <%
  }
  %> 
 <footer><p>
   <form action ="go_to_home" method= post >
           <input class="button" type="submit" value="go to home" align="left">   
          </form> 
 </footer>
</table>
</body>
</html>