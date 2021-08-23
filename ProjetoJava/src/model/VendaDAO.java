package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class VendaDAO extends DataBaseDAO{
	
	public VendaDAO() throws Exception {
		
	}
	//metodo de cadastrar uma venda
	public boolean registrar(Venda v) {
		
		try {
			
			this.conectar();
			
			String sql = "INSERT INTO venda (dataVenda, idCliente, idUsuario) "
					+ "VALUES (now(),?,?)";
			
			PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1,v.getCliente().getIdCliente());
			ps.setInt(2, v.getVendedor().getIdUsuario());
			
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				
				v.setIdVenda(rs.getInt(1));
			}
			
			for(VendaProduto vp: v.getCarrinho()) {
				
				String sql_vp = "INSERT INTO venda_produto(idVenda, idProduto, qtdVendida, valor) "
						+ "VALUES (?,?,?,?)";
				
				PreparedStatement ps_vp = con.prepareCall(sql_vp);
				
				ps_vp.setInt(1, v.getIdVenda());
				ps_vp.setInt(2, vp.getProduto().getIdProduto());
				ps_vp.setInt(3, vp.getQtd());
				ps_vp.setDouble(4, vp.getValorVendido());
				
				ps_vp.execute();
				
			}
			
			this.desconectar();
			return true;
			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	// metodo de listar uma venda
	public ArrayList<Venda> getLista() throws Exception{
		
		ArrayList<Venda> lista = new ArrayList<Venda>();
		String SQL = "SELECT * FROM venda";
		
		this.conectar();
		
		PreparedStatement s = con.prepareStatement(SQL);
		ResultSet rs = s.executeQuery(SQL);
		
		while(rs.next()) {
			
			Venda v = new Venda();
			v.setIdVenda(rs.getInt("idVenda"));
			v.setDataVenda(rs.getDate("dataVenda"));
			
			ClienteDAO cDAO = new ClienteDAO();
			v.setCliente(cDAO.getCarregaPorID(rs.getInt("idCliente")));
			
			UsuarioDAO uDAO = new UsuarioDAO();
			v.setVendedor(uDAO.getCarregaPorID(rs.getInt("idUsuario")));
			
			lista.add(v);
		}
		
		this.desconectar();
		return lista;
	}
	
}
