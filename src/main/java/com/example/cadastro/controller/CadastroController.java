package com.example.cadastro.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import com.example.cadastro.model.CadastroEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.model.Cadastro;
import com.example.cadastro.repository.CadastroRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CadastroController {

    @Autowired
    CadastroRepository cadastroRepository;


    @GetMapping("/cadastros")
    public ResponseEntity<List<Cadastro>> getAllCadastro(@RequestParam(required = false) String nome) {
        try {
            List<Cadastro> cadastros = new ArrayList<Cadastro>();

            if (nome == null)
                cadastroRepository.findAll().forEach(cadastros::add);
            else
                cadastroRepository.findByNomeContaining(nome).forEach(cadastros::add);

            if (cadastros.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cadastros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cadastro/{id}")
    public ResponseEntity<Cadastro> getCadastroById(@PathVariable("id") long id) {
        Optional<Cadastro> cadastroData = cadastroRepository.findById(id);
        if (cadastroData.isPresent()) {
            return new ResponseEntity<>(cadastroData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Cadastro> createCadastro(@RequestBody Cadastro cadastro) {
        try {
            CadastroEndereco cadastroEndereco = cadastro.getCadastroEndereco();
            Cadastro _cadastro = cadastroRepository.save(
                    new Cadastro(cadastro.getNome(), cadastro.getDataNascimento(),cadastroEndereco));

            return  ResponseEntity.status(HttpStatus.CREATED).body(_cadastro);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cadastro/{id}")
    public ResponseEntity<Cadastro> updateCadastro(@PathVariable("id") long id, @RequestBody Cadastro cadastro) {
        Optional<Cadastro> cadastroData = cadastroRepository.findById(id);

        if (cadastroData.isPresent()) {
            Cadastro _cadastro = cadastroData.get();
            _cadastro.setNome(cadastro.getNome());
            _cadastro.setDataNascimento(cadastro.getDataNascimento());
            return new ResponseEntity<>(cadastroRepository.save(_cadastro), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cadastro/{id}")
    public ResponseEntity<HttpStatus> deleteCadastro(@PathVariable("id") long id) {
        try {
            cadastroRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cadastro")
    public ResponseEntity<HttpStatus> deleteAllCadastro() {
        try {
            cadastroRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}