package br.edu.ifpe.loja.negocio;

import java.util.List;

import br.edu.ifpe.loja.entidades.Veiculo;
import br.edu.ifpe.loja.excecao.ExcecaoNegocio;
import br.edu.ifpe.loja.persistencia.FabricaDAO;
import br.edu.ifpe.loja.persistencia.GenericDAO;

public abstract class ControladorGenerico {

	private GenericDAO<Veiculo> dao = FabricaDAO.getVeiculoDAO();
	
	   public void remover(Long id) throws ExcecaoNegocio {
	        Veiculo veiculoExistente = dao.consultar(id);
	        if (veiculoExistente == null) {
	            throw new ExcecaoNegocio("Esse veículo não está cadastrado!");
	        }
	        dao.remover(id);
	    }

	    public Veiculo consultar(Long id) throws ExcecaoNegocio {
	        Veiculo veiculo = dao.consultar(id);
	        if (veiculo == null) {
	            throw new ExcecaoNegocio("Veículo não encontrado!");
	        }
	        return veiculo;
	    }

	    public List<Veiculo> listarTodos() {
	        return dao.listarTodos();
	    }
}
