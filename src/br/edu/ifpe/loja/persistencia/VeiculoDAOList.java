package br.edu.ifpe.loja.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.loja.entidades.Veiculo;

public class VeiculoDAOList {

	private List<Veiculo> lista;
	private static VeiculoDAOList instancia;
	
	private VeiculoDAOList() {
		this.lista = new ArrayList<>();
	}
	
	protected static VeiculoDAO getInstancia() { //3. criar a instancia da classe
        if (instancia == null) {
            instancia = new VeiculoDAOList();
        }

        return instancia;
    }

    public void inserir(Veiculo veiculo) {
        this.lista.add(veiculo);
    }

    private boolean isListEmpty() {
        return this.lista.length() == 0;
    }
}
