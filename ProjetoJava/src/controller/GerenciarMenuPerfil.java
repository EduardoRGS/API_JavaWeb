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
 * Servlet implementation class GerenciarMenuPerfil
 */
@WebServlet("/gerenciar_menu_perfil.do")
public class GerenciarMenuPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciarMenuPerfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String idPerfil = request.getParameter("idPerfil");
		String acao = request.getParameter("acao");
		
		String msg= "";
		
		try {
			
			Perfil p = new Perfil();
			PerfilDAO pDAO = new PerfilDAO();
		
			if(acao.equals("gerenciar")) {
				
				if(GerenciarLogin.verificarPermissao(request, response)) {
					
					p = pDAO.getCarregarporID(Integer.parseInt(idPerfil));
					
					if(p.getIdPerfil() > 0) {
						
						RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_menu_perfil.jsp");
						
						request.setAttribute("perfilv", p);
						disp.forward(request, response);
						
					}else {
						msg = "Perfil não encontrado";
					}
				}else{
					msg = "Acesso Negado!";
				}
				
			}
			
			if(acao.equals("desvincular")) {
				
				if(GerenciarLogin.verificarPermissao(request, response)) {
					
					String idMenu = request.getParameter("idMenu");
					
					if(idMenu.equals("")) {
						
						msg = "O Campo idMenu deverá ser selecionado";
						
					} else {
						
						if(pDAO.desvincular(Integer.parseInt(idMenu), Integer.parseInt(idPerfil))) {
							
							msg = "Desvinculado com sucesso";
						} else {
							
							msg = "Erro na Servlet GerenciarMenuPerfil metodo GET de desvincular";
						}
					}
				}else {
					
					msg = "Acesso Negado!";
				}
			}
		}catch(Exception e) {
			System.out.println(e);
			msg = "Erro ao executar servlet GerenciarMenuPerfil";
		}
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='gerenciar_menu_perfil.do?acao=gerenciar&idPerfil="+idPerfil+"';");
		out.println("</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String msg = "";
		
		String idPerfil = request.getParameter("idPerfil");
		String idMenu = request.getParameter("idMenu");
		
		try {
			
			if(idPerfil.equals("") || idMenu.equals("")) {
				
				msg = "Campos Obrigadorios deverão ser selecionados";
			}else {
				
				PerfilDAO pDAO = new PerfilDAO();
				
				if(pDAO.vincular(Integer.parseInt(idMenu), Integer.parseInt(idPerfil))) {
					
					msg = "Vinculado com sucesso!";
				}else {
					
					msg = "Erro na servlet GerenciarMenuPerfil metodo POST de vincular";
				}
			}
		}catch(Exception e) {
			
			System.out.println(e);
		}
		
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='gerenciar_menu_perfil.do?acao=gerenciar&idPerfil="+idPerfil+"';");
		out.println("</script>");
	}

}
