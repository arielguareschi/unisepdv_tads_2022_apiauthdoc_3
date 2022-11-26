package br.edu.unisep.tads.ifud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unisep.tads.ifud.exception.ResourceNotFoundException;
import br.edu.unisep.tads.ifud.model.Fornecedor;
import br.edu.unisep.tads.ifud.repository.FornecedorRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class FornecedorController {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping("/fornecedor")
    public List<Fornecedor> getAllFornecedors() {
        return fornecedorRepository.findAll();
    }
    
    @GetMapping("/fornecedor/{id}")
    public ResponseEntity<Fornecedor> getFornecedorById(
            @PathVariable(value = "id") Long fornecedorId) throws ResourceNotFoundException {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Fornecedor nao encontrada: " + fornecedorId));
        return ResponseEntity.ok().body(fornecedor);
    }

    @PostMapping("/fornecedor")
    public Fornecedor createFornecedor(@RequestBody Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @PutMapping("/fornecedor/{id}")
    public ResponseEntity<Fornecedor> updateFornecedor(
            @PathVariable(value = "id") Long fornecedorId,
            @RequestBody Fornecedor detalhes) throws ResourceNotFoundException {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Fornecedor nao encontrada: " + fornecedorId));
        fornecedor.setNome(detalhes.getNome());
        fornecedor.setEndereco(detalhes.getEndereco());
        fornecedor.setFone(detalhes.getFone());
        fornecedor.setCep(detalhes.getCep());
        fornecedor.setCnpj(detalhes.getCnpj());
        fornecedor.setIe(detalhes.getIe());
        fornecedor.setContato(detalhes.getContato());
        Fornecedor fornecedorUpdate = fornecedorRepository.save(fornecedor);
        return ResponseEntity.ok(fornecedorUpdate);
    }

    @DeleteMapping("/fornecedor/{id}")
    public Map<String, Boolean> deleteFornecedor(
            @PathVariable(value = "id") Long fornecedorId
            ) throws Exception {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Fornecedor nao encontrada: " + fornecedorId));
        fornecedorRepository.delete(fornecedor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
