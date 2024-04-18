package br.edu.ifpe.loja.persistencia;

import java.util.List;

import br.edu.ifpe.loja.entidades.Veiculo;

public interface FachadaPersistencia {

	void inserirVeiculo(Veiculo veiculo);
	void editarVeiculo(Veiculo veiculo);
	void removerVeiculo(int id);
	List<Veiculo> lisVeiculos();
	
}
