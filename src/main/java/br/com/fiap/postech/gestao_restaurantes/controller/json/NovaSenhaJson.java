package br.com.fiap.postech.gestao_restaurantes.controller.json;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NovaSenhaJson {
	
	@NotBlank
	private String novaSenha;

}
