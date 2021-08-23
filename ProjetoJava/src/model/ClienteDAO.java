package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO extends DataBaseDAO{
	
	
	public ClienteDAO() throws Exception{
		
	}
	
	// metodo de listar clientes
	public ArrayList<Cliente> getLista() throws Exception {
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		String sql = "SELECT * FROM cliente";
		
		this.conectar();
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		
		while(rs.next()) {
			
			Cliente c = new Cliente();
			
			c.setIdCliente(rs.getInt("idCLiente"));
			c.setNomeRazao(rs.getString("nomeRazao"));
			c.setCpfCnpj(rs.getString("cpfCnpj"));
			c.setRgIe(rs.getString("rgIe"));
			c.setDataNascAbertura(rs.getDate("dataNascAbertura"));
			c.setTipo(rs.getInt("tipo"));
			
			lista.add(c);
		}
		
		this.desconectar();
		return lista;
	}
	// metodo de cadastrar e alterar um cliente
	public boolean gravar(Cliente c) {
		
		try {
			
			this.conectar();
			String sql;
			
			if(c.getIdCliente() == 0) {
				sql = "INSERT INTO cliente(nomeRazao, cpfCnpj, rgIe, dataNascAbertura, tipo) "
						+ "VALUES(?,?,?,?,?)";
			}else {
				sql = "UPDATE cliente SET nomeRazao=?, cpfCnpj=?, rgIe=?, dataNascAbertura=?, tipo=? "
						+ "WHERE idCliente=?";
			}
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, c.getNomeRazao());
			ps.setString(2, c.getCpfCnpj());
			ps.setString(3, c.getRgIe());
			ps.setDate(4, new Date(c.getDataNascAbertura().getTime()));
			ps.setInt(5, c.getTipo());
			
			if(c.getIdCliente() > 0){
				
				ps.setInt(6, c.getIdCliente());
				
			}
			
			ps.execute();
			
			this.desconectar();
			return true;
			
		}catch(Exception e) {
			
			System.out.println(e);
			return false;
		}
	}
	// metodo de deletar um cliente
	public boolean deletar(Cliente c) {
		
		try {
			
			this.conectar();
			String sql = "DELETE FROM cliente WHERE idCliente=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, c.getIdCliente());
			ps.execute();
			
			this.desconectar();
			return true;
			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	// metodo que retorna o conteudo de acordo com o id do cliente
	public Cliente getCarregaPorID(int idCliente) throws Exception{
		
		Cliente c = new Cliente();
		
		String sql = "SELECT * FROM cliente WHERE idCliente=?";
		this.conectar();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idCliente);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			
			c.setIdCliente(rs.getInt("idCLiente"));
			c.setNomeRazao(rs.getString("nomeRazao"));
			c.setCpfCnpj(rs.getString("cpfCnpj"));
			c.setRgIe(rs.getString("rgIe"));
			c.setDataNascAbertura(rs.getDate("dataNascAbertura"));
			c.setTipo(rs.getInt("tipo"));
			
		}
		
		this.desconectar();
		return c;
	}
}
