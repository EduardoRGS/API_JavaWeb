package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDAO extends DataBaseDAO{
	
	public MenuDAO () throws Exception{
	}
	
	// metodo de cadastro e alterar um menu
	public boolean gravar(Menu m) {
		
		try {
			
			String sql;
			this.conectar();
			
			if(m.getIdMenu() == 0) {
				
				sql = "INSERT INTO menu(nome, link, icone, exibir) VALUES(?,?,?,?)";
				
			} else {
				
				sql = "UPDATE menu SET nome=?, link=?, icone=?, exibir=? WHERE idMenu=?";
			}
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, m.getNome());
			ps.setString(2, m.getLink());
			ps.setString(3, m.getIcone());
			ps.setInt(4, m.getExibir());
			
			if(m.getIdMenu() > 0) {
				
				ps.setInt(5, m.getIdMenu());
			}
			
			ps.execute();
			
			this.desconectar();
			return true;
			
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
	// metodo de listar o menu
	public ArrayList<Menu> getLista() throws Exception{
		
		ArrayList<Menu> lista = new ArrayList<Menu>();
		String SQL = "SELECT * FROM menu";
		
		this.conectar();
		
		PreparedStatement s = con.prepareStatement(SQL);
		ResultSet rs = s.executeQuery(SQL);
		
		while(rs.next()) {
			
			Menu m = new Menu();
			
			m.setIdMenu(rs.getInt("idMenu"));
			m.setNome(rs.getString("nome"));
			m.setLink(rs.getString("link"));
			m.setIcone(rs.getString("icone"));
			m.setExibir(rs.getInt("exibir"));
			
			lista.add(m);
		}
		
		this.desconectar();
		return lista;
	}
	
	// emtodo que retorna um conteudo de acordo com o id do menu
	public Menu getCarregarProID(int idMenu) throws Exception{
		
		Menu m = new Menu();
		
		String sql = "SELECT * FROM  menu WHERE idMenu=?";
		this.conectar();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idMenu);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			
			m.setIdMenu(rs.getInt("idMenu"));
			m.setNome(rs.getString("nome"));
			m.setIcone(rs.getString("icone"));
			m.setExibir(rs.getInt("exibir"));
		}
		
		this.desconectar();
		return m;
	}
	// meotodo de deletar um menu
	public boolean deletar(Menu m) {
		
		try {
			
			String sql = "DELETE FROM  menu WHERE idMenu=?";
			this.conectar();
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, m.getIdMenu());
			
			ps.execute();
			return true;
			
		}catch(Exception e){
			
			System.out.println(e);
			return false;
		}
	}
}
