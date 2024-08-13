package br.edu.ifpe.loja.entidades;

public class ArCondicionado implements IVeiculo{
    private double precoAcessorio = 2000.0;
    private IVeiculo veiculo;

    public ArCondicionado(IVeiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public double getPreco() {
        return veiculo.getPreco() + precoAcessorio;
    }
}
