package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Produto;
import model.ProdutoDAO;

/**
 * Servlet implementation class GerenciarProduto
 */
@WebServlet("/gerenciar_produto.do")
public class GerenciarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciarProduto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String acao = request.getParameter("acao");
		String idProduto = request.getParameter("idProduto");
		
		String msg = "";
		
		try {
			
			Produto p = new Produto();
			ProdutoDAO pDAO = new ProdutoDAO();
			
			if(acao.equals("alterar")) {
				
				if(GerenciarLogin.verificarPermissao(request, response)) {
					
					p = pDAO.getCarregaPorID(Integer.parseInt(idProduto));
					
					if(p.getIdProduto() > 0){
						
						RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_produto.jsp");
						
						request.setAttribute("produto", p);
						disp.forward(request, response);
						
					}else {
						
						msg = "Produto não encontrado";
					}
					
				}else {
					msg = "Acesso Negado!";
				}
			}
			
			if(acao.equals("deletar")) {
				
				if(GerenciarLogin.verificarPermissao(request, response)) {
					
					p.setIdProduto(Integer.parseInt(idProduto));
					
					if(pDAO.deletar(p)) {
						
						msg = "Produto deletado com sucesso!";
						
					}else {
						
						msg = "Erro na servlet GerenciarProduto metodo GET de deletar";
						
					}
				}else {
					msg = "Acesso Negado!";
				}
			}
		}catch(Exception e) {
			
			out.print(e);
			msg = "Erro ao executar servlet GerenciarProduto";
		}
		
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='listar_produto.jsp';");
		out.println("</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String idProduto = request.getParameter("idProduto");
		String nome = request.getParameter("nome");
		String qtd = request.getParameter("qtd");
		String valor = request.getParameter("valor");
		
		String msg = "";
		
		Produto p = new Produto();
		
		if(!idProduto.isEmpty()) {
			
			p.setIdProduto(Integer.parseInt(idProduto));
		}
		if(nome.equals("") || qtd.equals("") || valor.equals("")) {
			
			msg = "Campos obrigatórios deverão ser preenchidos!";
		}else {
			
			p.setNome(nome);
			p.setQtd(Integer.parseInt(qtd));
			
			double novoValor = 0;
			
			if(!valor.isEmpty()) {
				
				novoValor = Double.parseDouble(valor.replace(".", "").replace(",", "."));
			}
			
			p.setValor(novoValor);
			
			try {
				
				ProdutoDAO pDAO = new ProdutoDAO();
				
				if(pDAO.gravar(p)) {
					
					msg = "Gravado com sucesso";
				}else {
					
					msg = "Erro na servlet GerenciarProduto metodo POST de cadastro";
				}
			}catch(Exception e) {
				
				out.print(e);
				msg = "Erro ao executar Servlet GerenciarProduto";
			}
		}
		
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='listar_produto.jsp';");
		out.println("</script>");
		
		
	}

}
