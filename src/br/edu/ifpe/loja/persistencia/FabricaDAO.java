package br.edu.ifpe.loja.persistencia;

import br.edu.ifpe.loja.entidades.Veiculo;

public class FabricaDAO {

    private static GenericDAO<Veiculo> veiculoDAO;

    public static GenericDAO<Veiculo> getVeiculoDAO() {
        if (veiculoDAO == null) {
            veiculoDAO = new GenericDAOList<>();
        }
        return veiculoDAO;
    }
}
