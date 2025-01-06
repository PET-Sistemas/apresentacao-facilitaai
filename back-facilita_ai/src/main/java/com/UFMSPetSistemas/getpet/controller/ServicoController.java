package com.UFMSPetSistemas.getpet.controller;

import com.UFMSPetSistemas.getpet.model.entities.Servico;
import com.UFMSPetSistemas.getpet.model.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos") // Base da URL para os endpoints
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public ResponseEntity<List<Servico>> getServicos() {
        List<Servico> servicos = servicoRepository.findAll();
        return ResponseEntity.ok(servicos); // Retorna HTTP 200 com a lista de serviços
    }

    @PostMapping
    public ResponseEntity<Servico> setServico(@RequestBody Servico servico) {
        try {
            Servico novoServico = servicoRepository.save(servico);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoServico); // Retorna HTTP 201 com o objeto criado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna HTTP 500 em caso de erro
        }
    }
}