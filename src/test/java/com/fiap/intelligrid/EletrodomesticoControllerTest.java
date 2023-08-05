package com.fiap.intelligrid;

import com.fiap.intelligrid.controller.EletrodomesticoController;
import com.fiap.intelligrid.controller.request.EletrodomesticoAtualizacaoRequest;
import com.fiap.intelligrid.controller.request.EletrodomesticoRequest;
import com.fiap.intelligrid.controller.response.EletrodomesticoResponse;
import com.fiap.intelligrid.service.EletrodomesticoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EletrodomesticoControllerTest {
    @InjectMocks
    private EletrodomesticoController eletrodomesticoController;

    @Mock
    private EletrodomesticoService eletrodomesticoService;

    MockMvc mockMvc;


    @Test
    public void deveCriarEletrodomesticoComSucesso() throws Exception{
        EletrodomesticoRequest request =  new EletrodomesticoRequest(null,"teste","arno", "220");
        ResponseEntity<EletrodomesticoRequest> response = eletrodomesticoController.cadastrarEletrodomestico(request);
        Mockito.verify(eletrodomesticoService,Mockito.times(1)).salvar(request);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());

    }

    @Test
    public void deveRetornarEletrodomesticoPorId()throws Exception{
        EletrodomesticoResponse response =  new EletrodomesticoResponse(1L,"teste","arno", "220");
        ResponseEntity<EletrodomesticoResponse> response1 = eletrodomesticoController.buscarEletroeletronicoPorId(response.id());
        Mockito.verify(eletrodomesticoService,Mockito.times(1)).buscarResponsePorId(response.id());
        assertEquals(HttpStatus.OK,response1.getStatusCode());
    }

    @Test
    public void deveDeletarEletrodomesticoPorId() throws Exception {
        EletrodomesticoRequest request =  new EletrodomesticoRequest(1L,"teste","arno", "220");
        ResponseEntity<EletrodomesticoRequest> response = eletrodomesticoController.excluirEletrodomestico(request.id());
        Mockito.verify(eletrodomesticoService,Mockito.times(1)).deletar(request.id());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void deveAtualizarEletrodomesticoPorId()throws Exception{
        EletrodomesticoAtualizacaoRequest request =  new EletrodomesticoAtualizacaoRequest("teste","arno", "220");
        EletrodomesticoResponse response =  new EletrodomesticoResponse(1L,"teste","arno", "220");
        ResponseEntity<EletrodomesticoResponse> response1 = eletrodomesticoController.atualizarEletrodomestico(response.id(),request);
        Mockito.verify(eletrodomesticoService, Mockito.times(1)).atualizaEletrodomestico(response.id(),request);
        assertEquals(HttpStatus.OK,response1.getStatusCode());
    }


    @Test
    public void deveRetornarTodosEletrodomesticos(){
        ResponseEntity<List<EletrodomesticoResponse>> response = eletrodomesticoController.buscarEletrodomesticos();
        Mockito.verify(eletrodomesticoService, Mockito.times(1)).buscarTodos();
        assertEquals(HttpStatus.OK,response.getStatusCode());

    }


}



