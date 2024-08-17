package br.edu.ifpe.loja.negocio;

import java.util.List;

import br.edu.ifpe.loja.entidades.Veiculo;
import br.edu.ifpe.loja.excecao.ExcecaoNegocio;

public interface IControladorVeiculo {

	void inserir (Veiculo veiculo) throws ExcecaoNegocio;
	
	void editar (Veiculo veiculo) throws ExcecaoNegocio;

	void remover(Long id) throws ExcecaoNegocio;

	Veiculo consultar(Long id) throws ExcecaoNegocio;

	List<Veiculo> listarTodos();
}
