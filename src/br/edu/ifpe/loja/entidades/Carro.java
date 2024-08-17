package br.edu.ifpe.loja.entidades;

public class Carro extends Veiculo
{

	public Carro(String modelo, String marca, int anoFabricacao, int anoModelo, String placa, double preco, String dataSinistro) {
		super(modelo, marca, anoFabricacao, anoModelo, placa, preco, dataSinistro);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String prepararVeiculo() {		
		return "Ajustando o banco e espelhos do carro.";
	}

}
