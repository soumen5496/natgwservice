<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
  <link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
  <script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <script src="webjars/jquery/3.5.1/jquery.min.js"></script>
  
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<h2>Log In</h2>
${SPRING_SECURITY_LAST_EXCEPTION.message}
<div class="container">

<form action="/login" method="post">
 <table>
 <tr>
 <td>USER:</td>
 <td><input type="text" name="username"></td>
 </tr>
 <tr>
 <td>PASSWORD:</td>
 <td><input type="password" name="password"></td>
 </tr>
 <tr>
 <td> <input type="submit" name="submit" value="submit" class="btn btn-info"></td>
 </tr>
 
 </table>

</form>
<button onclick="myFunction()">Click me</button>

</div>

<script type="text/javascript">
 function myFunction()
 {
	 //alert(sel.value);

	 $.ajax({
	        type: "GET",
	        url: "http://localhost:8093/UC01/PRS?PNR=457845",
	        dataType: 'json',
	        username: "admin",
	        password: "admin",
	        success: function (result) {
	            alert("sucess");
	        },
	        error: function (result) {
	            alert("Error");
	        }
	    });
 }

</script>

</body>
</html>