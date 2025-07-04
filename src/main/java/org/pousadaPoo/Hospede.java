package org.pousadaPoo;

public class Hospede extends Pessoa{
    private String cpf;
    private String contato;
    private String endereco;

    public Hospede(String nome, String cpf, String contato, String endereco) {
        super(nome);
        this.cpf = cpf;
        this.contato = contato;
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
