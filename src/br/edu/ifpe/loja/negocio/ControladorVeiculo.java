package br.edu.ifpe.loja.negocio;

import br.edu.ifpe.loja.entidades.Veiculo;
import br.edu.ifpe.loja.excecao.ExcecaoNegocio;
import br.edu.ifpe.loja.persistencia.FabricaDAO;
import br.edu.ifpe.loja.persistencia.GenericDAO;
import java.util.List;

public class ControladorVeiculo implements IControladorVeiculo {

    private GenericDAO<Veiculo> dao = FabricaDAO.getVeiculoDAO();

    @Override
    public void inserir(Veiculo veiculo) throws ExcecaoNegocio {
        List<Veiculo> veiculosComPlaca = dao.listarTodos();
        for (Veiculo v : veiculosComPlaca) {
            if (v.getPlaca().equalsIgnoreCase(veiculo.getPlaca())) {
                throw new ExcecaoNegocio("Já existe um veículo cadastrado com a placa informada!");
            }
        }
        dao.inserir(veiculo);
    }

    @Override
    public void editar(Veiculo veiculo) throws ExcecaoNegocio {
        Veiculo veiculoExistente = dao.consultar(veiculo.getId());
        if (veiculoExistente == null) {
            throw new ExcecaoNegocio("Esse veículo não está cadastrado!");
        }
        veiculo.setPlaca(veiculoExistente.getPlaca());
        dao.editar(veiculo);
    }

    @Override
    public void remover(Long id) throws ExcecaoNegocio {
        Veiculo veiculoExistente = dao.consultar(id);
        if (veiculoExistente == null) {
            throw new ExcecaoNegocio("Esse veículo não está cadastrado!");
        }
        dao.remover(id);
    }

    @Override
    public Veiculo consultar(Long id) throws ExcecaoNegocio {
        Veiculo veiculo = dao.consultar(id);
        if (veiculo == null) {
            throw new ExcecaoNegocio("Veículo não encontrado!");
        }
        return veiculo;
    }

    @Override
    public List<Veiculo> listarTodos() {
        return dao.listarTodos();
    }


}
