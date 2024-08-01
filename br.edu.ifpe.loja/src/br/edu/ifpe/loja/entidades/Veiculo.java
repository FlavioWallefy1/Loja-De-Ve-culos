package br.edu.ifpe.loja.entidades;

public class Veiculo extends EntidadeBase implements IVeiculo{

    private String modelo;
    private String marca;
    private int anoFabricacao;
    private int anoModelo;
    private String placa;
    private double preco;

    
    public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Long getId() {
        return super.getId();
    }

    @Override
    public String getModelo() {
        return modelo;
    }

    @Override
    public String getMarca() {
        return marca;
    }

    @Override
    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    @Override
    public int getAnoModelo() {
        return anoModelo;
    }

    @Override
    public String getPlaca() {
        return placa;
    }

    @Override
    public double getPreco() {
        return preco;
    }
    
    public String getDescricaoAcessorios() {
        return "";
    }
    
    
    private Veiculo(VeiculoBuilder builder) {
        this.setId(builder.id); // Set the ID
        this.modelo = builder.modelo;
        this.marca = builder.marca;
        this.anoFabricacao = builder.anoFabricacao;
        this.anoModelo = builder.anoModelo;
        this.placa = builder.placa;
        this.preco = builder.preco;
    }

    public static class VeiculoBuilder {
        private Long id;
        private String modelo;
        private String marca;
        private int anoFabricacao;
        private int anoModelo;
        private String placa;
        private double preco;

        public VeiculoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public VeiculoBuilder modelo(String modelo) {
            this.modelo = modelo;
            return this;
        }

        public VeiculoBuilder marca(String marca) {
            this.marca = marca;
            return this;
        }

        public VeiculoBuilder anoFabricacao(int anoFabricacao) {
            this.anoFabricacao = anoFabricacao;
            return this;
        }

        public VeiculoBuilder anoModelo(int anoModelo) {
            this.anoModelo = anoModelo;
            return this;
        }

        public VeiculoBuilder placa(String placa) {
            this.placa = placa;
            return this;
        }
        
        public VeiculoBuilder preco(double preco) {
            this.preco = preco;
            return this;
        }

        public Veiculo criar() {
            return new Veiculo(this);
        }
    }
}
