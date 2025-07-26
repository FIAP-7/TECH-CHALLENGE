package br.com.fiap.postech.gestao_restaurantes.infra.controller.json;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Objeto para atualizar a senha de um usuário")
public class NovaSenhaJson {
	
	@NotBlank
	@Size(min = 8, message = "Senha necessita de 8 caracteres ou mais")
	@Schema(description = "Nova senha", example = "novaSenhaSegura123", required = true)
	private String novaSenha;

}
