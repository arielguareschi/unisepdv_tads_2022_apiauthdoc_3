package br.edu.unisep.tads.ifud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unisep.tads.ifud.model.User;

@Repository
public interface UserRepository 
    extends JpaRepository<User, Long> {
        Optional<User> findByUsername(String user);
        Boolean existsByUsername(String user);
        Boolean existsByEmail(String email);
}
