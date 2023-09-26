package com.example.repository;


import java.util.List;

import com.example.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRep extends JpaRepository<Pessoa, Long> {

    List<Pessoa> encontrarPorNome(String name);
}
