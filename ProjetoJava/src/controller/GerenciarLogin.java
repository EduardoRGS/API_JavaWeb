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

import model.Menu;
import model.Usuario;
import model.UsuarioDAO;

/**
 * Servlet implementation class GerenciarLogin
 */
@WebServlet("/gerenciar_login.do")
public class GerenciarLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static HttpServletResponse response;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciarLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().removeAttribute("ulogado");
		response.sendRedirect("form_login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		GerenciarLogin.response = response;
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ArrayList<String> erros = new ArrayList<String>();
		
		if(login == null || login.trim().isEmpty()) {
			erros.add("Preencha o login");
		}
		if(senha == null || senha.trim().isEmpty()) {
			erros.add("Preencha a senha");
		}
		
		if(erros.size() > 0 ) {
			
			String campos = "";
			
			for(String erro: erros) {
				
				campos = campos + "\\n"+erro;
				
			}
			
			exibirMensagem("Preencha os campo(s):"+campos);
		}else {
			
			try {
				
				UsuarioDAO uDAO = new UsuarioDAO();
				Usuario u = new Usuario();
			
				u = uDAO.getReguperarUsuario(login);
				
				if(u.getIdUsuario() > 0 && u.getSenha().equals(senha)) {
					
					HttpSession sessao = request.getSession();
					
					sessao.setAttribute("ulogado", u);
					response.sendRedirect("index.jsp");
					
				}else {
					
					exibirMensagem("Login ou senha inválidos!");
					
				}
			}catch(Exception e) {
				
				exibirMensagem("Usuario ou Perfil não encontrado!");
				System.out.println(e);
			}
		}
	}
	
	private static void exibirMensagem(String msg) {
			
		try {
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+msg+"');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Usuario verificarAcesso(HttpServletRequest request, HttpServletResponse response) {
		
		Usuario u = null;
		GerenciarLogin.response = response;

		try {
			HttpSession sessao = request.getSession();
			
			if(sessao.getAttribute("ulogado") == null) {
				
				response.sendRedirect("form_login.jsp");
				
			}else {
				
				String uri = request.getRequestURI();
				String queryString = request.getQueryString();
				if(queryString != null) {
					
					uri += "?" + queryString;
				}
				u = (Usuario)request.getSession().getAttribute("ulogado");
			
				if(u == null) {
					
					sessao.setAttribute("msg", "Você não está autenticado");
					response.sendRedirect("form_login.jsp");
					
				}else {
					
					boolean possuiAcesso = false;
					
					for(Menu m: u.getPerfil().getMenus()) {
						
						if(uri.contains(m.getLink())) {
							possuiAcesso = true;
							
							break;
						}else {
							possuiAcesso = false;
						}
					}
					
				}
			}
		}catch(Exception e) {
			exibirMensagem("Exceçao: "+e.getMessage());
		}
		
		return u;
	}
	
	public static boolean verificarPermissao (HttpServletRequest request, HttpServletResponse response) {
		
		Usuario u = null;
		GerenciarLogin.response = response;
		boolean possuiAcesso = false;
		
		try {
			HttpSession sessao = request.getSession();
			
			if(sessao.getAttribute("ulogado") == null) {
				
				response.sendRedirect("form_login.jsp");
				
			}else {
			
				String uri = request.getRequestURI();
				String queryString = request.getQueryString();
				
				if(queryString != null) {
					
					uri += "?" + queryString;
				}
				
				u = (Usuario) request.getSession().getAttribute("ulogado");
				
				if(u == null) {
					
					sessao.setAttribute("msg", "Você não está autenticado");
					response.sendRedirect("form_login.jsp");
					
				}else {
					
					for(Menu m: u.getPerfil().getMenus()) {
						
						if(uri.contains(m.getLink())) {
							
							possuiAcesso = true;
							
							break;
						}
					}
					
				}
			}
		}catch(Exception e) {
			
			exibirMensagem("Exceçao: "+e.getMessage());
		}
		
		return possuiAcesso;
	
	}
}
