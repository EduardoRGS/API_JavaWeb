package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO extends DataBaseDAO{
	
	public UsuarioDAO() throws Exception{
	}
	// metodo de listar usuarios
	public ArrayList<Usuario> getLista() throws Exception {
		
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT u.*, p.nome FROM usuario u "
				+ "INNER JOIN perfil p ON p.idPerfil = u.idPerfil";
		
		this.conectar();
		
		PreparedStatement ps = con.prepareCall(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			Usuario u = new Usuario();
			
			u.setIdUsuario(rs.getInt("u.idUsuario"));
			u.setNome(rs.getString("u.nome"));
			u.setLogin(rs.getString("u.login"));
			u.setSenha(rs.getString("u.senha"));
			u.setStatus(rs.getInt("u.status"));
			
			Perfil p = new Perfil();
			
			p.setIdPerfil(rs.getInt("u.idPerfil"));
			p.setNome(rs.getString("p.nome"));
			u.setPerfil(p);
			
			lista.add(u);
		}
		
		this.desconectar();
		return lista;
	}
	// metodop de cadastrar e alterar um usuario
	public boolean gravar(Usuario u) {
		
		try {
			
			String sql;
			this.conectar();
			
			if(u.getIdUsuario() == 0) {
				sql = "INSERT INTO usuario (nome, login, senha, status, idPerfil) "
						+ " VALUES (?,?,?,?,?)";
			}else {
				sql = "UPDATE usuario SET nome=?, login=?, senha=?, status=?, idPerfil=? "
						+ "WHERE idUsuario=?";
						
			}
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, u.getNome());
			ps.setString(2, u.getLogin());
			ps.setNString(3, u.getSenha());
			ps.setInt(4, u.getStatus());
			ps.setInt(5, u.getPerfil().getIdPerfil());
			
			if(u.getIdUsuario() > 0) {
				
				ps.setInt(6, u.getIdUsuario());
			}
			
			ps.execute();
			
			this.desconectar();
			return true;
			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	// metodo que retorna o conteudo de acordo com o id do usuario
	public Usuario getCarregaPorID(int idUsuario) throws Exception{
		
		Usuario u = new Usuario();
		String sql = "SELECT u.*, p.nome FROM usuario u "
				+ "INNER JOIN perfil p ON p.idPerfil = u.idPerfil "
				+ "WHERE u.idUsuario=?";
		
		this.conectar();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idUsuario);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			
			u.setIdUsuario(rs.getInt("u.idUsuario"));
			u.setNome(rs.getString("u.nome"));
			u.setLogin(rs.getString("u.login"));
			u.setSenha(rs.getString("u.senha"));
			u.setStatus(rs.getInt("u.status"));
			
			Perfil p = new Perfil();
			
			p.setIdPerfil(rs.getInt("u.idPerfil"));
			p.setNome(rs.getString("p.nome"));
			u.setPerfil(p);	
		}
		
		this.desconectar();
		return u;
	}
	
	//metodo de deletar um usuario
	public boolean deletar(Usuario u) {
		
		try {
			
			String sql = "UPDATE usuario SET status=2 WHERE idUsuario=? ";
			this.conectar();
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, u.getIdUsuario());
			
			ps.execute();
			
			this.desconectar();
			return true;
			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	// metodo de recuperar usuario para fazer o login
	public Usuario getReguperarUsuario(String login) {
		
		Usuario u = new Usuario();
		String sql = "SELECT u.* FROM usuario u "
				+ "WHERE u.login=?";
		
		try {
			
			this.conectar();
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				u.setIdUsuario(rs.getInt("u.idUsuario"));
				u.setNome(rs.getString("u.nome"));
				u.setLogin(rs.getString("u.login"));
				u.setSenha(rs.getString("u.senha"));
				u.setStatus(rs.getInt("u.status"));
				
				PerfilDAO pDAO = new PerfilDAO();
				u.setPerfil(pDAO.getCarregarporID(rs.getInt("u.idPerfil")));
				
			}
			
			this.desconectar();
			return u;
		}catch(Exception e) {
			
			System.out.println(e);
			return null;
		}
	}
}
