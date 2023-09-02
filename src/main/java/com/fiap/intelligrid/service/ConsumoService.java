package com.fiap.intelligrid.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.fiap.intelligrid.controller.request.ConsumoAtualizacaoRequest;
import com.fiap.intelligrid.controller.request.ConsumoRequest;
import com.fiap.intelligrid.controller.response.ConsumoResponse;
import com.fiap.intelligrid.domain.entity.Consumo;
import com.fiap.intelligrid.domain.entity.Eletrodomestico;
import com.fiap.intelligrid.domain.repository.ConsumoRepository;
import com.fiap.intelligrid.domain.repository.EletrodomesticoRepository;
import com.fiap.intelligrid.exceptions.ConsumoNotFoundException;
import com.fiap.intelligrid.exceptions.EletrodomesticoNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ConsumoService {

	private final ConsumoRepository consumoRepository;
	private final EletrodomesticoRepository eletrodomesticoRepository;
	
	public ConsumoService(ConsumoRepository consumoRepository, EletrodomesticoRepository eletrodomesticoRepository) {
		this.consumoRepository = consumoRepository;
		this.eletrodomesticoRepository = eletrodomesticoRepository;
	}
	
	@Transactional
	public void salvar(ConsumoRequest consumoRequest) throws EletrodomesticoNotFoundException {
	
		
		System.out.println("consumoRequest eletrodomesticoId\n"+consumoRequest.eletrodomesticoId()+"\n");
		
		Optional<Eletrodomestico> eletrodomesticoOpt = eletrodomesticoRepository.findById(consumoRequest.eletrodomesticoId());
		
		if (!eletrodomesticoOpt.isPresent()) {
		    throw new EletrodomesticoNotFoundException();
		}

		var eletrodomestico = eletrodomesticoOpt.get();
	   
		Consumo consumo = new Consumo();
		consumo.setEletrodomestico(eletrodomestico);
		consumo.setTempo(consumoRequest.tempo());
		
		System.out.println("consumo tempo\n"+consumo.getTempo()+"\n");
		
		System.out.println("consumoRequest tempo\n"+consumoRequest.tempo()+"\n");
		
		 long millis = 3600000;
		
		 //consumoRequest.tempo()
		 
		// long hours = TimeUnit.MINUTES.toHours(120);
		 
		long hours = TimeUnit.MILLISECONDS.toHours(millis) % 24;
	    long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
	    long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;
	    long milliseconds = millis % 1000;
		 
		 
		 
		System.out.println(hours+":"+minutes+":"+seconds+":"+milliseconds);
		 
		 			        
		long totalHoras = TimeUnit.MILLISECONDS.toHours( millis );
		
		BigDecimal result4 = BigDecimal.valueOf(totalHoras);
		
		System.out.println("\ntotalHoras "+totalHoras+" - result4 "+result4+"\n");
		
		consumo.setConsumoTotal( totalHoras*eletrodomestico.getPotencia() );
	//	System.out.println("\n"+consumo+"\n");
		consumoRepository.save(consumo);
		
		
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
	
	public List<Consumo> buscarPorPeriodo(LocalDate inicio, LocalDate termino) {
        return consumoRepository.findByDia( inicio, termino);
    }
	
	public ConsumoResponse buscarResponsePorId(Long id) throws ConsumoNotFoundException {
	        return new ConsumoResponse(
	                buscarPorId(id)
	        );
	}
	
	public Consumo buscarPorId(Long id) throws ConsumoNotFoundException {
        Optional<Consumo> consumo = consumoRepository.findById(id);
        if (consumo.isEmpty()) {
            throw new ConsumoNotFoundException("Consumo não encontrado");
        }
        return consumo.get();
    }
	
	@Transactional
	public ConsumoResponse atualizaConsumo(Long id, ConsumoAtualizacaoRequest dadosAtualizacao) throws ConsumoNotFoundException {
		Consumo consumo = buscarPorId(id);
	        if (dadosAtualizacao.consumoTotal() !=  0.0) {
	        	consumo.setConsumoTotal(dadosAtualizacao.consumoTotal());
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
