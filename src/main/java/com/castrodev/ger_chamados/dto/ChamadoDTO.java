package com.castrodev.ger_chamados.dto;

import com.castrodev.ger_chamados.model.chamado.Chamado;
import org.springframework.beans.BeanUtils;

public class ChamadoDTO {
    private Long idChamado;
    private String titulo;
    private String descicao;
    private String nomeSolicitante;
    private String emailSolicitante;
    private Long dataAbertura;
    private String statusChamado;

    public ChamadoDTO(Chamado chamado) {
        BeanUtils.copyProperties(chamado, this);
    }

    public ChamadoDTO(){

    }

    public String getStatusChamado() {
        return statusChamado;
    }

    public void setStatusChamado(String statusChamado) {
        this.statusChamado = statusChamado;
    }

    public Long getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Long dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getDescicao() {
        return descicao;
    }

    public void setDescicao(String descicao) {
        this.descicao = descicao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(Long idChamado) {
        this.idChamado = idChamado;
    }
}
