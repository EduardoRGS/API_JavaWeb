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
		
		<title>Lista de Cliente(s)</title>
		<script type="text/javascript">
			function confimarExclusao(id, nome){
				if(confirm('Deseja realmente excluir o Cliente '+nome+'?')){
					location.href='gerenciar_cliente.do?acao=deletar&idCliente='+id;
				}
			}
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<%@include file="banner.jsp" %>
			<%@include file="menu.jsp" %>
			<h1>Lista de Clientes</h1>
			
			<a href="form_cliente.jsp" class="btn btn-primary">Novo Cadastro</a>
			
			<table class="table table-hover table-striped table-bordered display" id="listarCliente">
			
			
				<thead>
				<tr>
					<th>ID</th>
					<th>Nome / RaZão Social</th>
					<th>CPF / CNPJ</th>
					<th>RG / IE</th>
					<th>Data Nasc / Abertura</th>
					<th>TIpo</th>
					<th>Opções</th>
				</tr>
				</thead>
				
				<tfoot>
				<tr>
					<th>ID</th>
					<th>Nome / RaZão Social</th>
					<th>CPF / CNPJ</th>
					<th>RG / IE</th>
					<th>Data Nasc / Abertura</th>
					<th>TIpo</th>
					<th>Opções</th>
				</tr>
				</tfoot>
				
				<jsp:useBean class="model.ClienteDAO" id="cDAO"/>
				
				<tbody>
				
					<c:forEach var="c" items="${cDAO.lista}">
					<tr>
						<td>${c.idCliente}</td>
						<td>${c.nomeRazao}</td>
						<td>${c.cpfCnpj}</td>
						<td>${c.rgIe}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${c.dataNascAbertura}"/></td>
						<td>
							<c:if test="${c.tipo==1}">
								Física
							</c:if>
							<c:if test="${c.tipo==2}">
								Jurídica
							</c:if>
						
						</td>
					
						
						
						<td>
							<a class="btn btn-primary" href="gerenciar_cliente.do?acao=alterar&idCliente=${c.idCliente}">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
	 							<path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
								</svg>
							</a>
							
							<button class="btn btn-danger" onclick="confimarExclusao(${c.idCliente},'${c.nomeRazao}')">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
	  							<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
							  <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
								</svg>
							</button>
							
						<a href="form_venda.jsp?acao=novo&idCliente=${c.idCliente}" class="btn btn-info">
							<i class="glychicon glyphicon">Realizar Venda</i>
						</a>
							
						</td>
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
						$("#listarCliente").DataTable({
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