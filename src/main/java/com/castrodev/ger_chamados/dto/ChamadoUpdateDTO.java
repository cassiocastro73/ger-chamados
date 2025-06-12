package com.castrodev.ger_chamados.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChamadoUpdateDTO {
    private Long idChamado;
    private String descricao;
    private Long statusChamado;

    public Long getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(Long idChamado) {
        this.idChamado = idChamado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getStatusChamado() {
        return statusChamado;
    }

    public void setStatusChamado(Long statusChamado) {
        this.statusChamado = statusChamado;
    }
}
