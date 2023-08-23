package com.fiap.intelligrid.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fiap.intelligrid.controller.request.ConsumoAtualizacaoRequest;
import com.fiap.intelligrid.controller.request.ConsumoRequest;
import com.fiap.intelligrid.controller.response.ConsumoResponse;
import com.fiap.intelligrid.domain.entity.Consumo;
import com.fiap.intelligrid.domain.repository.ConsumoRepository;
import com.fiap.intelligrid.exceptions.ConsumoNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ConsumoService {

	private final ConsumoRepository consumoRepository;
	
	public ConsumoService(ConsumoRepository consumoRepository) {
		this.consumoRepository = consumoRepository;
	}
	
	@Transactional
	public void salvar(ConsumoRequest consumoRequest) {
		consumoRepository.save(new Consumo(consumoRequest));
	}
	
	@Transactional
    public void deletar(Long id) throws ConsumoNotFoundException {
        long deleted = consumoRepository.deleteConsumoById(id);
        if (deleted == 0) {
            throw new ConsumoNotFoundException("Consumo não encontrado.");
        }
    }
	
	public List<ConsumoResponse> buscarTodos() {
	        return consumoRepository.findAll().stream().map(ConsumoResponse::new).toList();
	}
	
	public ConsumoResponse buscarResponsePorId(Long id) throws ConsumoNotFoundException {
	        return new ConsumoResponse(
	                buscarPorId(id)
	        );
	}
	
	public Consumo buscarPorId(Long id) throws ConsumoNotFoundException {
        Optional<Consumo> consumo = consumoRepository.findById(id);
        if (consumo.isEmpty()) {
            throw new ConsumoNotFoundException("Eletrodomestico não encontrado");
        }
        return consumo.get();
    }
	
	@Transactional
	public ConsumoResponse atualizaConsumo(Long id, ConsumoAtualizacaoRequest dadosAtualizacao) throws ConsumoNotFoundException {
		Consumo consumo = buscarPorId(id);
	        if (dadosAtualizacao.potenciaTotal() !=  0.0) {
	        	consumo.setPotenciaTotal(dadosAtualizacao.potenciaTotal());
	        }
	        if (dadosAtualizacao.tempo() != null) {
	        	consumo.setTempo(dadosAtualizacao.tempo());
	        }
	        if (dadosAtualizacao.dia() != null) {
	        	consumo.setDia(dadosAtualizacao.dia());
	        }
	        return new ConsumoResponse(consumo);
	}
}
