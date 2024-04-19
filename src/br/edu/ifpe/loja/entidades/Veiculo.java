package br.edu.ifpe.loja.entidades;

public class Veiculo {

	public String modelo;
	public String marca;
	public String ano;
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
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public Veiculo(String modelo, String marca, String ano, String placa) {
		super();
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
		this.placa = placa;
	}
	
	
	
	
	
}
