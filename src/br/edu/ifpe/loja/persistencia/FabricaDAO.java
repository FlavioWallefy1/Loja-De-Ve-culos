package br.edu.ifpe.loja.persistencia;

public class FabricaDAO {

	public static IVeiculoDAO getVeiculoDAO() {
		return VeiculoDAOList.getInstancia();
	}
}
