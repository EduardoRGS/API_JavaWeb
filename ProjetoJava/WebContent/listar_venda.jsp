<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ClienteDAO" %>
<%@ page import="model.Cliente" %>
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
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
		
		<link rel="stylesheet" href="datatables/jquery.dataTables.min.css"/>
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js">
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
		
		<title>Lista de Venda(s)</title>
		
		
	</head>
	<body>
		<div class="container-fluid">
			<%@include file="banner.jsp" %>
			<%@include file="menu.jsp" %>
			<h1>Lista de Vendas</h1>
			
			<a href="form_finalizar_venda.jsp" class="btn btn-primary">Novo Cadastro</a>
			
			<table class="table table-hover table-striped table-bordered display" id="listarVenda">
			
			
				<thead>
				<tr>
					<th>ID</th>
					<th>Data</th>
					<th>Cliente</th>
					<th>Usuario</th>
				
				</tr>
				</thead>
				
				<tfoot>
				<tr>
					<th>ID</th>
					<th>Data</th>
					<th>Cliente</th>
					<th>Usuario</th>
				
				</tr>
				</tfoot>
				
				<jsp:useBean class="model.VendaDAO" id="vDAO"/>
				
				<tbody>
				
					<c:forEach var="v" items="${vDAO.lista}">
					<tr>
						<td>${v.idVenda}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${v.dataVenda}"/></td>
						<td>${v.cliente.nomeRazao}</td>
						<td>${v.vendedor.nome}</td>
						
					</tr>
					</c:forEach>
				
				</tbody>
			</table>
				
				<script type="text/javascript" src="datatables/jquery.js"></script>
				<script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
				<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
				<script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
				<script type="text/javascript">
					$(document).ready(function() {
						$("#listarVenda").DataTable({
							"bJQueryUI": true,
							"oLanguage":{
								"sProcessing":"Processando...",
								"sLengthMenu":"Mostrar _MENU_registros",
								"sZeroRecords": "Não foram encontrados resultados",
								"sInfo": "Mostrar de _START_ até _END_ de _TOTAL_ registros",
								"sInfoEmpty":"Mostrando de 0 ate 0 de resgistros",
								"sInfoFiltered":"",
								"sInfoPostFix":"",
								"sSearch": "Pesquisar",
								"sUrl":"",
								"oPaginate":{
									"sFirst":"Primeiro",
									"sPrevious":"Anterior",
									"sNext":"Próximo",
									"sLast":"Último"
								}
							}
						});
					});
				</script>
				
		</div>
	</body>
</html>