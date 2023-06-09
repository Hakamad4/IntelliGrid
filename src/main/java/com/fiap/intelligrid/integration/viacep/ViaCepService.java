package com.fiap.intelligrid.integration.viacep;

import com.fiap.intelligrid.integration.viacep.domain.exception.ViaCepException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fiap.intelligrid.integration.viacep.domain.response.ViaCepResponse;

@Service
public class ViaCepService {

	private final RestTemplate restTemplate;

	public ViaCepService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ViaCepResponse buscaEnderecoPorCep(String cep) throws ViaCepException {
		final String regex = "\\d{8}";
		if (!cep.matches(regex)) {
			throw new ViaCepException("CEP inválido. Deve conter apenas 8 números.");
		}

		try {
			String url = "https://viacep.com.br/ws/" + cep + "/json";
			return this.restTemplate.getForObject(url, ViaCepResponse.class);
		} catch (Exception e) {
			throw new ViaCepException(e.getMessage());
		}
	}



}
