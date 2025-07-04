package com.castrodev.ger_chamados.controller;

import com.castrodev.ger_chamados.StatusChamado;
import com.castrodev.ger_chamados.dto.ChamadoCreateDTO;
import com.castrodev.ger_chamados.dto.ChamadoDTO;
import com.castrodev.ger_chamados.dto.ChamadoUpdateDTO;
import com.castrodev.ger_chamados.model.chamado.Chamado;
import com.castrodev.ger_chamados.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chamados")
public class ChamadoController {

    @Autowired
    ChamadoService chamadoService;

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> buscarChamados(@RequestParam(required = false)StatusChamado statusChamado){
        if(statusChamado != null){
            List<ChamadoDTO> chamados = chamadoService.buscarPorStatus(statusChamado);
            return ResponseEntity.status(HttpStatus.OK).body(chamados);
        }
        else{
            List<ChamadoDTO> chamados = chamadoService.buscarTodos();
            return ResponseEntity.status(HttpStatus.OK).body(chamados);
        }
    }

    @GetMapping("/{idChamado}")
    public ResponseEntity<ChamadoDTO> detalharChamado(@PathVariable Long idChamado){
        //service qeu faz a chamada ao repository
        Chamado chamado = chamadoService.buscarPorId(idChamado);
        ChamadoDTO chamadoDto = new ChamadoDTO(chamado);
        return ResponseEntity.status(HttpStatus.OK).body(chamadoDto);
    }

    @PostMapping("/novo-chamado")
    public ResponseEntity<ChamadoDTO> criarNovoChamado(@RequestBody @Valid ChamadoCreateDTO chamadoCreateDTO){
        ChamadoDTO chamadoCadastrado = new ChamadoDTO(chamadoService.criarChamado(chamadoCreateDTO));
        return ResponseEntity.status(HttpStatus.OK).body(chamadoCadastrado);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ChamadoDTO> atualizarChamado(@RequestBody ChamadoUpdateDTO chamadoUpdateDTO){
        ChamadoDTO chamadoAtualizado = chamadoService.atualizarChamado(chamadoUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(chamadoAtualizado);
    }

    @DeleteMapping("/{idChamado}")
    public ResponseEntity<Void> apagarChamado(@PathVariable Long idChamado){
        chamadoService.excluirChamado(idChamado);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
