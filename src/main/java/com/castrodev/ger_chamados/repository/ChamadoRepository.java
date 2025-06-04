package com.castrodev.ger_chamados.repository;

import com.castrodev.ger_chamados.model.chamado.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
