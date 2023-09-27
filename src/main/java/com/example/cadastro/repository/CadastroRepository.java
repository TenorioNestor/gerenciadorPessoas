package com.example.cadastro.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cadastro.model.Cadastro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

    List<Cadastro> findByNomeContaining(String nome);
    @Query(value = " select * from cadastro where id = :id ", nativeQuery = true)
    List<Cadastro> encontrarPorNome( @Param("id") Long id );
}
