package com.example.cadastro.repository;


import com.example.cadastro.model.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
    List<Cadastro> findByNomeContaining(String nome);

}
