package br.edu.ifpe.loja.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import br.edu.ifpe.loja.entidades.Veiculo;

public class VeiculoDAO{

	private GenericDAO<Veiculo> genericDaoInstancia;

	public VeiculoDAO() {
		this.genericDaoInstancia = new GenericDAOList<Veiculo>();
	}
	
	
	public void inserir(Veiculo veiculo) { 
		this.genericDaoInstancia.inserir(veiculo);
	}

	public void editar(Veiculo veiculo) {
		this.genericDaoInstancia.editar(veiculo);
	}



	public void remover(Long id) {
		this.genericDaoInstancia.remover(id);
	}

	public List<Veiculo> listarTodos() {
		return this.genericDaoInstancia.listarTodos();
	}

	public List<Veiculo> consultar(Long id) {
		return this.genericDaoInstancia.consultar(id);
	}

}
