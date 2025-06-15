package com.castrodev.ger_chamados.repository;

import com.castrodev.ger_chamados.StatusChamado;
import com.castrodev.ger_chamados.dto.ChamadoDTO;
import com.castrodev.ger_chamados.model.chamado.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    List<ChamadoDTO> findByStatusChamado(StatusChamado statusChamado);
}
