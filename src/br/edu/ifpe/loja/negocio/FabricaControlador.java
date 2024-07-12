package br.edu.ifpe.loja.negocio;

public class FabricaControlador {

	public static IControladorVeiculo getControladorVeiculo() {
		return new ControladorVeiculo();
	}

}
