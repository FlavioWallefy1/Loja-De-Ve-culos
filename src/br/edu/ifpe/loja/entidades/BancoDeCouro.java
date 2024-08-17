package br.edu.ifpe.loja.entidades;

public class BancoDeCouro implements IVeiculo {
    private double precoAcessorio = 1500.0;
    private IVeiculo veiculo;

    public BancoDeCouro(IVeiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public double getPreco() {
        return veiculo.getPreco() + precoAcessorio;
    }
}
