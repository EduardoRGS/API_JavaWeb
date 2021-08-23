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
import model.VendaDAO;
import model.VendaProduto;

/**
 * Servlet implementation class GerenciarVenda
 */
@WebServlet("/gerenciar_venda.do")
public class GerenciarVenda extends HttpServlet {
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
			String msg = "";
		
			
			try {
				
				Venda v = (Venda) session.getAttribute("venda");
				VendaDAO vDAO = new VendaDAO();
					
				if(vDAO.registrar(v)) {
					
					msg = "Venda realizada com sucesso!";
				}else {
						
					msg = "Errona servlet GerecniarVenda metodo GET de cadastrar venda";
				}
				
				
	
			}catch(Exception e) {
			
				out.print(e);
			}
			
			out.println("<script type='text/javascript'>");
			out.println("alert('"+msg+"');");
			out.println("location.href='listar_venda.jsp';");
			out.println("</script>");
			
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
	}

}
