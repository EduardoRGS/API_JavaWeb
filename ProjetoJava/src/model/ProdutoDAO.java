package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutoDAO extends DataBaseDAO{
	
	public ProdutoDAO() throws Exception{
		
	}
	// metodo de listar produtos
	public ArrayList<Produto> getLista() throws Exception{
		
		ArrayList<Produto> lista = new ArrayList<Produto>();
		
		String sql = "SELECT * FROM produto";
		this.conectar();
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		
		while(rs.next()) {
			
			Produto p = new Produto();
			
			p.setIdProduto(rs.getInt("idProduto"));
			p.setNome(rs.getString("nome"));
			p.setQtd(rs.getInt("qtd"));
			p.setValor(rs.getDouble("valor"));
			
			lista.add(p);
			
		}
		
		this.desconectar();
		return lista;
	}
	// meotodo de cadastrar e alterar produtos
	public boolean gravar(Produto p) {
		
		try {
			
			this.conectar();
			String sql;
			
			if(p.getIdProduto() == 0) {
				sql = "INSERT INTO produto(nome, qtd, valor) "
						+ "VALUES(?,?,?)";
				
			}else {
				
				sql = "UPDATE produto SET nome=?, qtd=?, valor=? "
						+ "WHERE idProduto=?";
			}
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, p.getNome());
			ps.setInt(2, p.getQtd());
			ps.setDouble(3, p.getValor());
			
			if(p.getIdProduto() > 0){
				
				ps.setInt(4, p.getIdProduto());
			}
			
			ps.execute();
			
			this.desconectar();
			return true;
			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	// metodo que retorna o conteudo de acordo com o id do produto
	public Produto getCarregaPorID(int idProduto) throws Exception{
		
		Produto p = new Produto();
		String sql = "SELECT * FROM produto WHERE idProduto=?";
		
		this.conectar();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idProduto);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			
			p.setIdProduto(rs.getInt("idProduto"));
			p.setNome(rs.getString("nome"));
			p.setQtd(rs.getInt("qtd"));
			p.setValor(rs.getDouble("valor"));
			
		}
		
		this.desconectar();
		return p;
	}
	// metodo de deletar um produto
	public boolean deletar(Produto p) {
		
		try {
			this.conectar();
			
			String sql = "DELETE FROM produto WHERE idProduto=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, p.getIdProduto());
			ps.execute();
			
			this.desconectar();
			return true;
		}catch(Exception e) {
			
			System.out.println(e);
			return false;
		}
	}
}
