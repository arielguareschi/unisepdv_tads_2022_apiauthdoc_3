package br.edu.unisep.tads.ifud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unisep.tads.ifud.exception.ResourceNotFoundException;
import br.edu.unisep.tads.ifud.model.Cliente;
import br.edu.unisep.tads.ifud.repository.ClienteRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/cliente")
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
    
    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> getClienteById(
            @PathVariable(value = "id") Long clienteId) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cliente nao encontrada: " + clienteId));
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/cliente")
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> updateCliente(
            @PathVariable(value = "id") Long clienteId,
            @RequestBody Cliente detalhes) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cliente nao encontrada: " + clienteId));
        cliente.setNome(detalhes.getNome());
        cliente.setEndereco(detalhes.getEndereco());
        cliente.setFone(detalhes.getFone());
        cliente.setCep(detalhes.getCep());
        cliente.setLogin(detalhes.getLogin());
        cliente.setSenha(detalhes.getSenha());
        cliente.setEmail(detalhes.getEmail());
        Cliente clienteUpdate = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteUpdate);
    }

    @DeleteMapping("/cliente/{id}")
    public Map<String, Boolean> deleteCliente(
            @PathVariable(value = "id") Long clienteId
            ) throws Exception {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cliente nao encontrada: " + clienteId));
        clienteRepository.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
