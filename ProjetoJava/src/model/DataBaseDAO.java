package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseDAO {
	public final String URL="jdbc:mysql://localhost:3306/projetoJava";
	public final String USER="root";
	public final String PASSWORD="";
	
	public Connection con;
	
	public DataBaseDAO() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
	}
	
	public void conectar() throws Exception{
		con = DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public void desconectar() throws Exception{
		con.close();
	}
}
