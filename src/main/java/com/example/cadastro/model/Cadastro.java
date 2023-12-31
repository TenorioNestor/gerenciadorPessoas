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

    @Column(name = "documento")
    private Long documento;

    @Column(name = "dataNascimento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;


    public Cadastro(String nome, Long documento,Date dataNascimento) {
        this.nome = nome;
        this.documento = documento;
        this.dataNascimento = dataNascimento;
    }

    public Cadastro() {

    }

    public long getId() {
        return id;
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

    public void setId(long id) {
        this.id = id;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }
}