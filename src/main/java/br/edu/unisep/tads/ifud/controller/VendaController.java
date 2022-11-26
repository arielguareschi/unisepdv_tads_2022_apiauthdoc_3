package br.edu.unisep.tads.ifud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unisep.tads.ifud.exception.ResourceNotFoundException;
import br.edu.unisep.tads.ifud.model.Venda;
import br.edu.unisep.tads.ifud.repository.VendaRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class VendaController {
    
    @Autowired
    private VendaRepository vendaRepository;

    @GetMapping("/venda")
    public List<Venda> getAllVendas() {
        return vendaRepository.findAll();
    }
    
    @GetMapping("/venda/{id}")
    public ResponseEntity<Venda> getVendaById(
            @PathVariable(value = "id") Long vendaId) throws ResourceNotFoundException {
        Venda venda = vendaRepository.findById(vendaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Venda nao encontrada: " + vendaId));
        return ResponseEntity.ok().body(venda);
    }

    @PostMapping("/venda")
    public Venda createVenda(@RequestBody Venda venda) {
        return vendaRepository.save(venda);
    }

    @PutMapping("/venda/{id}")
    public ResponseEntity<Venda> updateVenda(
            @PathVariable(value = "id") Long vendaId,
            @RequestBody Venda detalhes) throws ResourceNotFoundException {
        Venda venda = vendaRepository.findById(vendaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Venda nao encontrada: " + vendaId));
        venda.setCliente(detalhes.getCliente());
        venda.setTransportador(detalhes.getTransportador());
        venda.setObservacao(detalhes.getObservacao());
        Venda vendaUpdate = vendaRepository.save(venda);
        return ResponseEntity.ok(vendaUpdate);
    }

    @DeleteMapping("/venda/{id}")
    public Map<String, Boolean> deleteVenda(
            @PathVariable(value = "id") Long vendaId
            ) throws Exception {
        Venda venda = vendaRepository.findById(vendaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Venda nao encontrada: " + vendaId));
        vendaRepository.delete(venda);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
