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
		
		<title>JSP Page</title>
	</head>
	<body>
		<div class="container-fluid">
		
			<%@include file="banner.jsp" %>
			<%@include file="menu.jsp" %>
			
			<h1>Cadastrar Produto</h1>
			
			<form action="gerenciar_produto.do" method="POST">
			
				<input type="hidden" name="idProduto" value="${produto.idProduto}"/>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="nome" class="control-label">Nome</label>
						<input type="text" class="form-control" id="nome" name="nome" required="" value="${produto.nome}" maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="qtd" class="control-label">Qtd</label>
						<input type="text" class="form-control" id="qtd" name="qtd" required="" value="${produto.qtd}"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="valor" class="control-label">Valor</label>
						<input type="text" class="form-control" id="valor" name="valor" value="<fmt:formatNumber pattern="#,##0.00" value="${produto.valor}"/>" maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					
				</div>
				
				<div class="row">
					<span style="fontsize: 10px">
						<button class="btn btn-success">Gravar</button>
						<a href="listar_produto.jsp" class="btn btn-warning">Voltar</a>
					</span>
				</div>
				
			</form>
			
		</div>
	</body>
</html>