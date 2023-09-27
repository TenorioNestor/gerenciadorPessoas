package com.example.cadastro.model;
import jakarta.persistence.*;

//import javax.persistence;


@Entity
@Table(name = "Endereco")
public class CadastroEndereco {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "cep")
    private String cep;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "cidade")
    private String cidade;


    @Column(name = "publi")
    private boolean publi;

    public CadastroEndereco() {

    }

    public CadastroEndereco(String logradouro, String cep, Integer numero, String cidade, boolean published) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.publi = published;
    }

    public long getId() {
        return id;
    }


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public boolean isPubli() {
        return publi;
    }

    public void setPubli(boolean isPubli) {
        this.publi = isPubli;
    }


    @Override
    public String toString() {
        return "Tutorial [Logradouro=" + logradouro + ", CEP=" + cep + ", Numero=" + numero + ", Cidade=" + cidade +", published=" + publi + "]";
    }

}