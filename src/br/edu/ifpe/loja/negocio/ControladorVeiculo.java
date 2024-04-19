package br.edu.ifpe.loja.negocio;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.loja.entidades.Veiculo;
import br.edu.ifpe.loja.excecao.ExcecaoNegocio;
import br.edu.ifpe.loja.persistencia.IVeiculoDAO;
import br.edu.ifpe.loja.persistencia.FabricaDAO;


public class ControladorVeiculo implements IControladorVeiculo{
	
	public void inserir(Veiculo veiculo) throws ExcecaoNegocio{
		 IVeiculoDAO dao = FabricaDAO.getVeiculoDAO();
		 List<Veiculo> veiculosComPlaca = consultar(veiculo.getPlaca());
		    if (!veiculosComPlaca.isEmpty()) {
		        throw new ExcecaoNegocio("Já existe um veículo cadastrado com a placa informada!");
		    }
		dao.inserir(veiculo);
	}
	
	public void editar(Veiculo veiculo) throws ExcecaoNegocio {
        IVeiculoDAO dao = FabricaDAO.getVeiculoDAO();
        List<Veiculo> veiculosComPlaca = consultar(veiculo.getPlaca());
        if (veiculosComPlaca.isEmpty()) {
            throw new ExcecaoNegocio("Esse veículo não está cadastrado!");
        }
        dao.editar(veiculo);
    }
	
	public void remover(String placa) throws ExcecaoNegocio{
		IVeiculoDAO dao = FabricaDAO.getVeiculoDAO();
		List<Veiculo> veiculosComPlaca = consultar(placa);
		if (veiculosComPlaca.isEmpty()) {
	        throw new ExcecaoNegocio("Esse veículo não está cadastrado!");
	    }
		
		dao.remover(placa);
	}
	public List<Veiculo> consultar(String placa) throws ExcecaoNegocio {
		IVeiculoDAO dao = FabricaDAO.getVeiculoDAO();
		List<Veiculo> listaVeiculos = dao.listarTodos();
		List<Veiculo> veiculosEncontrados = new ArrayList<>();

		for (Veiculo veiculo : listaVeiculos) {
			if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
				veiculosEncontrados.add(veiculo);
			}
		}
		return veiculosEncontrados;
	}
}
