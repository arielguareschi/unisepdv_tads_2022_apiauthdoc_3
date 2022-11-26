package br.edu.unisep.tads.ifud.exception;

import java.util.Date;

public class ErrorResponse {
    
    private Date timestamp;
    private String status;
    private String mensagem;
    private String detalhes;
    
    public ErrorResponse(Date timestamp, String status, String mensagem, String detalhes) {
        this.timestamp = timestamp;
        this.status = status;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    
    
}
