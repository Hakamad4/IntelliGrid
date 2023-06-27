package com.fiap.intelligrid.integration.viacep;

import com.fiap.intelligrid.integration.viacep.domain.exception.ViaCepException;
import org.springframework.stereotype.Service;
import com.fiap.intelligrid.integration.viacep.domain.response.ViaCepResponse;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ViaCepService {

	private final WebClient webClient;

	public ViaCepService(WebClient webClient) {
		this.webClient = webClient;
	}

	public ViaCepResponse buscaEnderecoPorCep(String cep) throws ViaCepException {
		final String regex = "\\d{8}";
		if (!cep.matches(regex)) {
			throw new ViaCepException("CEP inválido. Deve conter apenas 8 números.");
		}

		try {
			String url = "https://viacep.com.br/ws/" + cep + "/json";
			return webClient.get()
					.uri(url)
					.retrieve()
					.bodyToMono(ViaCepResponse.class)
					.block();
		} catch (Exception e) {
			throw new ViaCepException(e.getMessage());
		}
	}



}
