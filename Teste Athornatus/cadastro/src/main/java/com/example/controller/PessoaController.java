package com.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Pessoa;
import com.example.repository.PessoaRep;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PessoaController {
    @Autowired
    PessoaRep pessoaRep;


    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> getAllPessoas(@RequestParam(required = false) String nome) {
        try {
            List<Pessoa> pessoas = new ArrayList<>();

            if (nome == null)
                pessoas.addAll(pessoaRep.findAll());
            else
                pessoas.addAll(pessoaRep.encontrarPorNome(nome));

            if (pessoas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pessoas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> getPorId(@PathVariable("id") long id) {
        Optional<Pessoa> pessoaData = pessoaRep.findById(id);

        return pessoaData.map(pessoa -> new ResponseEntity<>(pessoa, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        try {
            Pessoa _pessoa = pessoaRep
                    .save(new Pessoa(pessoa.getNome(), pessoa.getEndereco()));
            return new ResponseEntity<>(_pessoa, HttpStatus.CREATED);
        } catch (Exception e) {
            /*return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);*/
            log.error("Ocorreu um erro ao criar uma pessoa: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> updatePessoas(@PathVariable("id") Long id, @RequestBody Pessoa pessoa) {
        Optional<Pessoa> pessoaData = pessoaRep.findById(id);

        if (pessoaData.isPresent()) {
            Pessoa _pessoa = pessoaData.get();
            _pessoa.setNome(pessoa.getNome());
            return new ResponseEntity<>(pessoaRep.save(pessoa), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<HttpStatus> deletePessoas(@PathVariable("id") long id) {
        try {
            pessoaRep.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pessoas")
    public ResponseEntity<HttpStatus> deleteAllPessoas() {
        try {
            pessoaRep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
