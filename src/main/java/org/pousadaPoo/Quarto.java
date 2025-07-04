package org.pousadaPoo;

public class Quarto {
    private int numero;
    private String tipo;
    private double preco;
    private boolean disponivel;

    public Quarto(int numero, String tipo, double preco, boolean disponivel) {
        if (numero <= 0) throw new IllegalArgumentException("Número do quarto deve ser maior que zero.");
        if (tipo == null || tipo.isEmpty()) throw new IllegalArgumentException("Tipo de quarto inválido.");
        if (preco <= 0) throw new IllegalArgumentException("Preço deve ser maior que zero.");

        this.numero = numero;
        this.tipo = tipo;
        this.preco = preco;
        this.disponivel = disponivel;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
