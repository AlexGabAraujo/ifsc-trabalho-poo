package org.pousadaPoo;

public class ServicoExtra {
    private String nome;
    private double preco;

    public ServicoExtra(String nome, double preco) {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome do serviço não pode ser vazio.");
        if (preco < 0) throw new IllegalArgumentException("Preço do serviço inválido.");
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
