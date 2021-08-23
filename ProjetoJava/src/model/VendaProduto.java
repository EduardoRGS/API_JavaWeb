package model;

public class VendaProduto {
	
	private long idVendaProduto;
	private int qtd;
	private double valorVendido;
	private Venda venda;
	private Produto produto;
	
	public VendaProduto() {
		
	}

	public VendaProduto(long idVendaProduto, int qtd, double valorVendido, Venda venda, Produto produto) {
	
		this.idVendaProduto = idVendaProduto;
		this.qtd = qtd;
		this.valorVendido = valorVendido;
		this.venda = venda;
		this.produto = produto;
	}

	public long getIdVendaProduto() {
		return idVendaProduto;
	}

	public void setIdVendaProduto(long idVendaProduto) {
		this.idVendaProduto = idVendaProduto;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public double getValorVendido() {
		return valorVendido;
	}

	public void setValorVendido(double valorVendido) {
		this.valorVendido = valorVendido;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "VendaProduto [idVendaProduto=" + idVendaProduto + ", qtd=" + qtd + ", valorVendido=" + valorVendido
				+ ", produto=" + produto + "]";
	}
	
	
	
}
