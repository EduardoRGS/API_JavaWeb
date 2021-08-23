<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			
			<h1>Cadastrar Menu</h1>
			
			<form action="gerenciar_menu.do" method="POST">
			
				<input type="hidden" name="idMenu" value="${menu.idMenu}"/>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="nome" class="control-label">Nome Menu</label>
						<input type="text" class="form-control" id="nome" name="nome" required="" value="${menu.nome}" maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="link" class="control-label">Link</label>
						<input type="text" class="form-control" id="link" name="link" required="" value="${menu.link}"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="icone" class="control-label">Icone</label>
						<input type="text" class="form-control" id="icone" name="icone" value="${menu.icone}" maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="perfil" class="control-label">Exibir</label>
						<select class="form-control" name="exibir">
							<option value="1" 
							
								<c:if test="${menu.exibir==1}">
							 		selected="" 
								 </c:if> 
								 
							>Sim</option>
							
							<option value="2" 
								<c:if test="${menu.exibir==2}">
							 		selected="" 
								 </c:if>
							 >Não</option>
							 
						</select>
					</div>
				</div>
				
				<div class="row">
					<span style="fontsize: 10px">
						<button class="btn btn-success">Gravar</button>
						<a href="listar_menu.jsp" class="btn btn-warning">Voltar</a>
					</span>
				</div>
				
			</form>
			
		</div>
	</body>
</html>