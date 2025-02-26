package com.UFMSPetSistemas.getpet.controller;

import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import com.UFMSPetSistemas.getpet.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin 
public class AuthController {

    private final UsuarioRepository repo;

    @Autowired
    public AuthController(UsuarioRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/login")
    public ResponseEntity<String> verificaLogin(@RequestBody Usuario usuarioReq) {
        if (usuarioReq == null || usuarioReq.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requisição inválida: usuário ou email ausente");
        }
    
        Usuario req = repo.findByEmail(usuarioReq.getEmail());
        if (req == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    
        if (!req.getSenha().equals(usuarioReq.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    
        return ResponseEntity.ok("Usuário autorizado.");
    }
    
}
