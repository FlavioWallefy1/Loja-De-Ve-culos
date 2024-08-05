package br.edu.ifpe.loja.entidades;

public abstract class VeiculoDecorator implements IVeiculo {
    protected IVeiculo veiculo;

    public VeiculoDecorator(IVeiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public Long getId() {
        return veiculo.getId();
    }

    @Override
    public String getModelo() {
        return veiculo.getModelo();
    }

    @Override
    public String getMarca() {
        return veiculo.getMarca();
    }

    @Override
    public int getAnoFabricacao() {
        return veiculo.getAnoFabricacao();
    }

    @Override
    public int getAnoModelo() {
        return veiculo.getAnoModelo();
    }

    @Override
    public String getPlaca() {
        return veiculo.getPlaca();
    }

    @Override
    public double getPreco() {
        return veiculo.getPreco(); 
    }
}
