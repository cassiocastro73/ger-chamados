package com.castrodev.ger_chamados;

import com.castrodev.ger_chamados.StatusChamado;
import com.castrodev.ger_chamados.dto.ChamadoCreateDTO;
import com.castrodev.ger_chamados.dto.ChamadoDTO;
import com.castrodev.ger_chamados.dto.ChamadoUpdateDTO;
import com.castrodev.ger_chamados.model.chamado.Chamado;
import com.castrodev.ger_chamados.repository.ChamadoRepository;
import com.castrodev.ger_chamados.service.ChamadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChamadoServiceTest {

    @Mock
    private ChamadoRepository chamadoRepository;

    @InjectMocks
    private ChamadoService chamadoService;

    private Chamado chamado;
    private ChamadoCreateDTO chamadoCreateDTO;
    private ChamadoUpdateDTO chamadoUpdateDTO;

    @BeforeEach
    void setUp() {
        // Objeto base para os testes
        chamado = new Chamado(
                1L,
                "Problema na Impressora",
                "A impressora do setor de TI não está funcionando.",
                "John",
                "john@email.com",
                StatusChamado.ABERTO,
                LocalDateTime.now()
        );

        // DTO para criação
        chamadoCreateDTO = new ChamadoCreateDTO(
                "Problema na Impressora",
                "A impressora do setor de TI não está funcionando.",
                "John",
                "john@email.com"
        );

        // DTO para atualização
        chamadoUpdateDTO = new ChamadoUpdateDTO(
                1L,
                "A impressora foi consertada, mas o toner está acabando.",
                StatusChamado.EM_ATENDIMENTO
        );
    }

    @Test
    @DisplayName("Deve retornar uma lista de ChamadoDTO ao buscar todos")
    void buscarTodos_DeveRetornarListaDeChamadoDTO() {
        // Arrange
        when(chamadoRepository.findAll()).thenReturn(Collections.singletonList(chamado));

        // Act
        List<ChamadoDTO> resultado = chamadoService.buscarTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(chamado.getTitulo(), resultado.get(0).getTitulo());
        verify(chamadoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar um Chamado ao buscar por ID existente")
    void buscarPorId_QuandoIdExiste_DeveRetornarChamado() {
        // Arrange
        when(chamadoRepository.findById(1L)).thenReturn(Optional.of(chamado));

        // Act
        Chamado resultado = chamadoService.buscarPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(chamado.getIdChamado(), resultado.getIdChamado());
        verify(chamadoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar por ID inexistente")
    void buscarPorId_QuandoIdNaoExiste_DeveLancarExcecao() {
        // Arrange
        when(chamadoRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        // Nota: O método original usa .get() diretamente, o que lança NoSuchElementException.
        // O ideal seria tratar esse Optional no service, mas o teste reflete o comportamento atual.
        assertThrows(java.util.NoSuchElementException.class, () -> {
            chamadoService.buscarPorId(99L);
        });
        verify(chamadoRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Deve criar um novo chamado com sucesso")
    void criarChamado_DeveRetornarChamadoCriado() {
        // Arrange
        // Não precisamos de when() para o save, pois ele retorna o objeto que foi passado.
        // Usaremos `any(Chamado.class)` para capturar o objeto salvo e verificar seus valores.
        when(chamadoRepository.save(any(Chamado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Chamado resultado = chamadoService.criarChamado(chamadoCreateDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(chamadoCreateDTO.getTitulo(), resultado.getTitulo());
        assertEquals(chamadoCreateDTO.getDescricao(), resultado.getDescricao());
        assertEquals(StatusChamado.ABERTO, resultado.getStatusChamado());
        assertNotNull(resultado.getDataAbertura());
        verify(chamadoRepository, times(1)).save(any(Chamado.class));
    }

    @Test
    @DisplayName("Deve excluir um chamado com sucesso")
    void excluirChamado_DeveChamarDeleteByIdDoRepositorio() {
        // Arrange
        Long idParaExcluir = 1L;
        // Configura o mock para não fazer nada quando deleteById for chamado
        doNothing().when(chamadoRepository).deleteById(idParaExcluir);

        // Act
        chamadoService.excluirChamado(idParaExcluir);

        // Assert
        // Verifica se o método deleteById foi chamado exatamente uma vez com o ID correto
        verify(chamadoRepository, times(1)).deleteById(idParaExcluir);
    }

    @Test
    @DisplayName("Deve atualizar um chamado com sucesso")
    void atualizarChamado_DeveRetornarChamadoDTOAtualizado() {
        // Arrange
        when(chamadoRepository.findById(chamadoUpdateDTO.getIdChamado())).thenReturn(Optional.of(chamado));
        when(chamadoRepository.save(any(Chamado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ChamadoDTO resultado = chamadoService.atualizarChamado(chamadoUpdateDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(chamadoUpdateDTO.getDescricao(), resultado.getDescricao());
        assertEquals(chamadoUpdateDTO.getStatusChamado(), resultado.getStatusChamado());
        verify(chamadoRepository, times(1)).findById(chamadoUpdateDTO.getIdChamado());
        verify(chamadoRepository, times(1)).save(any(Chamado.class));
    }

    @Test
    @DisplayName("Deve atualizar um chamado sem alterar a descrição se for nula")
    void atualizarChamado_QuandoDescricaoNula_NaoDeveAlterarDescricao() {
        // Arrange
        chamadoUpdateDTO.setDescricao(null); // Testa o caso da descrição nula
        String descricaoOriginal = chamado.getDescricao();

        when(chamadoRepository.findById(chamadoUpdateDTO.getIdChamado())).thenReturn(Optional.of(chamado));
        when(chamadoRepository.save(any(Chamado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ChamadoDTO resultado = chamadoService.atualizarChamado(chamadoUpdateDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(descricaoOriginal, resultado.getDescricao()); // Descrição não deve mudar
        assertEquals(chamadoUpdateDTO.getStatusChamado(), resultado.getStatusChamado()); // Status deve mudar
        verify(chamadoRepository, times(1)).findById(chamadoUpdateDTO.getIdChamado());
        verify(chamadoRepository, times(1)).save(any(Chamado.class));
    }

    @Test
    @DisplayName("Deve retornar uma lista de chamados ao buscar por status")
    void buscarPorStatus_DeveRetornarListaDeChamadoDTO() {
        // Arrange
        StatusChamado status = StatusChamado.ABERTO;
        when(chamadoRepository.findByStatusChamado(status)).thenReturn(Collections.singletonList(new ChamadoDTO(chamado)));

        // Act
        List<ChamadoDTO> resultado = chamadoService.buscarPorStatus(status);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(status, resultado.get(0).getStatusChamado());
        verify(chamadoRepository, times(1)).findByStatusChamado(status);
    }
}