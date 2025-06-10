package com.castrodev.ger_chamados.controller;

import com.castrodev.ger_chamados.dto.ChamadoCreateDTO;
import com.castrodev.ger_chamados.dto.ChamadoDTO;
import com.castrodev.ger_chamados.model.chamado.Chamado;
import com.castrodev.ger_chamados.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("chamados")
public class ChamadoController {

    @Autowired
    ChamadoService chamadoService;

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> buscarTodosChamados(){
        //service qeu faz a chamada ao repository retornos http com a resposta
        List<ChamadoDTO> chamados = chamadoService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(chamados);
    }

    @GetMapping("/{idChamado}")
    public ResponseEntity<ChamadoDTO> buscarChamado(@PathVariable Long idChamado){
        //service qeu faz a chamada ao repository
        Chamado chamado = chamadoService.buscarPorId(idChamado);
        ChamadoDTO chamadoDto = new ChamadoDTO(chamado);
        return ResponseEntity.status(HttpStatus.OK).body(chamadoDto);
    }

    @PostMapping("/novo-chamado")
    public ResponseEntity<ChamadoDTO> criarNovoChamado(@RequestBody ChamadoCreateDTO chamadoCreateDTO){
        ChamadoDTO chamadoCadastrado = new ChamadoDTO(chamadoService.criarChamado(chamadoCreateDTO));
        return ResponseEntity.status(HttpStatus.OK).body(chamadoCadastrado);
    }

    //public void ResponseEntity<ChamadoDTO> editarChamado(){
       // return ResponseEntity.status(HttpStatus.OK);
   // }
}
