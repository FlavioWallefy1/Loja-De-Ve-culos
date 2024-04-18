package br.edu.ifpe.loja.negocio;

import br.edu.ifpe.loja.entidades.Veiculo;

public class ControladorVeiculo implements IControladorVeiculo{

	public void inserir(Veiculo veiculo) throws ExcecaoNegocio{
		if(!this.isValido(veiculo)) {
			throw new ExcecaoNegocio("Veiculo inválido!");
		}
		
		IVeiculoDAO dao = fabricacao.getVeiculoDAO;
		dao.inserir(veiculo);
	}
	
	private void isValido(Veiculo veiculo) {
		//validar se veiculo ja existe
	}
}
