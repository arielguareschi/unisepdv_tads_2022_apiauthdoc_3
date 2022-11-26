package br.edu.unisep.tads.ifud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unisep.tads.ifud.exception.ResourceNotFoundException;
import br.edu.unisep.tads.ifud.model.User;
import br.edu.unisep.tads.ifud.repository.UserRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class UserController {
    
    @Autowired
    private UserRepository usuarioRepository;

    @GetMapping("/usuario")
    public List<User> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
    
    @GetMapping("/usuario/{id}")
    public ResponseEntity<User> getUsuarioById(
            @PathVariable(value = "id") Long usuarioId) throws ResourceNotFoundException {
        User usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario nao encontrada: " + usuarioId));
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/usuario")
    public User createUsuario(@RequestBody User usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<User> updateUsuario(
            @PathVariable(value = "id") Long usuarioId,
            @RequestBody User detalhes) throws ResourceNotFoundException {
        User usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario nao encontrada: " + usuarioId));
        usuario.setUsername(detalhes.getUsername());
        usuario.setEmail(detalhes.getEmail());
        usuario.setPassword(detalhes.getPassword());
        User usuarioUpdate = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioUpdate);
    }

    @DeleteMapping("/usuario/{id}")
    public Map<String, Boolean> deleteUsuario(
            @PathVariable(value = "id") Long usuarioId
            ) throws Exception {
        User usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario nao encontrada: " + usuarioId));
        usuarioRepository.delete(usuario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
