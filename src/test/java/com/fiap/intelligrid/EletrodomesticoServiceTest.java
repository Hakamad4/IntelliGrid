package com.fiap.intelligrid;

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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EletrodomesticoServiceTest {
    @InjectMocks
    EletrodomesticoService eletrodomesticoService;

    @Mock
    EletrodomesticoRepository eletrodomesticoRepository;

    @Test
    @Transactional
    public void deveSalvarEletrodomestico(){
        EletrodomesticoRequest eletrodomesticoRequest = new EletrodomesticoRequest(null,"teste","arno","220");
        eletrodomesticoService.salvar(eletrodomesticoRequest);
        Mockito.verify(eletrodomesticoRepository,times(1)).save(Mockito.any(Eletrodomestico.class));
    }

    @Test
    public void deveBuscarEletrodomesticoPorId() throws Exception{
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
    public void deveBuscarResponsePorId() throws Exception{
        Eletrodomestico eletrodomestico = new Eletrodomestico(1L, "teste", "arno", "220");

        when(eletrodomesticoRepository.findById(anyLong())).thenReturn(Optional.of(eletrodomestico));
        EletrodomesticoResponse resultado = eletrodomesticoService.buscarResponsePorId(1L);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(eletrodomestico.getId(), resultado.id());
    }

    @Test
    public void deveBuscarResponsePorId_EletrodomesticoNaoEncontrado(){
        when(eletrodomesticoRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(EletrodomesticoNotFoundException.class, () -> {
            eletrodomesticoService.buscarResponsePorId(1L);
        });

    }

}
