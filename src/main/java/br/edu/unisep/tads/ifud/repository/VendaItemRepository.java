package br.edu.unisep.tads.ifud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unisep.tads.ifud.model.VendaItem;

@Repository
public interface VendaItemRepository 
    extends JpaRepository<VendaItem, Long> {
}
