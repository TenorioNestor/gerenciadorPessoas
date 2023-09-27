package com.example.cadastro.repository;


import com.example.cadastro.model.CadastroEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnderecoRepository extends JpaRepository<CadastroEndereco, Long> {
    List<CadastroEndereco> findByLogradouroContaining(String logradouro);
}
