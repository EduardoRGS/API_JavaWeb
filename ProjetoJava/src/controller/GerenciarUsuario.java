package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Perfil;
import model.Usuario;
import model.UsuarioDAO;

/**
 * Servlet implementation class GerenciarUsuario
 */
@WebServlet("/gerenciar_usuario.do")
public class GerenciarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String acao = request.getParameter("acao");
		String idUsuario = request.getParameter("idUsuario");
		
		String msg="";
		
		try {
			
			Usuario u = new Usuario();
			UsuarioDAO uDAO = new UsuarioDAO();
		
			if(acao.equals("alterar")) {
				
				if(GerenciarLogin.verificarPermissao(request, response)) {
					
					u = uDAO.getCarregaPorID(Integer.parseInt(idUsuario));
				
				
					if(u.getIdUsuario() > 0) {
						
						RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_usuario.jsp");
						
						request.setAttribute("usuario", u);
						disp.forward(request, response);
						
					}else {
						
						msg = "Usuario não encontrado";
					}
				}else {
					msg = "Acesso Negado!";
				}
			}
			
			if(acao.equals("deletar")) {
				
				if(GerenciarLogin.verificarPermissao(request, response)) {
					
					u.setIdUsuario(Integer.parseInt(idUsuario));
					
					if(uDAO.deletar(u)) {
						
						msg = "Desativado com Sucesso!";
					}else {
						
						msg = "Erro na Servlet GerenciarUsuario metodo GEt de desativar usuario";
					}
				}else {
					msg = "Acesso Negado!";
				}
			}
			
		}catch(Exception e){
			
			System.out.println(e);
			msg = "Erro ao executar na servlet GerenciarUsuario";
		}
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='listar_usuario.jsp';");
		out.println("</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String idUsuario = request.getParameter("idUsuario");
		String nome = request.getParameter("nome");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String status = request.getParameter("status");
		String idPerfil = request.getParameter("idPerfil");
		
		String msg ="";
		
		Usuario u = new Usuario();
		
		if(!idUsuario.isEmpty()) {
			
			u.setIdUsuario(Integer.parseInt(idUsuario));
		}

		if(nome.equals("") || login.equals("") || senha.equals("") || status.equals("") || idPerfil.equals("")) {
			
			msg = "Campo obrigatótios devem ser preenchidos!";
			
		}else {

			u.setNome(nome);
			u.setLogin(login);
			u.setSenha(senha);
			u.setStatus(Integer.parseInt(status));
			
			Perfil p = new Perfil();
			
			p.setIdPerfil(Integer.parseInt(idPerfil));
			u.setPerfil(p);
	
			try {
				
				UsuarioDAO uDAO = new UsuarioDAO();
				if(uDAO.gravar(u)) {
					
					msg = "Gravado com sucesso!";
				}else {
					
					msg = "Erro na servlet GerenciarUsuario metodo POST de cadastro";
				}
			}catch(Exception e) {
				
				System.out.println(e);
				msg = "Erro ao executar na servlet GerenciarUsuario";
			}
		}
		
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='listar_usuario.jsp';");
		out.println("</script>");
	}

}
