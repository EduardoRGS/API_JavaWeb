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
import model.PerfilDAO;

/**
 * Servlet implementation class GerenciarPerfil
 */
@WebServlet("/gerenciar_perfil.do")
public class GerenciarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciarPerfil() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String msg = "";
		
		String acao = request.getParameter("acao");
		String idPerfil = request.getParameter("idPerfil");
		
		Perfil p = new Perfil();
		
		try {
			
			PerfilDAO pDAO = new PerfilDAO();
			
			if(acao.equals("alterar")) {
				
				if(GerenciarLogin.verificarPermissao(request, response)) {
					
					p = pDAO.getCarregarporID(Integer.parseInt(idPerfil));
				
					if(p.getIdPerfil() != 0) {
						
						RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_perfil.jsp");
						
						request.setAttribute("perfil", p);
						disp.forward(request, response);
						
					}else {
						
						msg = "Perfil não encontrado!";
					}
				}else {
					
					msg = "Acesso Negado!";
				}
			}
			
			if(acao.equals("deletar")) {
				
				if(GerenciarLogin.verificarPermissao(request, response)) {
					
					p.setIdPerfil(Integer.parseInt(idPerfil));
					
					if(pDAO.deletar(p)) {
						
						msg = "Excluido com sucesso!";
					}else {
						
						msg = "Erro na servlet GerenciarPerfil metodo GEt de excluir";
					}
				}else {
					
					msg = "Acesso Negado!";
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			msg = "Erro ao executar na servlet GerenciarPerfil";
		}
		
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='listar_Perfil.jsp';");
		out.println("</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String idPerfil = request.getParameter("idPerfil");
		String nome = request.getParameter("nome");
		
		String msg="";
		
		Perfil p = new Perfil();
		
		try {
			
			PerfilDAO pDAO = new PerfilDAO();
			
			if(!idPerfil.isEmpty()) {
				p.setIdPerfil(Integer.parseInt(idPerfil));			
			}
			
			if(nome.equals("") || nome.isEmpty()){
				
				msg = "Campos obrigátorios deverão ser preenchidos";
				
			}else {
				
				p.setNome(nome);
				
				if(pDAO.gravar(p)) {
					
					msg = "Gravado com sucesso!";
				} else {
					
					msg = "Erro na servlet GerenciarPerfil metodo POST de cadastro";
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			msg = "Erro ao executar na ServletPerfil";
		}
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='listar_Perfil.jsp';");
		out.println("</script>");
	}

}
