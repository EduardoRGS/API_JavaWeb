<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
			<title>JSP Page</title>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="bootstrap/bootstrap-theme.min.css"/>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
		
	</head>
	<body>
		<h1>Formulário de Login</h1>
		<%
			String msg = (String)request.getSession().getAttribute("msg");
			
			if(msg != null){
				
				request.getSession().removeAttribute("msg");
			
		%>
		<div class="alert alert-info"><%=msg%></div>
		<%
			}
		%>
		<form action="gerenciar_login.do" method="POST">
				
				<div class="row">
					<div class="form-group col-sm-8">
						<label for="login" class="control-label">Login</label>
							<input type="text" name="login" id="login" value="" required="">
					</div>
				
				</div>
				
				<div class="row">
					<div class="form-group col-sm-8">
						<label for="senha" class="control-label">Senha</label>
							<input type="password" name="senha" id="senha" value="" required="">
					</div>
				
				</div>
				
				<div class="row">
					<span style="fontsize: 10px">
						<button class="btn btn-success">Entrar</button>
					</span>
				</div>
			
		</form>
		
	</body>
</html>