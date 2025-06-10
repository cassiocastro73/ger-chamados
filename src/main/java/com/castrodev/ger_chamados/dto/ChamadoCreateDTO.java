package com.castrodev.ger_chamados.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChamadoCreateDTO {
    private String titulo;
    private String descricao;
    private String nomeSolicitante;
    private String emailSolicitante;
    private Long dataAbertura;
    private Long statusChamado;
}
