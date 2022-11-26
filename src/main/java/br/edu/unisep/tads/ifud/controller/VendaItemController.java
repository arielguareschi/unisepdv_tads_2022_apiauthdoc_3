package br.edu.unisep.tads.ifud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unisep.tads.ifud.exception.ResourceNotFoundException;
import br.edu.unisep.tads.ifud.model.VendaItem;
import br.edu.unisep.tads.ifud.repository.VendaItemRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class VendaItemController {
    
    @Autowired
    private VendaItemRepository vendaItemRepository;

    @GetMapping("/venda-item")
    public List<VendaItem> getAllVendaItems() {
        return vendaItemRepository.findAll();
    }
    
    @GetMapping("/venda-item/{id}")
    public ResponseEntity<VendaItem> getVendaItemById(
            @PathVariable(value = "id") Long vendaItemId) throws ResourceNotFoundException {
        VendaItem vendaItem = vendaItemRepository.findById(vendaItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "VendaItem nao encontrada: " + vendaItemId));
        return ResponseEntity.ok().body(vendaItem);
    }

    @PostMapping("/venda-item")
    public VendaItem createVendaItem(@RequestBody VendaItem vendaItem) {
        return vendaItemRepository.save(vendaItem);
    }

    @PutMapping("/venda-item/{id}")
    public ResponseEntity<VendaItem> updateVendaItem(
            @PathVariable(value = "id") Long vendaItemId,
            @RequestBody VendaItem detalhes) throws ResourceNotFoundException {
        VendaItem vendaItem = vendaItemRepository.findById(vendaItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "VendaItem nao encontrada: " + vendaItemId));
        vendaItem.setVenda(detalhes.getVenda());
        vendaItem.setProduto(detalhes.getProduto());
        vendaItem.setQuantidade(detalhes.getQuantidade());
        vendaItem.setPreco(detalhes.getPreco());
        vendaItem.setObservacao(detalhes.getObservacao());
        VendaItem vendaItemUpdate = vendaItemRepository.save(vendaItem);
        return ResponseEntity.ok(vendaItemUpdate);
    }

    @DeleteMapping("/venda-item/{id}")
    public Map<String, Boolean> deleteVendaItem(
            @PathVariable(value = "id") Long vendaItemId
            ) throws Exception {
        VendaItem vendaItem = vendaItemRepository.findById(vendaItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "VendaItem nao encontrada: " + vendaItemId));
        vendaItemRepository.delete(vendaItem);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
