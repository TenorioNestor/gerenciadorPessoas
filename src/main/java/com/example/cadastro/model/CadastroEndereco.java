package com.example.cadastro.model;
import jakarta.persistence.*;

//import javax.persistence;


@Entity
@Table(name = "Endereco")
public class CadastroEndereco {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "cep")
    private String cep;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "cidade")
    private String cidade;

    public CadastroEndereco(){}
    public CadastroEndereco(String logradouro, String cep, Integer numero, String cidade) {

        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
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



    @Override
    public String toString() {
        return "Tutorial [Logradouro=" + logradouro + ", CEP=" + cep + ", Numero=" + numero + ", Cidade=" + cidade +"]";
    }

}