package com.example.cadastro.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;



@Entity
@Table(name = "Cadastro")
public class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String nome;


    @Column(name = "dataNascimento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="endereco_id", referencedColumnName = "id")
    private CadastroEndereco cadastroEndereco;

    public Cadastro(String nome1, Date date){}
    public Cadastro(String nome, Date dataNascimento, CadastroEndereco cadastroEndereco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cadastroEndereco = cadastroEndereco;
    }

    public Cadastro() {

    }

    public long getId() {
        return id;
    }
    public CadastroEndereco getCadastroEndereco() {
        return cadastroEndereco;
    }

    public void setCadastroEndereco(CadastroEndereco cadastroEndereco) {
        this.cadastroEndereco = cadastroEndereco;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", nome=" + nome + ", data=" + dataNascimento + ", Endereco=" + cadastroEndereco + "]";

    }


    public void setId(long id) {
        this.id = id;
    }
}