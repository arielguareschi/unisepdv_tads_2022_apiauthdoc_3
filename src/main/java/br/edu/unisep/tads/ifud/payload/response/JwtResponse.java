package br.edu.unisep.tads.ifud.payload.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    
    private String token;
    private String type = "Bearer ";
    private Long id;
    private String username;
    private String email;
    private List<String> role;
    
    public JwtResponse(String token, Long id, String username, String email, List<String> role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    
}
