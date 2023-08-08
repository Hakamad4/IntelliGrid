package com.fiap.intelligrid;

import com.fiap.intelligrid.controller.request.EletrodomesticoAtualizacaoRequest;
import com.fiap.intelligrid.controller.request.EletrodomesticoRequest;
import com.fiap.intelligrid.controller.response.EletrodomesticoResponse;
import com.fiap.intelligrid.domain.entity.Eletrodomestico;
import com.fiap.intelligrid.domain.repository.EletrodomesticoRepository;
import com.fiap.intelligrid.exceptions.EletrodomesticoNotFoundException;
import com.fiap.intelligrid.service.EletrodomesticoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EletrodomesticoServiceTest {
    
    @InjectMocks
    EletrodomesticoService eletrodomesticoService;

    @Mock
    EletrodomesticoRepository eletrodomesticoRepository;

    @Test
    @Transactional
    public void deveSalvarEletrodomestico() {
        EletrodomesticoRequest eletrodomesticoRequest = new EletrodomesticoRequest(null,"teste","arno","220");
        eletrodomesticoService.salvar(eletrodomesticoRequest);
        verify(eletrodomesticoRepository,times(1)).save(Mockito.any(Eletrodomestico.class));
    }

    @Test
    public void deveBuscarEletrodomesticoPorId() throws Exception {
        Eletrodomestico eletrodomestico = new Eletrodomestico(1L,"teste","arno","220");
        when(eletrodomesticoRepository.findById(anyLong())).thenReturn(Optional.of(eletrodomestico));
        Eletrodomestico resultado = eletrodomesticoService.buscarPorId(1L);
        Assertions.assertEquals(eletrodomestico,resultado);

    }

    @Test
    public void deveBuscarPorId_EletrodomesticoNaoEncontrado() {
        when(eletrodomesticoRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(EletrodomesticoNotFoundException.class, () -> {
            eletrodomesticoService.buscarPorId(1L);
        });
    }

    @Test
    public void deveBuscarResponsePorId() throws Exception {
        Eletrodomestico eletrodomestico = new Eletrodomestico(1L, "teste", "arno", "220");

        when(eletrodomesticoRepository.findById(anyLong())).thenReturn(Optional.of(eletrodomestico));
        EletrodomesticoResponse resultado = eletrodomesticoService.buscarResponsePorId(1L);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(eletrodomestico.getId(), resultado.id());
        Assertions.assertEquals(eletrodomestico.getNome(), resultado.nome());
        Assertions.assertEquals(eletrodomestico.getModelo(), resultado.modelo());
        Assertions.assertEquals(eletrodomestico.getPotencia(), resultado.potencia());
    }

    @Test
    public void deveBuscarResponsePorId_EletrodomesticoNaoEncontrado() {
        when(eletrodomesticoRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(EletrodomesticoNotFoundException.class, () -> {
            eletrodomesticoService.buscarResponsePorId(1L);
        });

    }

    @Test
    public void deveRetornarTodosEletrodomesticos() {

        List<Eletrodomestico> eletrodomesticos = new ArrayList<>();
        eletrodomesticos.add(new Eletrodomestico(1L, "teste1", "arno", "220"));
        eletrodomesticos.add(new Eletrodomestico(2L, "teste2", "arno", "110"));
        eletrodomesticos.add(new Eletrodomestico(3L, "teste3", "arno", "110"));

        when(eletrodomesticoRepository.findAll()).thenReturn(eletrodomesticos);

        List<EletrodomesticoResponse> resultados = eletrodomesticoService.buscarTodos();

        Assertions.assertNotNull(resultados);
        Assertions.assertEquals(eletrodomesticos.size(), resultados.size());

        for (int i = 0; i < eletrodomesticos.size(); i++) {
                    EletrodomesticoResponse resultado = resultados.get(i);
                    Eletrodomestico eletrodomestico = eletrodomesticos.get(i);

                    Assertions.assertEquals(eletrodomestico.getId(), resultado.id());
                    Assertions.assertEquals(eletrodomestico.getNome(), resultado.nome());
                    Assertions.assertEquals(eletrodomestico.getModelo(), resultado.modelo());
                    Assertions.assertEquals(eletrodomestico.getPotencia(), resultado.potencia());
        }
    }
    
    @Test
    public void deveDeletarEletrodomesticosPorId() throws Exception {

    when(eletrodomesticoRepository.deleteEletrodomesticoById(anyLong())).thenReturn(1L);
    eletrodomesticoService.deletar(1L);
    verify(eletrodomesticoRepository, times(1)).deleteEletrodomesticoById(1L);

    }

    @Test
    public void deveLancarExcessaoDeletarEletrodomesticoNaoEncontrado() {

        when(eletrodomesticoRepository.deleteEletrodomesticoById(anyLong())).thenReturn(0L);

        Assertions.assertThrows(EletrodomesticoNotFoundException.class, () -> {
            eletrodomesticoService.deletar(1L);
        });

        verify(eletrodomesticoRepository, times(1)).deleteEletrodomesticoById(1L);
    }

    @Test
    public void testAtualizaEletrodomestico() throws Exception {

        Eletrodomestico eletrodomestico = new Eletrodomestico(1L, "teste 1", "arno", "220");

        when(eletrodomesticoRepository.findById(anyLong())).thenReturn(Optional.of(eletrodomestico));

        EletrodomesticoAtualizacaoRequest dadosAtualizacao = new EletrodomesticoAtualizacaoRequest("teste 1", "arno", "220");

        EletrodomesticoResponse resultado = eletrodomesticoService.atualizaEletrodomestico(1L, dadosAtualizacao);

        verify(eletrodomesticoRepository, times(1)).findById(1L);

        Assertions.assertEquals(dadosAtualizacao.nome(), resultado.nome());
        Assertions.assertEquals(dadosAtualizacao.modelo(), resultado.modelo());
        Assertions.assertEquals(dadosAtualizacao.potencia(), resultado.potencia());
    }

}




