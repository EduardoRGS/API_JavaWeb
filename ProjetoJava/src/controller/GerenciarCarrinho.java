package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Produto;
import model.ProdutoDAO;
import model.Venda;
import model.VendaProduto;

/**
 * Servlet implementation class GerenciarCarrinho
 */
@WebServlet("/gerenciar_carrinho.do")
public class GerenciarCarrinho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try (PrintWriter out = response.getWriter()) {
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet GerenciarCarrinho</title>");
			out.println("</head>");
			out.println("<body>");
			
			HttpSession session = request.getSession();
			try {
				
				Venda v = (Venda)session.getAttribute("venda");
				ArrayList<VendaProduto> carrinho = v.getCarrinho();
			
				String acao = request.getParameter("acao");
				ProdutoDAO pDAO = new ProdutoDAO();
				
				if(acao.equals("add")) {
					
					Produto p = new Produto();
					int idProduto = Integer.parseInt(request.getParameter("idProduto"));
					
					p = pDAO.getCarregaPorID(idProduto);
					
					int qtd = Integer.parseInt(request.getParameter("qtd"));
					
					VendaProduto vp = new VendaProduto();
					
					vp.setProduto(p);
					vp.setQtd(qtd);
					vp.setValorVendido(p.getValor());
					carrinho.add(vp);
					v.setCarrinho(carrinho);
					
					
					session.setAttribute("venda", v);
					response.sendRedirect("form_venda.jsp?acao=c");
					
					
				}else if(acao.equals("del")){
					
					int index = Integer.parseInt(request.getParameter("index"));
					
					carrinho.remove(index);
					v.setCarrinho(carrinho);
					
					session.setAttribute("venda", v);
					response.sendRedirect("form_finalizar_venda.jsp");
				}
				
			}catch(Exception e) {
				out.print(e);
			}
			
			out.println("<h1>Servlet GerenciarCarrinho at "+ request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
