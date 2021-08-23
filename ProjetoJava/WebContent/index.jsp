<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no", name="viesport"/>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="bootstrap/bootstrap-theme.min.css"/>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
		
		<title>JSP Page</title>
	</head>
	<body>
		<div class="container-fluid">
			<%@include file="banner.jsp" %>
			<%@include file="menu.jsp" %>
			<h1>Olá Bem Vindo(a) ao meu projeto de JavaWeb</h1>
			
			<h4>Contato:
				<br/>
				<a href="https://github.com/EduardoRGS">Meu Github</a>
				<br/>
				<a href="https://www.linkedin.com/in/eduardo-ribeiro-b5aa94198/">Meu Linkedin</a>
			</h4>
		</div>
	</body>
</html>