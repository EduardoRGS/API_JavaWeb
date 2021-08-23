<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no", name="viesport"/>
		
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="bootstrap/bootstrap-theme.min.css"/>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		
		<title>Cadastro de Cliente</title>
	</head>
	<body>
		<div class="container-fluid">
		
			<%@include file="banner.jsp" %>
			<%@include file="menu.jsp" %>
			
			<h1>Cadastrar Cliente</h1>
			
			<form action="gerenciar_cliente.do" method="POST">
			
				<input type="hidden" name="idCliente" value="${cliente.idCliente}"/>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="nomeRazao" class="control-label">Nome / Razão Social</label>
						<input type="text" class="form-control" id="nomeRazao" name="nomeRazao" required="" value="${cliente.nomeRazao}" maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="cpfCnpj" class="control-label">CPF / CNPJ</label>
						<input type="text" class="form-control" id="cpfCnpj" name="cpfCnpj" required="" value="${cliente.cpfCnpj}" maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="rgIe" class="control-label">RG / IE</label>
						<input type="text" class="form-control" id="rgIe" name="rgIe" required="" value="${cliente.rgIe}" maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="dataNascAbertura" class="control-label">Data de Nascimento / Abertura</label>
						<input type="text" class="form-control" id="dataNascAbertura" name="dataNascAbertura" required="" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${cliente.dataNascAbertura}"/>" maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="tipo" class="control-label">Tipo</label>
						<select class="form-control" name="tipo">
							<option value="1" 
							
								<c:if test="${cliente.tipo==1}">
							 		selected="" 
								 </c:if> 
								 
							>Física</option>
							
							<option value="2" 
								<c:if test="${cliente.tipo==2}">
							 		selected="" 
								 </c:if>
							 >Jurídica</option>
							 
						</select>
					</div>
				</div>
				
				<div class="row">
					<span style="fontsize: 10px">
						<button class="btn btn-success">Gravar</button>
						<a href="listar_cliente.jsp" class="btn btn-warning">Voltar</a>
					</span>
				</div>
				
			</form>
			
		</div>
	</body>
</html>