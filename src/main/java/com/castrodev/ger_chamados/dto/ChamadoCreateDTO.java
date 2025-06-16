package com.castrodev.ger_chamados.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChamadoCreateDTO {
    @NotBlank(message = "O título é obrigatório")
    private String titulo;
    @NotBlank(message = "A descrição é obrigatório")
    private String descricao;
    @NotBlank(message = "O nome do solicitante é obrigatório")
    private String nomeSolicitante;
    @NotBlank(message = "O e-mail do solicitante é obrigatório")
    @Email(message = "O e-mail do solicitante deve ser válido")
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
