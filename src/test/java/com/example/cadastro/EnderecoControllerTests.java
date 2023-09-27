package com.example.cadastro;

import com.example.cadastro.model.CadastroEndereco;
import com.example.cadastro.repository.EnderecoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EnderecoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnderecoRepository enderecoRepository;

    @Test
    public void testGetAllEndereco() throws Exception {
        List<CadastroEndereco> enderecoList = new ArrayList<>();
        enderecoList.add(new CadastroEndereco("Rua A", "12345", 1, "Cidade A"));
        enderecoList.add(new CadastroEndereco("Rua B", "54321", 2, "Cidade B"));

        Mockito.when(enderecoRepository.findAll()).thenReturn(enderecoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/enderecos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].logradouro").value("Rua A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].logradouro").value("Rua B"));
    }

    @Test
    public void testGetEnderecoById() throws Exception {
        CadastroEndereco endereco = new CadastroEndereco("Rua A", "12345", 1, "Cidade A");
        Optional<CadastroEndereco> optionalEndereco = Optional.of(endereco);

        Mockito.when(enderecoRepository.findById(1L)).thenReturn(optionalEndereco);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/endereco/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro").value("Rua A"));
    }

    @Test
    public void testGetEnderecoByIdNotFound() throws Exception {
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/endereco/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // Implemente testes similares para os outros métodos (create, update, delete) conforme necessário
}
