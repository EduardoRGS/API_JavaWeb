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
			
			<h1>Cadastrar Usuário</h1>
			
			<form action="gerenciar_usuario.do" method="POST">
			
				<input type="hidden" name="idUsuario" value="${usuario.idUsuario}"/>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="nome" class="control-label">Nome</label>
						<input type="text" class="form-control" id="nome" name="nome" required="" value="${usuario.nome}" maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="login" class="control-label">Login</label>
						<input type="text" class="form-control" id="login" name="login" required="" value="${usuario.login}" maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="senha" class="control-label">Senha</label>
						<input type="password" class="form-control" id="senha" name="senha" required="" value="${usuario.senha}" maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="status" class="control-label">Status</label>
						<select class="form-control" name="status">
							<option value="1" 
							
								<c:if test="${usuario.status==1}">
							 		selected="" 
								 </c:if> 
								 
							>Ativo</option>
							
							<option value="2" 
								<c:if test="${usuario.status==2}">
							 		selected="" 
								 </c:if>
							 >Inativo</option>
							 
						</select>
					</div>
					
						<div class="form-group col-sm-6">
							<label for="perfil" class="control-label">Perfil</label>
							
							<select class="form-control" name="idPerfil" required="">
							
							<option value="">Selecione o Perfil</option>
							
							<jsp:useBean class="model.PerfilDAO" id="perfil"/>
							
								<c:forEach var="p" items="${perfil.lista}">
								
									<option value="${p.idPerfil}"
										<c:if test="${p.idPerfil==usuario.perfil.idPerfil}">selected=""</c:if>
									>	${p.nome}</option>
								
								</c:forEach>
							</select>
						</div>
					
				
				</div>
				
				<div class="row">
					<span style="fontsize: 10px">
						<button class="btn btn-success">Gravar</button>
						<a href="listar_usuario.jsp" class="btn btn-warning">Voltar</a>
					</span>
				</div>
				
			</form>
			
		</div>
	</body>
</html>