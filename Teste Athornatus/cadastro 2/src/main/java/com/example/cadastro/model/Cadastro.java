package com.example.cadastro.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

//import javax.persistence;


@Entity
@Table(name = "Cadastro")
public class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dataNascimento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    @Column(name = "published")
    private boolean published;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="ENDERECO_ID", referencedColumnName = "id")
    private CadastroEndereco cadastroEndereco;

    public Cadastro() {

    }

    public Cadastro(String nome, Date dataNascimento,CadastroEndereco cadastroEndereco, boolean published) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cadastroEndereco = cadastroEndereco;
        this.published = published;
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

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + getId() + ", nome=" + getNome() + ", data=" + getDataNascimento() + ", Endereco=" + getCadastroEndereco() + "]";

    }

}