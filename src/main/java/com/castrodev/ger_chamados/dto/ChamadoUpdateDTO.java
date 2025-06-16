package com.castrodev.ger_chamados.dto;

import com.castrodev.ger_chamados.StatusChamado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChamadoUpdateDTO {
    private Long idChamado;
    private String descricao;
    private StatusChamado statusChamado;

    public ChamadoUpdateDTO(Long idChamado, String descricao, StatusChamado statusChamado) {
        this.idChamado = idChamado;
        this.descricao = descricao;
        this.statusChamado = statusChamado;
    }

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

    public StatusChamado getStatusChamado() {
        return statusChamado;
    }

    public void setStatusChamado(StatusChamado statusChamado) {
        this.statusChamado = statusChamado;
    }
}
