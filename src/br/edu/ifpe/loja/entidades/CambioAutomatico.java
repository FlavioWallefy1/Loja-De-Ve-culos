package br.edu.ifpe.loja.entidades;

public class CambioAutomatico implements IVeiculo {

    private double precoAcessorio = 5000.0;
    private IVeiculo veiculo;

    public CambioAutomatico(IVeiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public double getPreco() {
        return veiculo.getPreco() + precoAcessorio;
    }
}
