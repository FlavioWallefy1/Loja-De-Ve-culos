package br.edu.ifpe.loja.negocio;

import java.util.List;

import br.edu.ifpe.loja.entidades.Veiculo;
import br.edu.ifpe.loja.excecao.ExcecaoNegocio;

public interface IControladorVeiculo {

	void inserir (Veiculo veiculo) throws ExcecaoNegocio;

	List<Veiculo> consultar(String modelo) throws ExcecaoNegocio;
}
