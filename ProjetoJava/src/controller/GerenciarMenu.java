package controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Menu;
import model.MenuDAO;
import model.Perfil;
import model.PerfilDAO;

/**
 * Servlet implementation class GerenciarMenu
 */
@WebServlet("/gerenciar_menu.do")
public class GerenciarMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciarMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String msg = "";
		
		String acao = request.getParameter("acao");
		String idMenu = request.getParameter("idMenu");
		
		Menu m = new Menu();
		try {
			
		MenuDAO mDAO = new MenuDAO();
		
		if(acao.equals("alterar")) {
			
			if(GerenciarLogin.verificarPermissao(request, response)) {
				
				m = mDAO.getCarregarProID(Integer.parseInt(idMenu));
				
				if(m.getIdMenu() != 0) {
					
					RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_menu.jsp");
					
					request.setAttribute("menu", m);
					disp.forward(request, response);
					
				}else {
					
					msg = "Menu não encontrado";
				}
			}else {
				
				msg = "Acesso Negado!";
			}
		}
		
		if(acao.equals("deletar")) {
			
			if(GerenciarLogin.verificarPermissao(request, response)) {
				
				m.setIdMenu(Integer.parseInt(idMenu));
				
				if(mDAO.deletar(m)) {
					
					msg = " Menu excluido com sucesso!";
				}else {
					
					msg = "Erro na servlet GerenciarMenu metodo GET do excluir";
				}
			}else {
				
				msg = "Acesso Negado!";
			}
		}
		}catch(Exception e) {
			
			System.out.println(e);
			msg = "Erro ao executar servlet GerenciarMenu";
		}
		
		
		
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='listar_menu.jsp';");
		out.println("</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String idMenu = request.getParameter("idMenu");
		String nome = request.getParameter("nome");
		String link = request.getParameter("link");
		String icone = request.getParameter("icone");
		String exibir = request.getParameter("exibir");
		
		String msg="";
		
		Menu m = new Menu();
		
		try {
			
			MenuDAO mDAO = new MenuDAO();
			
			if(!idMenu.isEmpty()) {
				
				m.setIdMenu(Integer.parseInt(idMenu));			
			}
			
			if(nome.equals("") || link.equals("") || exibir.equals("")){
				
				msg = "Campos obrigátorios deverão ser preenchidos";
				
			}else {
				
				m.setNome(nome);
				m.setLink(link);
				m.setIcone(icone);
				m.setExibir(Integer.parseInt(exibir));
				if(mDAO.gravar(m)) {
					
					msg = "Gravado com sucesso!";
				} else {
					msg = "Erro na Servlet GerenciarMenu metodo POST de cadastro";
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			msg = "Erro ao executar servlet GerenciarMenu";
		}
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='listar_menu.jsp';");
		out.println("</script>");
	}

}
