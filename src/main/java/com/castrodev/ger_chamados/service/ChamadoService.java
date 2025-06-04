package com.castrodev.ger_chamados.service;

import com.castrodev.ger_chamados.dto.ChamadoDTO;
import com.castrodev.ger_chamados.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    @Autowired
    ChamadoRepository chamadoRepository;

    public List<ChamadoDTO> buscarTodos(){
        return chamadoRepository.findAll().stream()
                .map(ChamadoDTO::new).collect(Collectors.toList());
    }

    public ChamadoDTO buscarPorId(Long id){
        return chamadoRepository.findById(id); //corrigir bug
    }

}
