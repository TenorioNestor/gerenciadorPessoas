package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name="pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "nome", length = 100)
    private String nome;
    @Column(name = "endereco", length = 100)
    private String endereco;

    public Pessoa() {
    }

    public Pessoa(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", nome=" + id + "]";
    }


}
