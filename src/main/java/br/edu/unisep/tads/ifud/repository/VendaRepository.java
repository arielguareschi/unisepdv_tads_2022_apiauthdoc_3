package br.edu.unisep.tads.ifud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unisep.tads.ifud.model.Venda;

@Repository
public interface VendaRepository 
    extends JpaRepository<Venda, Long> {
}
