package com.example.cadastro.controller;

import com.example.cadastro.model.CadastroEndereco;
import com.example.cadastro.repository.EnderecoRepository;
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
public class EnderecoController {

    @Autowired
    EnderecoRepository enderecoRepository;

    @GetMapping("/enderecos")
    public ResponseEntity<List<CadastroEndereco>> getAllEndereco(@RequestParam(required = false) String logradouro) {
        try {
            List<CadastroEndereco> cadastroEnderecos = new ArrayList<CadastroEndereco>();

            if (logradouro == null)
                enderecoRepository.findAll().forEach(cadastroEnderecos::add);
            else
                enderecoRepository.findByLogradouroContaining(logradouro).forEach(cadastroEnderecos::add);

            if (cadastroEnderecos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cadastroEnderecos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/endereco/{id}")
    public ResponseEntity<CadastroEndereco> getEnderecoById(@PathVariable("id") long id) {
        Optional<CadastroEndereco> enderecoData = enderecoRepository.findById(id);

        if (enderecoData.isPresent()) {
            return new ResponseEntity<>(enderecoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/endereco")
    public ResponseEntity<CadastroEndereco> createEndereco(@RequestBody CadastroEndereco cadastroEndereco) {
        try {
            CadastroEndereco _cadastroEndereco = enderecoRepository
                    .save(new CadastroEndereco(cadastroEndereco.getLogradouro(), cadastroEndereco.getCep(),cadastroEndereco.getNumero(), cadastroEndereco.getCidade()));
            return new ResponseEntity<>(_cadastroEndereco, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/endereco/{id}")
    public ResponseEntity<CadastroEndereco> updateEndereco(@PathVariable("id") long id, @RequestBody CadastroEndereco cadastroEndereco) {
        Optional<CadastroEndereco> cadastroData = enderecoRepository.findById(id);

        if (cadastroData.isPresent()) {
            CadastroEndereco _cadastroEndereco = cadastroData.get();
            _cadastroEndereco.setLogradouro(cadastroEndereco.getLogradouro());
            _cadastroEndereco.setCep(cadastroEndereco.getCep());
            _cadastroEndereco.setNumero(cadastroEndereco.getNumero());
            _cadastroEndereco.setCidade(cadastroEndereco.getCidade());
            return new ResponseEntity<>(enderecoRepository.save(_cadastroEndereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/endereco/{id}")
    public ResponseEntity<HttpStatus> deleteEndereco(@PathVariable("id") long id) {
        try {
            enderecoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/endereco")
    public ResponseEntity<HttpStatus> deleteAllEndereco() {
        try {
            enderecoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}