package br.edu.ifpe.loja.entidades;

public class Veiculo {

	public String modelo;
	public String marca;
	public int anoFabricacao;
	public int anoModelo;
	public String placa;
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public int getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}
		
	public Veiculo(String modelo, String marca, int anoFabricacao, int anoModelo, String placa) {
		super();
		this.modelo = modelo;
		this.marca = marca;
		this.anoFabricacao = anoFabricacao;
		this.anoModelo = anoModelo;
		this.placa = placa;
	}	
}