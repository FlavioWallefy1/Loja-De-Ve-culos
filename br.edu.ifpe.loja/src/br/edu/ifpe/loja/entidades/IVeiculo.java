package br.edu.ifpe.loja.entidades;

public interface IVeiculo {
    Long getId();
    String getModelo();
    String getMarca();
    int getAnoFabricacao();
    int getAnoModelo();
    String getPlaca();
    double getPreco();
}
