package model;

import java.util.Date;

public class Cliente {
	private int idCliente;
	private String nomeRazao;
	private String cpfCnpj;
	private String rgIe;
	private Date dataNascAbertura;
	private int tipo;
	public Cliente() {
	
	}
	public Cliente(int idCliente, String nomeRazao, String cpfCnpj, Date dataNascAbertura, int tipo) {
		super();
		this.idCliente = idCliente;
		this.nomeRazao = nomeRazao;
		this.cpfCnpj = cpfCnpj;
		this.dataNascAbertura = dataNascAbertura;
		this.tipo = tipo;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeRazao() {
		return nomeRazao;
	}
	public void setNomeRazao(String nomeRazao) {
		this.nomeRazao = nomeRazao;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getRgIe() {
		return rgIe;
	}
	public void setRgIe(String rgIe) {
		this.rgIe = rgIe;
	}
	public Date getDataNascAbertura() {
		return dataNascAbertura;
	}
	public void setDataNascAbertura(Date dataNascAbertura) {
		this.dataNascAbertura = dataNascAbertura;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Cliente [nomeRazao=" + nomeRazao + "]";
	}
	
	
	
	
}
