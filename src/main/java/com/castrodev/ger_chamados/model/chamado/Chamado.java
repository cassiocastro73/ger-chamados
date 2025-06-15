package com.castrodev.ger_chamados.model.chamado;

import com.castrodev.ger_chamados.StatusChamado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime dataAbertura;

    @Enumerated
    @Column(name = "statuschamado")
    private StatusChamado statusChamado;

    public Long getIdChamado() {
        return idChamado;
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

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public StatusChamado getStatusChamado() {
        return statusChamado;
    }

    public void setStatusChamado(StatusChamado statusChamado) {
        this.statusChamado = statusChamado;
    }
}
