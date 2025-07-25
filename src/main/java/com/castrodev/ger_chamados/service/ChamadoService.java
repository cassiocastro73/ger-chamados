package com.castrodev.ger_chamados.service;

import com.castrodev.ger_chamados.StatusChamado;
import com.castrodev.ger_chamados.dto.ChamadoCreateDTO;
import com.castrodev.ger_chamados.dto.ChamadoDTO;
import com.castrodev.ger_chamados.dto.ChamadoUpdateDTO;
import com.castrodev.ger_chamados.model.chamado.Chamado;
import com.castrodev.ger_chamados.repository.ChamadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    @Autowired
    ChamadoRepository chamadoRepository;

    public List<ChamadoDTO> buscarTodos(){
        return chamadoRepository.findAll(Sort.by(Sort.Direction.DESC, "dataAbertura")).stream()
                .map(ChamadoDTO::new).collect(Collectors.toList());
    }

    public Chamado buscarPorId(Long id){
        return chamadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chamado com ID " + id + " não encontrado."));
    }

    public Chamado criarChamado(ChamadoCreateDTO chamadoCreateDTO){
        Chamado chamado = new Chamado();
        BeanUtils.copyProperties(chamadoCreateDTO, chamado);
        chamado.setStatusChamado(StatusChamado.ABERTO);
        chamado.setDataAbertura(LocalDateTime.now());
        chamadoRepository.save(chamado);
        return chamado;
    }
    public void excluirChamado(Long idChamado){
        chamadoRepository.findById(idChamado)
                .orElseThrow(() -> new EntityNotFoundException("Chamado com ID " + idChamado + " não encontrado."));
        chamadoRepository.deleteById(idChamado);
    }

    public ChamadoDTO atualizarChamado(ChamadoUpdateDTO chamadoUpdateDTO){
       Chamado chamado = chamadoRepository.findById(chamadoUpdateDTO.getIdChamado())
               .orElseThrow(() -> new EntityNotFoundException("Chamado com ID " + chamadoUpdateDTO.getIdChamado() + " não encontrado."));
       chamado.setStatusChamado(chamadoUpdateDTO.getStatusChamado());
       if(chamadoUpdateDTO.getDescricao() != null){
           chamado.setDescricao(chamadoUpdateDTO.getDescricao());
       }
        chamadoRepository.save(chamado);
        return new ChamadoDTO(chamado);
    }

    public List<ChamadoDTO> buscarPorStatus(StatusChamado status) {
        return chamadoRepository.findByStatusChamado(status);
    }
}
