package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.ClienteDAO;


/**
 * Servlet implementation class GerenciarCliente
 */
@WebServlet("/gerenciar_cliente.do")
public class GerenciarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String acao = request.getParameter("acao");
		String idCliente = request.getParameter("idCliente");
		
		String msg = "";
		
		try {
			
			Cliente c = new Cliente();
			ClienteDAO cDAO = new ClienteDAO();
			
			if(acao.equals("alterar")) {
				if(GerenciarLogin.verificarPermissao(request, response)) {
					
					c = cDAO.getCarregaPorID(Integer.parseInt(idCliente));
					
					if(c.getIdCliente() > 0){
						
						RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_cliente.jsp");
						request.setAttribute("cliente", c);
						disp.forward(request, response);
						
					}else {
						msg = "Cliente não encontrado";
					}
					
				}else {
					msg = "Acesso Negado!";
				}
			}
			
			if(acao.equals("deletar")) {
				
				if(GerenciarLogin.verificarPermissao(request, response)) {
					
					c.setIdCliente(Integer.parseInt(idCliente));
					
					if(cDAO.deletar(c)) {
						
						msg = "Cliente deletado com sucesso!";
						
					}else {
						
						msg = "Erro na servlet GerecniarCliente metodo GET de deletar";
						
					}
				}else {
					msg = "Acesso Negado!";
				}
			}
		}catch(Exception e) {
			out.print(e);
			msg = "Erro ao executar na servlet GerenciarCliente";
		}
		
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='listar_cliente.jsp';");
		out.println("</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String idCliente = request.getParameter("idCliente");
		String nomeRazao = request.getParameter("nomeRazao");
		String cpfCnpj = request.getParameter("cpfCnpj");
		String rgIe = request.getParameter("rgIe");
		String dataNascAbertura = request.getParameter("dataNascAbertura");
		String tipo = request.getParameter("tipo");
		
		String msg = "";
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Cliente c = new Cliente();
			ClienteDAO cDAO = new ClienteDAO();
			
			if(!idCliente.isEmpty()) {
				
				c.setIdCliente(Integer.parseInt(idCliente));
			}
			
			if(nomeRazao.equals("") || cpfCnpj.equals("") || rgIe.equals("") || dataNascAbertura.equals("") || tipo.equals("")) {
				
				msg = "Campos obrigatórios deverão ser preenchidos!";
				
			}else {
				
				c.setNomeRazao(nomeRazao);
				c.setCpfCnpj(cpfCnpj);
				c.setRgIe(rgIe);
				
				if(!dataNascAbertura.isEmpty()) {
					
					c.setDataNascAbertura(sdf.parse(dataNascAbertura));
					
				}
				
				c.setTipo(Integer.parseInt(tipo));
				
				if(cDAO.gravar(c)) {
					
					msg = "Cadastrado com sucesso!";
					
				}else {
					
					msg = "Erro na Servlet GerenciarCliente metodo POST de cadastro";
					
				}
				
				
			}
		}catch (Exception e) {
			msg = "Erro ao executar servlet GerenciarCliente";
			System.out.println(e);
		}
		
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='listar_cliente.jsp';");
		out.println("</script>");
		
	}

}
