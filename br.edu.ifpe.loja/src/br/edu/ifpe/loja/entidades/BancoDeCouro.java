package br.edu.ifpe.loja.entidades;

public class BancoDeCouro extends VeiculoDecorator{
	
	private double precoAcessorio;
    private String descricaoAcessorio;

	public BancoDeCouro(IVeiculo veiculo, double precoAcessorio, String descricaoAcessorio) {
		 super(veiculo);
	     this.precoAcessorio = precoAcessorio;
	     this.descricaoAcessorio = descricaoAcessorio;
	}

    @Override
    public double getPreco() {
        return super.getPreco() + precoAcessorio;
    }

    public String getDescricaoAcessorio() {
        return descricaoAcessorio;
    }
}
