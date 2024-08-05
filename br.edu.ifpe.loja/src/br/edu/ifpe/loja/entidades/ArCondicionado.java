package br.edu.ifpe.loja.entidades;

public class ArCondicionado extends VeiculoDecorator {
    private double precoAcessorio = 2000.0;

    public ArCondicionado(IVeiculo veiculo) {
        super(veiculo);
    }

    @Override
    public double getPreco() {
        return super.getPreco() + precoAcessorio;
    }
}
