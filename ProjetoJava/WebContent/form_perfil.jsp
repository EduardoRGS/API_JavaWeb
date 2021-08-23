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
		
		<title>JSP Page</title>
	</head>
	<body>
		<div class="container-fluid">
		
			<%@include file="banner.jsp" %>
			<%@include file="menu.jsp" %>
			
			<h1>Cadastrar Perfil</h1>
			
			<form action="gerenciar_perfil.do" method="POST">
			
				<input type="hidden" name="idPerfil" value="${perfil.idPerfil}"/>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="perfil" class="control-label">Nome Perfil</label>
						<input type="text" class="form-control" id="nome" name="nome" required="" value="${perfil.nome}" maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					<span style="fontsize: 10px">
						<button class="btn btn-success">Gravar</button>
						<a href="listar_Perfil.jsp" class="btn btn-warning">Voltar</a>
					</span>
				</div>
				
			</form>
			
		</div>
	</body>
</html>