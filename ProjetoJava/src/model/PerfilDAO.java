package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PerfilDAO extends DataBaseDAO{
	
	public PerfilDAO() throws Exception{
		
	}
	// metodo de lista um perfil
	public ArrayList<Perfil> getLista() throws Exception{
		
		ArrayList<Perfil> lista = new ArrayList<Perfil>();
		String SQL = "SELECT * FROM perfil";
		
		this.conectar();
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(SQL);
		
		while(rs.next()) {
			
			Perfil p = new Perfil();
			
			p.setIdPerfil(rs.getInt("idPerfil"));
			p.setNome(rs.getString("nome"));
			
			lista.add(p);
		}
		
		this.desconectar();
		return lista;
	}
	
	//metodo de cadastrar e alterar um perfil
	public boolean gravar(Perfil p) {
		
		try {
			String sql;
			this.conectar();
			
			if(p.getIdPerfil() == 0) {
				
				sql = "INSERT INTO perfil(nome) VALUES(?)";
				
			} else {
				
				sql = "UPDATE perfil SET nome=? WHERE idPerfil=?";
			}
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setNString(1, p.getNome());
			
			if(p.getIdPerfil() > 0) {
				
				ps.setInt(2, p.getIdPerfil());
			}
			
			ps.execute();
			this.desconectar();
			
			return true;
			
		}catch(Exception e){
			
			System.out.println(e);
			return false;
		}
	}
	// metodo que retorna o conteudo de acordo com o id do usuario
	public Perfil getCarregarporID(int idPerfil) throws Exception{
		
		Perfil p = new Perfil();
		String sql = "SELECT * FROM perfil WHERE idPerfil=?";
		
		this.conectar();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idPerfil);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			
			p.setIdPerfil(rs.getInt("idPerfil"));
			p.setNome(rs.getString("nome"));
			p.setMenus(menusVinculadosPorPerfil(idPerfil));
			p.setNaoMenus(menusNaoVinculadosPorPerfil(idPerfil));
			
		}
		
		this.desconectar();
		return p;
	}
	
	//metodo de deletar um perfil
	public boolean deletar (Perfil p) {
		
		try {
			
			this.conectar();
			String sql= "DELETE FROM perfil WHERE idPerfil=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, p.getIdPerfil());
			
			ps.execute();
			this.desconectar();
			
			return true;
			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	// metodo de listar uos perfils vinculados a um menu
	public ArrayList<Menu> menusVinculadosPorPerfil(int idPerfil) throws Exception{
		
		ArrayList<Menu> lista = new ArrayList<Menu>();
		String sql = "SELECT m.* FROM menu_perfil as mp, menu as m "
				+ "WHERE mp.idMenu = m.idMenu AND mp.idPerfil = ?";
		
		this.conectar();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idPerfil);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			Menu m = new Menu();
			
			m.setIdMenu(rs.getInt("m.idMenu"));
			m.setNome(rs.getString("m.nome"));
			m.setLink(rs.getString("m.link"));
			m.setIcone(rs.getString("m.icone"));
			m.setExibir(rs.getInt("m.exibir"));
			
			lista.add(m);
		}
		
		this.desconectar();
		return lista;
	}
	
	// metodo de listar os perfis nao vinculado a um menu
	public ArrayList<Menu> menusNaoVinculadosPorPerfil(int idPerfil) throws Exception{
		
		ArrayList<Menu> lista = new ArrayList<Menu>();
		String sql = "SELECT m.* FROM menu as m "
				+ "WHERE m.idMenu NOT IN ( SELECT mp.idMenu FROM menu_perfil as mp WHERE mp.idPerfil=?)";
		
		this.conectar();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idPerfil);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			Menu m = new Menu();
			
			m.setIdMenu(rs.getInt("m.idMenu"));
			m.setNome(rs.getString("m.nome"));
			m.setLink(rs.getString("m.link"));
			m.setIcone(rs.getString("m.icone"));
			m.setExibir(rs.getInt("m.exibir"));
			
			lista.add(m);
		}
		
		this.desconectar();
		return lista;
	}
	
	// metodo de vincular um perfil a um menu
	public boolean vincular(int idMenu,int idPerfil) {
		
		try {
			
			String sql = "INSERT INTO menu_perfil (idMenu, idPerfil) "
					+ "VALUES (?,?)";
			
			this.conectar();
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1,idMenu);
			ps.setInt(2, idPerfil);
			ps.execute();
			
			this.desconectar();
			
			return true;
			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	//metodo de desvincular um perfil de um menu
	public boolean desvincular(int idMenu, int idPerfil) {
		
		try {
			
			String sql = "DELETE FROM menu_perfil WHERE idMenu=? AND idPerfil=?";
			
			this.conectar();
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1,idMenu);
			ps.setInt(2, idPerfil);
			ps.execute();
			
			this.desconectar();
			return true;
			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
