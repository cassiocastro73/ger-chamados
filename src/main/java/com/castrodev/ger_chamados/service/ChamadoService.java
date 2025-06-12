package com.castrodev.ger_chamados.service;

import com.castrodev.ger_chamados.dto.ChamadoCreateDTO;
import com.castrodev.ger_chamados.dto.ChamadoDTO;
import com.castrodev.ger_chamados.dto.ChamadoUpdateDTO;
import com.castrodev.ger_chamados.model.chamado.Chamado;
import com.castrodev.ger_chamados.repository.ChamadoRepository;
import org.springframework.beans.BeanUtils;
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

    public Chamado buscarPorId(Long id){
        return chamadoRepository.findById(id).get(); //corrigir bug
    }

    public Chamado criarChamado(ChamadoCreateDTO chamadoCreateDTO){
        Chamado chamado = new Chamado();
        BeanUtils.copyProperties(chamadoCreateDTO, chamado);
        chamadoRepository.save(chamado);
        return chamado;
    }
    public void excluirChamado(Long idChamado){
        chamadoRepository.deleteById(idChamado);
    }

    public ChamadoDTO atualizarChamado(ChamadoUpdateDTO chamadoUpdateDTO){
       Chamado chamado = chamadoRepository.findById(chamadoUpdateDTO.getIdChamado()).get();
       chamado.setStatusChamado(chamadoUpdateDTO.getStatusChamado());
       if(chamadoUpdateDTO.getDescricao() != null){
           chamado.setDescricao(chamadoUpdateDTO.getDescricao());
       }
        chamadoRepository.save(chamado);
        return new ChamadoDTO(chamado);
    }

}
