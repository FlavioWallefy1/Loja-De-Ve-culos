package br.edu.ifpe.loja.entidades;

public class Roda implements IVeiculo{
    private double precoAcessorio = 1000.0;
    private IVeiculo veiculo;

    public Roda(IVeiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public double getPreco() {
        return veiculo.getPreco() + precoAcessorio;
    }
}
