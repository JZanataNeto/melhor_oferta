package com.joaozanata.melhor_oferta;

public class Oferta {
    private int id;
    private String produto;
    private double preco;
    private double frete;
    private int prazo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }
}
