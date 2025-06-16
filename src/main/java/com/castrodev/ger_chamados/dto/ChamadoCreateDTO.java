package com.castrodev.ger_chamados.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChamadoCreateDTO {
    private String titulo;
    private String descricao;
    private String nomeSolicitante;
    private String emailSolicitante;

    public ChamadoCreateDTO(String titulo, String descricao, String nomeSolicitante, String emailSolicitante) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nomeSolicitante = nomeSolicitante;
        this.emailSolicitante = emailSolicitante;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }
}
