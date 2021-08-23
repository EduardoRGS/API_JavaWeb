<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Venda" %>
<%@ page import="model.Cliente" %>
<%@ page import="model.ClienteDAO" %>
<%@ page import="model.VendaProduto" %>
<%@ page import="java.util.ArrayList" %>
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
		
		<title>finalizar Venda</title>
		<script type="text/javascript">
			function excluir(index, item){
				if(confirm('Deseja realmente excluir o item '+item+'?')){
					window.open("gerenciar_carrinho.do?acao=del&index="+index,"_self");
				}
			}
		</script>
	</head>
	<body>
		<div class="container-fluid">
		
			<%@include file="banner.jsp" %>
			<%@include file="menu.jsp" %>
			
			<h1>Finalizar Venda</h1>
			
			<%
				Venda v = new Venda();
				Cliente c = new Cliente();
				
				try{
					
					v = (Venda)session.getAttribute("venda");
					
				}catch (Exception e){
					out.print("Erro "+e);
				
				}
			
			%>
			
			<br /> <br />
			Vendedor: <%=v.getVendedor().getNome()%>
			<br />
			Cliente: <%=v.getCliente().getNomeRazao() %><br />
			
			<table class="table table-striped table-bordered table-hover display" id="listarVendas">
				
				<thead>
					<tr>
					
						<th>Item</th>
						<th>Produto</th>
						<th>QTD</th>
						<th>Valor</th>
						<th>Total</th>
						<th>Remover</th>
					</tr>
				</thead>
				
				<tbody>
					<%
						double total = 0;
						int cont = 0;
						
						for(VendaProduto vp:v.getCarrinho()){
							
						
					
					%>
					<tr>
						<td align="center"><%=cont+1%></td>
						<td><%=vp.getProduto().getNome()%></td>
						<td><%=vp.getQtd()%></td>
						<td>R$ <%=vp.getValorVendido() %></td>
						<td>R$ <%=vp.getQtd()*vp.getValorVendido() %></td>
						<td align="center">
							<a href="#" onclick="excluir(<%=cont%>, <%=cont+1%>)" class="btn btn-danger">
								<i>
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
	  							<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
								<path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
								</svg>
								</i>
							</a>
						</td>
					</tr>
						<% 
							total = total +(vp.getQtd()*vp.getValorVendido());
							cont++;
							}
						%>
				</tbody>
				
			</table>
			
			
			<a href="listar_cliente.jsp" class="btn btn-warning">Cancelar</a>
			<a href="form_venda.jsp?acao=c" class="btn btn-primary">Continuar Vendendo</a>
			<a href="gerenciar_venda.do" class="btn btn-success">Confirmar Venda</a>
		</div>
		
				<script type="text/javascript" src="datatables/jquery.js"></script>
				<script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
				<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
				<script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
				<script type="text/javascript">
					$(document).ready(function() {
						$("#listarVendas").DataTable({
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
	</body>
</html>