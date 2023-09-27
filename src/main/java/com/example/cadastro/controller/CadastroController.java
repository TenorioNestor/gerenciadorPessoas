package com.example.cadastro.controller;

import com.example.cadastro.model.Cadastro;
import com.example.cadastro.model.CadastroEndereco;
import com.example.cadastro.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            Cadastro _cadastro = cadastroRepository.save(cadastro);

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