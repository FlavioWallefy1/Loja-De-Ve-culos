package br.edu.ifpe.loja.entidades;

public class Veiculo extends EntidadeBase {

    private String modelo;
    private String marca;
    private int anoFabricacao;
    private int anoModelo;
    private String placa;

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
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

    private Veiculo(VeiculoBuilder builder) {
        this.setId(builder.id); // Set the ID
        this.modelo = builder.modelo;
        this.marca = builder.marca;
        this.anoFabricacao = builder.anoFabricacao;
        this.anoModelo = builder.anoModelo;
        this.placa = builder.placa;
    }

    public static class VeiculoBuilder {
        private Long id;
        private String modelo;
        private String marca;
        private int anoFabricacao;
        private int anoModelo;
        private String placa;

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

        public Veiculo criar() {
            return new Veiculo(this);
        }
    }
}
