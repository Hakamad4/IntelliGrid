package com.fiap.intelligrid.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

	private final String mensagem;
	private final HttpStatus status;
	private List<CamposErroResponse> composErro;

}
