package br.edu.unisep.tads.ifud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unisep.tads.ifud.model.Cliente;

@Repository
public interface ClienteRepository 
    extends JpaRepository<Cliente, Long> {
}
