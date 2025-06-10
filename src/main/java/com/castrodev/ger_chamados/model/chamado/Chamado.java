package com.castrodev.ger_chamados.model.chamado;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idchamado")
    private Long idChamado;
    private String titulo;
    private String descricao;
    @Column(name = "nomesolicitante")
    private String nomeSolicitante;
    @Column(name = "emailsolicitante")
    private String emailSolicitante;

    @Column(name = "dataabertura")
    private Long dataAbertura;

    @Column(name = "statuschamado")
    private Long statusChamado;

    public Long getIdChamado() {
        return idChamado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Long getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Long dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Long getStatusChamado() {
        return statusChamado;
    }

    public void setStatusChamado(Long statusChamado) {
        this.statusChamado = statusChamado;
    }
}
