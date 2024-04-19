package br.edu.ifpe.loja.persistencia;

import java.util.List;

import br.edu.ifpe.loja.entidades.Veiculo;

public interface IVeiculoDAO {
	
	void inserir(Veiculo veiculo);

	List<Veiculo> consultar(String modelo);
	List<Veiculo> listarTodos();
}
