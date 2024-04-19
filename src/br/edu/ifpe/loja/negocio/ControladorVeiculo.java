package br.edu.ifpe.loja.negocio;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.loja.entidades.Veiculo;
import br.edu.ifpe.loja.excecao.ExcecaoNegocio;
import br.edu.ifpe.loja.persistencia.IVeiculoDAO;
import br.edu.ifpe.loja.persistencia.FabricaDAO;


public class ControladorVeiculo implements IControladorVeiculo{

	public void inserir(Veiculo veiculo) throws ExcecaoNegocio{
		if(!this.isValido(veiculo)) {
			throw new ExcecaoNegocio("Veiculo inválido!");
		}

		IVeiculoDAO dao = FabricaDAO.getVeiculoDAO();
		dao.inserir(veiculo);
	}

	private boolean isValido(Veiculo veiculo) {
		if (veiculo.getModelo() == null || veiculo.getMarca() == null || veiculo.getAno() == null) {
			return false;
		}
		return true;
	}
	
	public List<Veiculo> consultar(String modelo) throws ExcecaoNegocio {
        IVeiculoDAO dao = FabricaDAO.getVeiculoDAO();
        List<Veiculo> listaVeiculos = dao.listarTodos();
        List<Veiculo> veiculosEncontrados = new ArrayList<>();

        for (Veiculo veiculo : listaVeiculos) {
            if (veiculo.getModelo().equalsIgnoreCase(modelo)) {
                veiculosEncontrados.add(veiculo);
            }
        }
        return veiculosEncontrados;
    }
}
