package br.edu.unisep.tads.ifud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unisep.tads.ifud.exception.ResourceNotFoundException;
import br.edu.unisep.tads.ifud.model.Transportador;
import br.edu.unisep.tads.ifud.repository.TransportadorRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class TransportadorController {
    
    @Autowired
    private TransportadorRepository transportadorRepository;

    @GetMapping("/transportador")
    public List<Transportador> getAllTransportadors() {
        return transportadorRepository.findAll();
    }
    
    @GetMapping("/transportador/{id}")
    public ResponseEntity<Transportador> getTransportadorById(
            @PathVariable(value = "id") Long transportadorId) throws ResourceNotFoundException {
        Transportador transportador = transportadorRepository.findById(transportadorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Transportador nao encontrada: " + transportadorId));
        return ResponseEntity.ok().body(transportador);
    }

    @PostMapping("/transportador")
    public Transportador createTransportador(@RequestBody Transportador transportador) {
        return transportadorRepository.save(transportador);
    }

    @PutMapping("/transportador/{id}")
    public ResponseEntity<Transportador> updateTransportador(
            @PathVariable(value = "id") Long transportadorId,
            @RequestBody Transportador detalhes) throws ResourceNotFoundException {
        Transportador transportador = transportadorRepository.findById(transportadorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Transportador nao encontrada: " + transportadorId));
        transportador.setNome(detalhes.getNome());
        transportador.setFone(detalhes.getFone());
        transportador.setPlaca(detalhes.getPlaca());
        Transportador transportadorUpdate = transportadorRepository.save(transportador);
        return ResponseEntity.ok(transportadorUpdate);
    }

    @DeleteMapping("/transportador/{id}")
    public Map<String, Boolean> deleteTransportador(
            @PathVariable(value = "id") Long transportadorId
            ) throws Exception {
        Transportador transportador = transportadorRepository.findById(transportadorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Transportador nao encontrada: " + transportadorId));
        transportadorRepository.delete(transportador);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
