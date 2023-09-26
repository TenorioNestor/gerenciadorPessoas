package com.example.cadastro.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cadastro.model.Cadastro;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
    List<Cadastro> findByPublished(boolean published);

    List<Cadastro> findByNomeContaining(String nome);
}
