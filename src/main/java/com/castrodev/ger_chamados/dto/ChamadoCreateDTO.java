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
}
