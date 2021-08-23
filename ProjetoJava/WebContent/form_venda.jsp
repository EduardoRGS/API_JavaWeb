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
		
		<title>Venda</title>
	</head>
	<body>
		<div class="container-fluid">
		
			<%@include file="banner.jsp" %>
			<%@include file="menu.jsp" %>
			
			<h1>Cadastrar Nova Venda</h1>
			
			<%
				Venda v = new Venda();
				Cliente c = new Cliente();
				
				try{
					
					ClienteDAO cDAO = new ClienteDAO();
					
					String acao = request.getParameter("acao");
					
					if(acao.equals("novo")){
						
						int idCliente = Integer.parseInt(request.getParameter("idCliente"));
						
						c = cDAO.getCarregaPorID(idCliente);
						v.setCliente(c);
						v.setVendedor(ulogado);
						v.setCarrinho(new ArrayList<VendaProduto>());
						session.setAttribute("venda", v);
						
						
					}else{
						v = (Venda)session.getAttribute("venda");
					}
				}catch (Exception e){
					out.print("Erro: "+e);
				
				}
			
			%>
			
			<br />
			Vendedor: <%=v.getVendedor().getNome()%>
			<br />
			Cliente: <%=v.getCliente().getNomeRazao()%><br />
			<h4>Catálogo: (<%=v.getCarrinho().size()%> itens do Carrinho)</h4>
			
			<jsp:useBean class="model.ProdutoDAO" id="produto"/>
			<c:forEach var="p" items="${produto.lista}">
				<div id="prod${p.idProduto}">
				
					<form action="gerenciar_carrinho.do" method="GET">
					
						<input type="hidden" name="acao" value="add">
						
						<input type="hidden" name="idProduto" value="${p.idProduto}">
						
						${p.nome}
						
						<input type="number" name="qtd" value="1" style="width: 40px">
						
						<button class="btn btn-default">
						
							<i type="button" class="btn btn-info">Comprar
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart3" viewBox="0 0 16 16">
 									 <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
									</svg>
							</i>
							
						</button>
					
					</form>
				
				</div>
			</c:forEach>
			
			<a href="listar_cliente.jsp" class="btn btn-warning">Cancelar</a>
			<a href="form_finalizar_venda.jsp" class="btn btn-success">Finalizar Venda</a>
			
		</div>
	</body>
</html>