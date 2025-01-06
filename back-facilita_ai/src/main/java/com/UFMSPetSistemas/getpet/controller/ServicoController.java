package com.UFMSPetSistemas.getpet.controller;

import com.UFMSPetSistemas.getpet.model.entities.Servico;
import com.UFMSPetSistemas.getpet.model.repository.ServicoRepository;
import com.UFMSPetSistemas.getpet.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos") // Base da URL para os endpoints
@CrossOrigin
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private UsuarioRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Servico>> getServicos() {
        List<Servico> servicos = servicoRepository.findAll();
        return ResponseEntity.ok(servicos); // Retorna HTTP 200 com a lista de serviços
    }

    @PostMapping
    public ResponseEntity<Servico> setServico(@RequestBody Servico servico) {
        try {
            System.out.println("Data recebida: " + servico.getValor());

            Servico prepareServico = servico;
            prepareServico.setUsuario(userRepository.findByEmail(servico.getUsuario().getEmail()));
            Servico novoServico = servicoRepository.save(prepareServico);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoServico); // Retorna HTTP 201 com o objeto criado
        } catch (Exception e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna HTTP 500 em caso de erro
        }
    }
}