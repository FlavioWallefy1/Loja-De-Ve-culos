package br.edu.ifpe.loja.entidades;

public class Moto extends Veiculo {
	
	public Moto(String modelo, String marca, int anoFabricacao, int anoModelo, String placa, double preco, String dataSinistro) {
		super(modelo, marca, anoFabricacao, anoModelo, placa, preco, dataSinistro);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String ajusteEspecifico() {
		return "Colocando o capacete e ajustando a moto";
		
	}

}
