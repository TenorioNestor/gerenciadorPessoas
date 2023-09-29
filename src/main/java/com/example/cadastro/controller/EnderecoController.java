package com.example.cadastro.controller;

import com.example.cadastro.exception.ResourceNotFoundException;
import com.example.cadastro.model.CadastroEndereco;
import com.example.cadastro.repository.CadastroRepository;
import com.example.cadastro.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EnderecoController {

    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    CadastroRepository cadastroRepository;

    @GetMapping("/cadastro/{cadastroId}/endereco")
    public ResponseEntity<List<CadastroEndereco>> getAllEnderecosByCadastroId(@PathVariable(value = "cadastroId") Long cadastroId) {
        if (!cadastroRepository.existsById(cadastroId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + cadastroId);
        }

        List<CadastroEndereco> comments = enderecoRepository.findByCadastroId(cadastroId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
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

    @PostMapping("/cadastro/{cadastroId}/endereco")
    public ResponseEntity<CadastroEndereco> createEndereco(@PathVariable("cadastroId") Long cadastroId, @RequestBody CadastroEndereco enderecoRequest) {
        CadastroEndereco cadastroEndereco = cadastroRepository.findById(cadastroId).map(cadastro -> {
            enderecoRequest.setCadastro(cadastro);
            return enderecoRepository.save(enderecoRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + cadastroId));
        return new ResponseEntity<>(cadastroEndereco, HttpStatus.CREATED);
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