package com.UFMSPetSistemas.getpet.model.repository;

import com.UFMSPetSistemas.getpet.model.entities.Servico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Override
    public List<Servico> findAll();
}