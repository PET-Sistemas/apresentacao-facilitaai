package com.UFMSPetSistemas.getpet.model.repository;

import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    //public Usuario findByGerenteId(Integer idGerente);
    public Usuario findByEmail(String email);
    public Usuario findById(int id);

}
