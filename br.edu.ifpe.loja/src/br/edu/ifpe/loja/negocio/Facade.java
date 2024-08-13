package br.edu.ifpe.loja.negocio;

import java.util.List;

import br.edu.ifpe.loja.entidades.Veiculo;
import br.edu.ifpe.loja.excecao.ExcecaoNegocio;

public class Facade {

    private IControladorVeiculo controladorVeiculo;

    public Facade() {
        controladorVeiculo = new ControladorVeiculo();
    }

    public void inserir(Veiculo veiculo) throws ExcecaoNegocio {
        controladorVeiculo.inserir(veiculo);
    }

    public void editar(Veiculo veiculo) throws ExcecaoNegocio {
        controladorVeiculo.editar(veiculo);
    }

    public void remover(Long id) throws ExcecaoNegocio {
        controladorVeiculo.remover(id);
    }

    public Veiculo consultar(Long id) throws ExcecaoNegocio {
        return controladorVeiculo.consultar(id);
    }

    public List<Veiculo> listarTodos() {
        return controladorVeiculo.listarTodos();
    }
}
