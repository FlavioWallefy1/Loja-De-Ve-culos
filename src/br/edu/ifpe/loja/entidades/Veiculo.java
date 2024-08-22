package br.edu.ifpe.loja.entidades;

import java.time.LocalDate;

import br.edu.ifpe.loja.util.AdapterSinitro;
import br.edu.ifpe.loja.util.DataSinitro;

public abstract class Veiculo extends EntidadeBase implements IVeiculo{
    private String modelo;
    private String marca;
    private int anoFabricacao;
    private int anoModelo;
    private String placa;
    private double preco;
    private IVeiculo veiculo;
    protected String dataSinistro;
    private static final AdapterSinitro adapterSinitro = new DataSinitro();
    

    public Veiculo(String modelo, String marca,  int anoFabricacao,  int anoModelo, String placa, double preco, String dataSinistro) {
        super();
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.placa = placa;
        this.preco = preco;
        this.dataSinistro = dataSinistro;
    }
    
    public IVeiculo getIVeiculo(){
        return veiculo;
    }

    public void setIVeiculo(IVeiculo veiculo){
        this.veiculo = veiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public String getDataSinistro() {
        return dataSinistro;
    }

    public void setDataSinistro(String dataSinistro) {
        this.dataSinistro = dataSinistro;
    }

    public String getDataSinistroExtenso() {
        return adapterSinitro.formatarExtenso(dataSinistro);
    }

     public String prepararVeiculo() {
    	 verificarDocumentacao();
         return ajusteEspecifico();
     }
     
     public String verificarDocumentacao() {
		return "Verificando documentação do veículo...";
	}
     
     public abstract String ajusteEspecifico();
     
	// Classe interna Builder
    public static class VeiculoBuilder {
        private Long id;
        private String modelo;
        private String marca;
        private int anoFabricacao;
        private int anoModelo;
        private String placa;
        private double preco;
        private String dataSinistro;

        public VeiculoBuilder dataSinistro(String dataSinistro) {
            this.dataSinistro = dataSinistro;
            return this;
        }

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
            return new Veiculo(modelo, marca, anoFabricacao, anoModelo, placa, preco, dataSinistro ) {

				@Override
				public String ajusteEspecifico() {
					return "Ajustando Veiculo";
				}

	
            	
            };
        }
    }}